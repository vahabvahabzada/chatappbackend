package app.services;

import java.util.List;

import app.dao.SearchDAO;

public class SearchService {
    private final SearchDAO daoSearch;

    public SearchService(SearchDAO daoSearch) {
        this.daoSearch = daoSearch;
    }

    public List<String> searchUsers(String searchtext) {
        return daoSearch.searchUsers(searchtext);
    }
}
