/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.model.newsapi;


import java.util.List;

/**
 * https://www.jsonschema2pojo.org
 */
public class NewsAPIResponse {

    public String status;
    public Integer totalResults;
    public List<Article> articles = null;

}
