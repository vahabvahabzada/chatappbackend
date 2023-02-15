package app.dao;

import java.util.List;

import app.database.DBOperation;

public class SearchDAO {
    public SearchDAO() {
    }

    public List<String> searchUsers(String searchText) {
        try {
            return DBOperation.searchUsers(searchText);
        } catch (Exception ignored) {
        }
        return null;
    }
}
