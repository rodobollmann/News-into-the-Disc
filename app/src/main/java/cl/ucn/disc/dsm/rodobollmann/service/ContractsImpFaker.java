/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.service;

import com.github.javafaker.Faker;

import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

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

        int N = 20;

        // Generate test data
        Faker faker = new Faker();

        for (int i = 0; i < N; i++){

            //Test: valid data
            News news = new News(
                    faker.book().title(),
                    faker.book().publisher(),
                    faker.book().author(),
                    faker.internet().url(),
                    faker.internet().url(),
                    faker.book().genre(),
                    faker.dune().quote(),
                    ZonedDateTime.now(ZoneId.of("-3")));

            this.save(news);

        }
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

    if (size > this.listNews.size()) {
        throw new IndexOutOfBoundsException("Size > The current size");
    }

    // Return the last "size" inside of unmodifiable list
            return Collections.unmodifiableList(
              this.listNews.subList(this.listNews.size()-size, this.listNews.size())
      );

  }

    /**
     * Save one news to the system.
     *
     * @param news to save.
     */
    @Override
    public void save(final News news) {

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

        //Insert into the end of the list
        this.listNews.add(news);

        //sort the list by publishedAt
        Collections.sort(this.listNews,
                (a, b) -> b.getPublishedAt().compareTo(a.getPublishedAt()));
    }
}
