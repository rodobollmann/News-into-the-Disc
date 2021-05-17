/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.service;

import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.rodobollmann.model.News;

/**
 * The class
 *
 * @author Rodolfo Bollmann Checura
 */
public class ContractsImpFaker implements  Contracts{
    /**
     * Get the list of news.
     *
     * @param size of the list.
     * @return the list of news.
     */
    @Override
    public List<News> retrieveNews(Integer size) {
        return new ArrayList<>();
    }

    /**
     * Save one news to the system.
     *
     * @param news to save.
     */
    @Override
    public void save(News news) {

    }
}
