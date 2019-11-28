package com.minisal.timelinev2.repository;

import com.minisal.timelinev2.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByTitle(String title);
    Optional<Message> findById(long Id);
    List<Message> findByIdLessThan(long id);
    List<Message> findByIdGreaterThan(long id);
    List<Message> findByAddTimeGreaterThanAndAddTimeLessThan(Calendar greaterThanAddTime,Calendar lessThanAddTime);
    void deleteAll();
}
