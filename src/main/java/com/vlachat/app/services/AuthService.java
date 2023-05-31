package com.vlachat.app.services;

import com.vlachat.app.daos.AuthDao;
import com.vlachat.app.entities.User;
import com.vlachat.app.security.PassWordHasher;

public class AuthService {
    private final AuthDao daoAuth;
    private PassWordHasher passWordHasher;

    public AuthService(AuthDao daoAuth,PassWordHasher passWordHasher) {
        this.daoAuth = daoAuth;
        this.passWordHasher=passWordHasher;
    }

    public boolean login(User user){
        if(daoAuth.getUser(user.getName())==null){
            return false;
        }// demeli hele hec bele istifadeci yoxsu umumiyyetle,ona gore password-u yoxlamaga deymez

        return passWordHasher.validate(user.getPassword(), daoAuth.getUser(user.getName()).getPassword());
    }
}