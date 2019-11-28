package com.minisal.timelinev2.repository;
import com.minisal.timelinev2.entity.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext
public class ApplicationRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ApplicationRepository repo;

    private Application new_feature_application;

    @BeforeEach
    public void init() {
        new_feature_application = new Application.ApplicationBuilder().description("New feature Application")
                .name("CSS")
                .owner("Haiying")
                .build();
    }
    @AfterEach
    public void cleanup() {
        this.entityManager.clear();
    }

    @Test
    public void testFindAllApplications() {

        this.entityManager.persist(new_feature_application);
        Iterable<Application> applications = repo.findAll();

        int count = 0;
        for (Application application : applications) {
            assertEquals("New feature Application", application.getDescription());
            assertEquals("CSS", application.getName());
            assertEquals("Haiying",application.getOwner());
            System.out.println(application.toString());
            count++;
        }
        assertEquals(1, count);
    }
}

