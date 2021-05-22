/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.ucn.disc.dsm.rodobollmann.model.News;

/**
 * The class
 *
 * @author Rodolfo Bollmann Checura
 */
public class ContractsImpFaker implements  Contracts{

    private final List<News> listNews = new ArrayList<>();

    /**
     * The Constructor.
     */
    public ContractsImpFaker() {
        //Nothing here
    }

  /**
   * Get the list of news.
   *
   * @param size of the list.
   * @return the list of news.
   */
  @Override
  public List<News> retrieveNews(final Integer size) {

    // Preconditions
    if (size <= 0) {
      throw new IllegalArgumentException("size cannot be zero or negative");
    }

    // Error! Warning!
    return Collections.unmodifiableList(this.listNews);

  }

    /**
     * Save one news to the system.
     *
     * @param news to save.
     */
    @Override
    public void save(News news) {

        //Nullity test
        if (news == null){
            throw new IllegalArgumentException("Need news != null");
        }

        //No duplicates allowed
        for (News n : this.listNews){
            if (n != null && n.getId().equals(news.getId())){
                throw new IllegalArgumentException("News already in the list");
            }
        }

        this.listNews.add(news);
    }
}
