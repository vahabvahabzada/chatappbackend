package com.vlachat.app.services;

import java.util.LinkedList;
import java.util.List;

import com.vlachat.app.daos.NewestMessagesDao;
import com.vlachat.app.dtos.GetNewestMessagesDto;
import com.vlachat.app.dtos.MessageDto;
import com.vlachat.app.entities.Message;
import com.vlachat.app.mappers.MessageMapper;

public class NewestMessagesService {
    private final NewestMessagesDao newestMessagesDao;
    public NewestMessagesService(NewestMessagesDao newestMessagesDao){
        this.newestMessagesDao=newestMessagesDao;
    }
    public List<MessageDto> getNewestMessages(GetNewestMessagesDto dto){
        List<Message> messages=newestMessagesDao.getNewestMessages(dto.getFrom(),dto.getTo(),dto.getBound());
        List<MessageDto> results=new LinkedList<>();
        MessageMapper mapper=new MessageMapper();
        for(Message message:messages){
            results.add(mapper.entityToDto(message));
        }
        return results;
    }
}// N-3
