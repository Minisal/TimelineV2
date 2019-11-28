package com.minisal.timelinev2.service;
import com.minisal.timelinev2.entity.Application;
import com.minisal.timelinev2.exception.ApplicationNotFoundException;
import com.minisal.timelinev2.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<Application> listApplications(){
        return (List<Application>)applicationRepository.findAll();
    }

    @Override
    public Application findApplication(long id) throws ApplicationNotFoundException {
        Optional<Application> optionalApplication = applicationRepository.findById(id);

        if (optionalApplication.isPresent())
            return optionalApplication.get();
        else
            throw new ApplicationNotFoundException("Application not found");
    }
}


