package app.services;

import app.dao.AuthDAO;
import app.entities.User;

public class AuthService {
    private final AuthDAO daoAuth;

    public AuthService(AuthDAO daoAuth) {
        this.daoAuth = daoAuth;
    }

    public String putToken(String username) {
        return daoAuth.putToken(username);
    }

    public User getUser(String username) {
        return daoAuth.getUser(username);
    }
}
