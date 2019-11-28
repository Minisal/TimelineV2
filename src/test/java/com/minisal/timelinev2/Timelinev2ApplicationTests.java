package com.minisal.timelinev2;

import com.minisal.timelinev2.entity.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class Timelinev2ApplicationTests {

	@Test
	void contextLoads() {
		Timelinev2Application app = new Timelinev2Application();
		assertNotNull(app);
	}
}
