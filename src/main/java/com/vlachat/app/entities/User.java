package com.vlachat.app.entities;

public class User {
    private long id;
    private String name;
    private String password;

    public User(String name,String password){
        this.name=name;
        this.password=password;
    }

    public void setId(long id){
        this.id=id;
    }

    public long getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
