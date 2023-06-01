package com.vlachat.app.dtos;

public class SenderAndReceiverDto {
    private String sender;
    private String receiver;

    public SenderAndReceiverDto(String sender,String receiver){
        this.sender=sender;
        this.receiver=receiver;
    }
    public void setSender(String sender){
        this.sender=sender;
    }
    public String getSender(){
        return sender;
    }
    public void setReceiver(String receiver){
        this.receiver=receiver;
    }
    public String getReceiver(){
        return receiver;
    }
}
// L-1