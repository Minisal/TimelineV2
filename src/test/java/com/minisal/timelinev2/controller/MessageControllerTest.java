package com.minisal.timelinev2.controller;

import com.minisal.timelinev2.service.ApplicationService;
import com.minisal.timelinev2.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ApplicationService applicationService;
    @MockBean
    MessageService messageService;

    MessageController messageController;

    @Test
    void shouldGetAllApplications() throws Exception{

        ResultActions perform = mockMvc.perform(get("/applications"));
        perform.andExpect(status().isOk());
        verify(applicationService, times(1)).listApplications();
    }

    @Test
    void shouldGetAllMessages() throws Exception{

        ResultActions perform = mockMvc.perform(get("/messages"));
        perform.andExpect(status().isOk());
        verify(messageService, times(1)).listMessages();
    }

    @Test
    void shouldGetNowMessages() throws Exception{

        ResultActions perform = mockMvc.perform(get("/getNow"));
        perform.andExpect(status().isOk());
        verify(messageService, times(1)).getNow();
    }

    @Test
    void shouldGetNewMessages() throws Exception{
        long id = 3;
        ResultActions perform = mockMvc.perform(get("/getNew?id="+id));
        perform.andExpect(status().isOk());
        verify(messageService, times(1)).getNew(id);
    }

    @Test
    void shouldGetOldMessages() throws Exception{
        long id = 3;
        ResultActions perform = mockMvc.perform(get("/getOld?id="+id));
        perform.andExpect(status().isOk());
        verify(messageService, times(1)).getOld(id);
    }
}
