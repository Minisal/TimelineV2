package com.minisal.timelinev2;

import com.minisal.timelinev2.entity.Message;
import com.minisal.timelinev2.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Timelinev2Application {

	private static final Logger log = LoggerFactory.getLogger(Timelinev2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Timelinev2Application.class, args);
	}

}
