/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;


/**
 * The class.
 *
 * @author Rodolfo Bollmann Checura
 */
public final class TestNews {
    /**
     * Testing the constructor.
     */
    @Test
    public void testConstructor() {

    //Test: valid data
    {
      News news =
          new News(
              "The Title",
              "The Source",
              "The Author",
              "The Url",
              "The Image Url",
              "The Description",
              "The Content",
              // FIXME: Check the current zone in Chile
              ZonedDateTime.now(ZoneId.of("-3")));

      // Testing the internal class
      Assertions.assertNotNull(news.getId());
      Assertions.assertEquals("The Title", news.getTitle());
      Assertions.assertEquals("The Source", news.getSource());
      Assertions.assertEquals("The Author", news.getAuthor());
      Assertions.assertEquals("The Url", news.getUrl());
      Assertions.assertEquals("The Image Url", news.getUrlImage());
      Assertions.assertEquals("The Description", news.getDescription());
      Assertions.assertEquals("The Content", news.getContent());
      Assertions.assertNotNull(news.getPublishedAt());

    }

    //Test: no valid data
    {
        //Illegal data
        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            //PublishedAt
            new News(
                    null,
                    "The Source",
                    "The Author",
                    "The Url",
                    "The Image Url",
                    "The Description",
                    "The Content",
                    null);

            //Source null
            new News(
                    "The Title",
                    null,
                    "The Author",
                    "The Url",
                    "The Image Url",
                    "The Description",
                    "The Content",
                    ZonedDateTime.now(ZoneId.of("-3")));

            //Source size <= 4
            new News(
                    "The Title",
                    "The",
                    "The Author",
                    "The Url",
                    "The Image Url",
                    "The Description",
                    "The Content",
                    ZonedDateTime.now(ZoneId.of("-3")));
        });
        // title validation
        News news = new News(
                null,
                "The Source",
                "The Author",
                "The Url",
                "The Image Url",
                "The Description",
                "The Content",
                ZonedDateTime.now(ZoneId.of("-3")));

        Assertions.assertEquals("No Title", news.getTitle());
    }

 }
}
