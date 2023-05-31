package com.vlachat.app.services;

import com.vlachat.app.daos.SignUpDao;
import com.vlachat.app.entities.User;
import com.vlachat.app.security.PassWordHasher;

public class SignUpService {
    private final SignUpDao daoSignup;
    private PassWordHasher passWordHasher;

    public SignUpService(SignUpDao daoSignup, PassWordHasher passWordHasher) {
        this.daoSignup = daoSignup;
        this.passWordHasher = passWordHasher;
    }

    public boolean addUserToDB(User user) {
        user.setPassword(passWordHasher.encode(user.getPassword()));
        return daoSignup.addUserToDB(user);
    }
}
