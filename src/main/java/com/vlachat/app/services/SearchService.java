package com.vlachat.app.services;

import java.util.LinkedList;
import java.util.List;

import com.vlachat.app.daos.SearchDao;
import com.vlachat.app.dtos.UserDto;
import com.vlachat.app.entities.User;
import com.vlachat.app.exceptions.CannotBeEmptyException;
import com.vlachat.app.mappers.UserMapper;

public class SearchService {
    private final SearchDao searchDao;
    public SearchService(SearchDao searchDao){
        this.searchDao=searchDao;
    }

    public List<UserDto> searchUser(String searchText){
        if(searchText==null){
            throw new CannotBeEmptyException("Search text can not be null!");
        }
        UserMapper mapper=new UserMapper();
        List<User> users=searchDao.searchUser(searchText);
        List<UserDto> results=new LinkedList<>();
        for(User user:users){
            results.add(mapper.entityToDto(user));
        }
        return results;
    }
}
// S-3