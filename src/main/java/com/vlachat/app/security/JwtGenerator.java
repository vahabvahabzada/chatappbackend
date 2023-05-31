package com.vlachat.app.security;

import java.util.Date;

import com.vlachat.app.exceptions.CredentialsNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtGenerator {
    public String generateToken(String username){
        String token=Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime()+SecurityConstants.JWT_EXP_TIME))
        .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
        .compact();

        return token;
    }
    
    public boolean validate(String token){
        try{
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        }catch(Exception ex){
            throw new CredentialsNotFoundException("Credentials not found");
        }
    }
}