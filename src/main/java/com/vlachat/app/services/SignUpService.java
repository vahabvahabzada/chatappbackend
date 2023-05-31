package com.vlachat.app.services;

import com.vlachat.app.daos.SignUpDao;
import com.vlachat.app.dtos.UserDto;
import com.vlachat.app.entities.User;
import com.vlachat.app.mappers.UserMapper;
import com.vlachat.app.security.PassWordHasher;

public class SignUpService {
    private final SignUpDao daoSignup;
    private PassWordHasher passWordHasher;

    public SignUpService(SignUpDao daoSignup, PassWordHasher passWordHasher) {
        this.daoSignup = daoSignup;
        this.passWordHasher = passWordHasher;
    }

    public boolean addUserToDB(UserDto userDto) {
        UserMapper mapper=new UserMapper();
        User user=mapper.dtoToEntity(userDto);
        user.setPassword(passWordHasher.encode(user.getPassword()));
        //user.setPassword(passWordHasher.encode(user.getPassword()));
        return daoSignup.addUserToDB(user);
    }
}
