/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.service;

import java.util.List;

import cl.ucn.disc.dsm.rodobollmann.model.News;

/**
 * The class.
 *
 * @author Rodolfo Bollmann Checura
 */
public interface Contracts {
    /**
     * Get the list of news.
     *
     * @param size of the list.
     * @return the list of news.
     */
    List<News> retrieveNews(Integer size);

    /**
     * Save one news to the system.
     * @param news to save.
     */
    void save(News news);
}
