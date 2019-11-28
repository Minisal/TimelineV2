package com.minisal.timelinev2.exception;

public class MessageNotFoundException extends Exception{
    String message = null;
    public MessageNotFoundException(String message){
        this.message = message;
        System.out.println(message);
    }
    public String getMessage(){
        return this.message;
    }
}
