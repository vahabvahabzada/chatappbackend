package com.vlachat.app.dtos;


public class MessageDto {
    private String from;
    private String to;
    private String body;
    private UserDto user;

    public MessageDto(String from,String to,String body){
        this.from=from;
        this.to=to;
        this.body=body;
    }

    public MessageDto(String from,String to,String body,UserDto user){
        this.from=from;
        this.to=to;
        this.body=body;
        this.user=user;
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

    public void setUser(UserDto user){
        this.user=user;
    }

    public UserDto getUser(){
        return user;
    }

    @Override
    public String toString() {
        if(this.user==null){
            return "{\"from\":\""+from+"\",\"to\":\""+to+"\",\"body\":\""+body+"\"}";
        }
        else{
            return "{\"from\":\""+from+"\",\"to\":\""+to+"\",\"body\":\""+body+"\",\"user\":"+this.user.toString()+"}";
        }
    }
}
