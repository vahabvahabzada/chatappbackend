package app.services;

import app.dao.UpdateTokenDao;

public class UpdateTokenService {
    private final UpdateTokenDao daoUpdToken;

    public UpdateTokenService(UpdateTokenDao daoUpdToken) {
        this.daoUpdToken = daoUpdToken;
    }

    public String updateToken(String oldToken) {
        return daoUpdToken.updateToken(oldToken);
    }
}
