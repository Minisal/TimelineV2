package com.minisal.timelinev2.exception;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApplicationNotFoundExceptionTest {
    @Test
    public void use_expect_exception_object_to_assert_exception() throws ApplicationNotFoundException {
        Exception ex = new ApplicationNotFoundException("Application Not Found");
        assertEquals("Application Not Found",ex.getMessage());
    }

    @Test
    public void check_message_not_found_exception() throws MessageNotFoundException {
        Exception ex = new MessageNotFoundException("Message Not Found");
        assertEquals("Message Not Found",ex.getMessage());
    }
}
