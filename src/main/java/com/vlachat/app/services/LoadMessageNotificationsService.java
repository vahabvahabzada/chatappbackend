package com.vlachat.app.services;

import java.util.LinkedList;
import java.util.List;

import com.vlachat.app.daos.LoadMessageNotificationsDao;
import com.vlachat.app.dtos.MessageDto;
import com.vlachat.app.entities.Message;
import com.vlachat.app.mappers.MessageMapper;

public class LoadMessageNotificationsService {
    private final LoadMessageNotificationsDao dao;
    public LoadMessageNotificationsService(LoadMessageNotificationsDao dao){
        this.dao=dao;
    }
    public List<MessageDto> loadMessageBoxes(String currentUser){
        MessageMapper mapper=new MessageMapper();
        List<Message> mesgs=dao.loadMessageBoxes(currentUser);
        List<MessageDto> messages=new LinkedList<>();
        for(Message message:mesgs){
            messages.add(mapper.entityToDto(message));
        }
        return messages;
    }
}// NF-3
