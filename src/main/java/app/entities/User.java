package app.entities;

public class User {
    private String username;
    private String password;
    private String token;
    private String tokenExpTime;

    public User(String username, String password, String token, String tokenExpTime) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.tokenExpTime = tokenExpTime;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getUserName() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setExpTime(String tokenExpTime) {
        this.tokenExpTime = tokenExpTime;
    }

    public String getExpTime() {
        return this.tokenExpTime;
    }
}
// Step-2