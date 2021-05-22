/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import cl.ucn.disc.dsm.rodobollmann.model.News;

public final class TestContracts {

    /**
     * Testing the constructor.
     */

    @Test

    public void testConstructor(){

        //call the constructor
        Contracts contracts = new ContractsImpFaker();
        Assertions.assertNotNull(contracts, "Contracts null!");
    }

    @Test

    public void TestSave(){

        Contracts contracts = new ContractsImpFaker();

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

        //Save into the backend
        contracts.save(news);

        //WTF?
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        contracts.save(null);
        });

        //Verify the size of the list
        Assertions.assertEquals(1, contracts.retrieveNews(10).size());

        //Save into the backend
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contracts.save(news);
        });

    }


}
