/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ocpsoft.prettytime.PrettyTime;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
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
     * The PrettyTime
     */
    private static final PrettyTime PRETTY_TIME = new PrettyTime();

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

            //Bind the source
            this.rowNewsBinding.rnTvSource.setText(news.getSource());

            //Bind the description
            this.rowNewsBinding.rnTvDescription.setText(news.getDescription());

            //zonedDateTime to Date
            final Date theDate = DateTimeUtils.toDate(news.getPublishedAt().toInstant());
            //Bind the Date
            this.rowNewsBinding.rnTvPublishedAt.setText(PRETTY_TIME.format(theDate));

            //Bind the image
            if (news.getUrlImage() != null){
                final Uri uri = Uri.parse(news.getUrlImage());
                this.rowNewsBinding.rnSdvImage.setImageURI(uri);
            } else{
                //No image, use the default
                this.rowNewsBinding.rnSdvImage.setImageResource(R.drawable.ic_launcher_foreground);
            }

        }
    }
}
