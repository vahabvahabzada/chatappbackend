package com.vlachat.app.dtos;


public class GetNewestMessagesDto {
    private String from;
    private String to;
    private long bound;

    public GetNewestMessagesDto(String from,String to,long bound){// burada bound artiq fronta-getmis messages arrayinin olcusudur ki,ancaq yeni mesajlari ceksin
        this.from=from;
        this.to=to;
        this.bound=bound;       
    }

    public void setBound(long bound){
        this.bound=bound;
    }
    public long getBound(){
        return bound;
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
}
