package com.vlachat.app.entities;

public class Message {
    private long id;
    private String from;
    private String to;
    private String body;

    private User user;

    public Message(String from,String to,String body){
        this.from=from;
        this.to=to;
        this.body=body;
    }

    public Message(String from,String to,String body,User user){
        this.from=from;
        this.to=to;
        this.body=body;
        this.user=user;
    }

    public void setId(long id){
        this.id=id;
    }

    public long getId(){
        return id;
    }

    public void setFrom(String from){
        this.from=from;
    }

    public String getFrom(){
        return from;
    }

    public void setTo(String to){
        this.to=to;
    }

    public String getTo(){
        return to;
    }

    public void setBody(String body){
        this.body=body;
    }

    public String getBody(){
        return body;
    }

    public void setUser(User user){
        this.user=user;
    }

    public User getUser(){
        return user;
    }
    
}

// M-1