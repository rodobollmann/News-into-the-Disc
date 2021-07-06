/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;

import cl.ucn.disc.dsm.rodobollmann.service.Contracts;
import cl.ucn.disc.dsm.rodobollmann.service.ContractsImpFaker;
import cl.ucn.disc.dsm.rodobollmann.service.newsapi.NewsAPIService;

/**
 * The NewsViewModel
 *
 * @author Rodolfo Bollmann Checura
 */
public final class NewsViewModel extends AndroidViewModel {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(NewsViewModel.class);

    /**
     * The Contract.
     */
    private final Contracts contracts = new NewsAPIService(); //ContractsImpFaker();

    /**
     * The List Of News.
     */
    private final MutableLiveData<List<News>> theNews;

    /**
     * The Constructor.
     * @param application to use.
     */
    public NewsViewModel(final Application application) {
        super(application);

        this.theNews = new MutableLiveData<>();
    }

    /**
     *
     * @return the list of news wrapped inside a LiveData
     */
    public LiveData<List<News>> getNews(){

        return this.theNews;
    }

    /**
     * Refresh (get) the news from the backend
     */
    public void refresh(){

        //Show message if theNews are empty
        if(this.theNews.getValue() == null || this.theNews.getValue().size() == 0){
            log.warn("No News, fetching from contracts ..");
        }

        // Background thread

        Executors.newSingleThreadExecutor().execute(() ->{
        List<News> news = this.contracts.retrieveNews(50);

            //GUI thread
            new Handler(Looper.getMainLooper()).post(() ->{
                this.theNews.setValue(news);
            });

        });

    }

}
