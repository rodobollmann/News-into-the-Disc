/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.service;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import cl.ucn.disc.dsm.rodobollmann.BaseTest;
import cl.ucn.disc.dsm.rodobollmann.model.News;

public final class TestContracts extends BaseTest {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(TestContracts.class);

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

        //The contracts implementation
        Contracts contracts = new ContractsImpFaker();

        //The Faker
        Faker faker = new Faker();

        // 1 News
        News news =
                new News(
                        faker.book().title(),
                        faker.book().publisher(),
                        faker.book().author(),
                        faker.internet().url(),
                        faker.internet().url(),
                        faker.book().genre(),
                        faker.dune().quote(),
                        ZonedDateTime.now(ZoneId.of("-3")));

        log.info("TheNews: {}.", toString(news));

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
