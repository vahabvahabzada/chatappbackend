package com.vlachat.app.mappers;

import com.vlachat.app.dtos.UserDto;
import com.vlachat.app.entities.User;

public class UserMapper {
    public User dtoToEntity(UserDto userDto){
        User user=new User(userDto.getName(), userDto.getPassword());
        return user;
    }

    public UserDto entityToDto(User user){
        UserDto userDto=new UserDto(user.getName(), user.getPassword());
        return userDto;
    }
}
