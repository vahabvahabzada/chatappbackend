package app.dao;

import app.database.DBOperation;
import app.entities.User;

public class SignUpDAO {
    public SignUpDAO() {
    }

    public /* void */boolean addUserToDB(User user) {
        try {
            return DBOperation.addUserToDB(user);
        } catch (Exception ignored) {
        }
        return false;
    }
}
// Step-5