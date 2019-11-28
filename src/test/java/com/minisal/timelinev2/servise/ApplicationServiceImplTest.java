package com.minisal.timelinev2.servise;
import com.minisal.timelinev2.entity.Application;
import com.minisal.timelinev2.entity.Message;
import com.minisal.timelinev2.exception.ApplicationNotFoundException;
import com.minisal.timelinev2.exception.MessageNotFoundException;
import com.minisal.timelinev2.repository.ApplicationRepository;
import com.minisal.timelinev2.service.ApplicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ApplicationServiceImplTest {
    @Autowired
    private ApplicationServiceImpl service;

    @MockBean
    private ApplicationRepository applicationRepository;

    private Application new_feature_application;

    private List<Application> applications;

    @BeforeEach
    public void init() {
        new_feature_application = new Application();
        new_feature_application.setDescription("New feature Application");
        new_feature_application.setName("CSS");
        new_feature_application.setOwner("Haiying");

        applications = new ArrayList<>();
        applications.add(new_feature_application);
    }

    @Test
    public void shouldlistApplications() {

        when(applicationRepository.findAll()).thenReturn(applications);
        List<Application> applicationList = service.listApplications();
        verify(applicationRepository,times(1)).findAll();
    }

    @Test
    public void should_find_application_by_id() throws ApplicationNotFoundException {
        long id = 1;
        when(applicationRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(new_feature_application));
        Application app = service.findApplication(id);
        verify(applicationRepository,times(1)).findById(id);
    }
}
