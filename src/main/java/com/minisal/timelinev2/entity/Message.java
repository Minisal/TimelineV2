package com.minisal.timelinev2.entity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "content", length = 2000)
    private String content;

    @Column(name = "user", length = 2000)
    private String user;

    @Column(name = "addTime")
    private Calendar addTime;

    public Message() { }

    public Message(String title, String content, String user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.addTime = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAddTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(this.addTime.getTime());
    }

    public String setAddTime(String addTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date =format.parse(addTime);
            this.addTime.setTime(date);
            return addTime;

    }

    public static class MessageBuilder{
        private String title;
        private String content;
        private String user;

        public Message.MessageBuilder title(String title){
            this.title = title;
            return this;
        }

        public Message.MessageBuilder content(String content){
            this.content = content;
            return this;
        }

        public Message.MessageBuilder user(String user){
            this.user = user;
            return this;
        }

        public Message build(){
            return new Message(title,content,user);
        }

    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addTimeStr = format.format(this.addTime.getTime());
        return "message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", addTime='" + addTimeStr + '\'' +
                '}';
    }
}
