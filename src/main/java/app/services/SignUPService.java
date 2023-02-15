package app.services;

import app.dao.SignUpDAO;
import app.entities.User;

public class SignUPService {
    private final SignUpDAO daoSignup;

    public SignUPService(SignUpDAO daoSignup) {
        this.daoSignup = daoSignup;
    }

    public /* void */boolean addUserToDB(User user) {
        return daoSignup.addUserToDB(user);
    }
}
// Step-5