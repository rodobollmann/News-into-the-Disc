/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.service.newsapi;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.rodobollmann.model.News;
import cl.ucn.disc.dsm.rodobollmann.model.newsapi.Article;
import cl.ucn.disc.dsm.rodobollmann.model.newsapi.NewsAPIResponse;
import cl.ucn.disc.dsm.rodobollmann.service.Contracts;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * The service of NewsAPI
 *
 * @author Rodolfo Bollmann Checura
 */
public final class NewsAPIService implements Contracts {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(NewsAPIService.class);

    /**
     * The NewsAPI
     */
    private final NewsAPI newsAPI;

    /**
     * The current zone
     */
    private static final ZoneId theZone = ZoneId.of("-4");

    /**
     * The constructor: build the NewsAPI
     */
    public NewsAPIService(){

        this.newsAPI = new Retrofit.Builder()
                //The URL used to connect
                .baseUrl(NewsAPI.BASE_URL)
                //Json to POJO (Java converter)
                .addConverterFactory(GsonConverterFactory.create())
                // Build the Retrofit
                .build()
                //Create the NewsAPI implementation
                .create(NewsAPI.class);
    }

    /**
     * Get the list of news.
     *
     * @param size of the list.
     * @return the list of news.
     */
    @Override
    public List<News> retrieveNews(Integer size) {

        //The call to get the NewsAPI response
        Call<NewsAPIResponse> theCall = this.newsAPI.getTopHeadlines(size, NewsAPI.Category.science);

        try{

            //Connect and get the response in synchronize way
            Response<NewsAPIResponse> theResponse = theCall.execute();

            // .. if unsuccessful ..
            if (!theResponse.isSuccessful()) {
                log.error("Cant get the news: <{}> ", theResponse.errorBody().string());
                throw new RuntimeException("Cant get the news, response code: " + theResponse.code());
            }

                // ..read the NewsAPIResponse from the body of the call
                NewsAPIResponse newsAPIResponse = theResponse.body();

                //Nullity verification
                if (newsAPIResponse == null){
                    throw new RuntimeException("Body of NewsAPI was null");
                }

                //The result
                List<News> theNews = new ArrayList<>();

                //..iterate over the list the Article
                for (Article article : newsAPIResponse.articles){

                    //  TODO: Convert to transform pattern
                    News news =
                        new News(
                        article.title,
                        article.source.name,
                        article.author,
                        article.url,
                        article.urlToImage,
                        article.description,
                        article.content, 
                        parseDate(article.publishedAt)
                    );

                    //Insert into the list of News
                    theNews.add(news);
                
                }

                return theNews;

        }   catch (IOException ex){
            log.error("Can get the news", ex);
            throw new RuntimeException("Cant get the News", ex);
        }

    }

    /**
     * Convert the String *** to ZonedDateTime
     * @param publishedAt used to convert
     * @return the{@link ZonedDateTime}
     */
    private ZonedDateTime parseDate(String publishedAt) {

        return ZonedDateTime
                .parse(publishedAt)
                .withZoneSameInstant(theZone);
    }

    /**
     * Save one news to the system.
     *
     * @param news to save.
     */
    @Override
    public void save(News news) {
        throw new NotImplementedException("Method not implemented!");
    }


    /**
     * The interface to NewsAPI
     */
    public interface NewsAPI{

        /**
         * The base URL
         */
        String BASE_URL = "https://newsapi.org/v2/";

        /**
         * The API key
         */
        String API_KEY = "49e8efa1359f4990b99961f6bb9f31e0";

         /**
        * Search through millions of articles from over 80,000 large and small news sources and blogs.
        *
        * This endpoint suits article discovery and analysis.
        *
        * @return the Call with the {@link NewsAPIResponse}
        */
        @Headers("X-Api-Key: " + API_KEY)
        @GET("everything")
        Call<NewsAPIResponse> getEverything();

        /**
         * https://newsapi.org/docs/endpoints/top-headlines
         *
         * @param pageSize The number of results to return per page (request). 20 is the default, 100 is the maximum.
         * @return the call to get NewsAPIResponse.
         */
        @Headers("X-Api-Key: " + API_KEY)
        @GET("top-headlines")
        Call<NewsAPIResponse> getTopHeadlines(@Query("pageSize") int pageSize);

        /**
         * https://newsapi.org/docs/endpoints/top-headlines
         */
        @Headers("X-Api-Key: " + API_KEY)
        @GET("top-headlines")
        Call<NewsAPIResponse> getTopHeadlines(@Query("pageSize") int pageSize,
                                              @Query("category") Category category);

        /**
         * The category you want to get headlines for. Possible options: business,
         * entertainment, general, health, science, sports, technology.
         * Note: you can't mix this param with the sources param.
         */
        enum Category{
            business, entertainment, general, health, science, sports, technology

        }

    }

}
