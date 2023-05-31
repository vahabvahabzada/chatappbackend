package com.vlachat.app.security;

import org.mindrot.jbcrypt.BCrypt;

public class PassWordHasher {
    public String encode(String password){
        
        String salt=BCrypt.gensalt();
        String hashedPassword=BCrypt.hashpw(password, salt);

        return hashedPassword;
    }

    public boolean validate(String entered,String originalHashedPassword){
        return BCrypt.checkpw(entered, originalHashedPassword);
    }
}