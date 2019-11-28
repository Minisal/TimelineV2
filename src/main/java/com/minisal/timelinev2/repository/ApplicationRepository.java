package com.minisal.timelinev2.repository;
import java.util.Optional;

import com.minisal.timelinev2.entity.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long>{
    Optional<Application> findById(long Id);
}

