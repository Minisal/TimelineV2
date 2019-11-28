package com.minisal.timelinev2.controller;

import com.minisal.timelinev2.entity.Message;
import com.minisal.timelinev2.exception.MessageNotFoundException;
import com.minisal.timelinev2.service.ApplicationService;
import com.minisal.timelinev2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    private ApplicationService applicationService;
    private MessageService messageService;

    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Autowired
    public void setMessageService(MessageService messageService){
        this.messageService = messageService;
    }


    @GetMapping("/applications")
    public String retrieveApplications(Model model){
        model.addAttribute("applications", applicationService.listApplications());
        return "applications";
    }

    @GetMapping("/messages")
    public List<Message> retrieveMessages(Model model){
        return this.messageService.listMessages();
    }

    @GetMapping("/getNow")
    public List<Message> retrieveNowMessages(Model model) throws MessageNotFoundException {
        return this.messageService.getNow();
    }
    @GetMapping("/getNew")
    public List<Message> retrieveNewTickets(@RequestParam long id) throws MessageNotFoundException {
        return this.messageService.getNew(id);
    }
    @GetMapping("/getOld")
    public List<Message> retrieveOldTickets(@RequestParam long id) throws MessageNotFoundException{
        return this.messageService.getOld(id);
    }

}
