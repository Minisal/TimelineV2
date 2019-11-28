package com.minisal.timelinev2.service;
import com.minisal.timelinev2.entity.Application;
import com.minisal.timelinev2.exception.ApplicationNotFoundException;

import java.util.List;

public interface ApplicationService {
    List<Application> listApplications();
    Application findApplication(long id) throws ApplicationNotFoundException;
}
