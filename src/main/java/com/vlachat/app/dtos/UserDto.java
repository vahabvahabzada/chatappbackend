package com.vlachat.app.dtos;

public class UserDto {
    private String name;
    private String password;

    public UserDto(String name,String password){
        this.name=name;
        this.password=password;
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
