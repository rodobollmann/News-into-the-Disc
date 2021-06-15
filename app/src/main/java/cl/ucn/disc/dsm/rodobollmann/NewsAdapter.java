/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.rodobollmann.databinding.RowNewsBinding;
import cl.ucn.disc.dsm.rodobollmann.model.News;

/**
 * The Adapter of News
 *
 * @author Rodolfo Bollmann Checura
 */
public final class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    /**
     * DataSource
     */
    private List<News> theNews = new ArrayList<>();

    /**
     * The Costructor
     */
    public NewsAdapter() {
        //Nothing here
    }

    /**
     * Update the datasource
     *
     * @param news to use as data source
     */
    public void setNews(final List<News> news){
        this.theNews = news;

        //Notify  the RecyclerView
        this.notifyDataSetChanged();
    }

    /**
     * Called when the RecyclerView need a fresh row of NewsViewHolder.
     *
     * @param parent The viewGroup where the new will be added
     * @param viewType the view of this type
     * @return the NewsViewHolder ready for action
     */
    @Override
    public NewsViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        //Call the constructor inflating the layout
        return new NewsViewHolder(
                RowNewsBinding.inflate(
                        LayoutInflater.from(parent.getContext())
                )
        );
    }

    /**
     * Called by the RecyclerView when need to display some data at specific position
     *
     * @param holder to use to set the GUI data
     * @param position of the dataset to show
     */
    @Override
    public void onBindViewHolder(final NewsAdapter.NewsViewHolder holder, int position) {
        //Bind the ViewHolder + News at certain position.
        holder.bind(this.theNews.get(position));

    }

    /**
     *
     * @return the size of the dataset
     */
    @Override
    public int getItemCount() {
        return this.theNews.size();
    }

    /**
     * The viewsHolder implementation of News
     */
    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        /**
         * The GUI of News.
         */
        private final RowNewsBinding rowNewsBinding;

        /**
         *
         * @param rowNewsBinding the layout to use
         */
        public NewsViewHolder(final RowNewsBinding rowNewsBinding) {
            super(rowNewsBinding.getRoot());
            this.rowNewsBinding = rowNewsBinding;
        }

        /**
         *
         * @param news to use.
         */
        public void bind(final News news){

            //BInd the title
            this.rowNewsBinding.rnTvTitle.setText(news.getTitle());

            //Bind the author
            this.rowNewsBinding.rnTvAuthor.setText(news.getAuthor());

        }
    }
}
