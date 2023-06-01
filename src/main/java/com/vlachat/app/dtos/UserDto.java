package com.vlachat.app.dtos;

public class UserDto {
    private String name;
    private String password;

    public UserDto(String name,String password){
        this.name=name;
        this.password=password;
    }

    public UserDto(String name){// sonradan bura,profil sekli ve.s de elave ede bilerik
        this.name=name;
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

    @Override
    public String toString() {
        String str="{";
        if(name!=null){
            str+=("\"name\":"+name);
        }
        if(password!=null){
            if(!str.equals("{")){
                str+=",";
            }
            str+=("\"password\":"+password);
        }
        str+="}";

        return str;
    }
}
