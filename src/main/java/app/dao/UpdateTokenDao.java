package app.dao;

import app.database.DBOperation;

public class UpdateTokenDao {
    public UpdateTokenDao() {
    }

    public String updateToken(String oldToken) {
        try {
            return DBOperation.updateToken(oldToken);
        } catch (Exception ignored) {
        }
        return null;
    }
}
