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

        News news = new News(
                "The Title",
                "The Source",
                "The Author",
                "The Url",
                "The Image Url",
                "The Description",
                "The Content",
                //FIXME: Check the current zone in Chile
                ZonedDateTime.now(ZoneId.of("-3")));

        //Testing the internal class
        Assertions.assertEquals("The Title",news.getTitle());

        // TODO: Complete the tests.

    }
}
