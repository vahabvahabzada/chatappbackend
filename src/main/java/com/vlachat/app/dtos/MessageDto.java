package com.vlachat.app.dtos;

public class MessageDto {
    private String from;
    private String to;
    private String body;

    public MessageDto(String from,String to,String body){
        this.from=from;
        this.to=to;
        this.body=body;
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

    @Override
    public String toString() {
        return "{\"from\":\""+from+"\",\"to\":\""+to+"\",\"body\":\""+body+"\"}";
    }
}