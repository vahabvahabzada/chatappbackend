package com.vlachat.app.services;

import java.util.LinkedList;
import java.util.List;

import com.vlachat.app.daos.LoadChatDao;
import com.vlachat.app.dtos.MessageDto;
import com.vlachat.app.dtos.SenderAndReceiverDto;
import com.vlachat.app.entities.Message;
import com.vlachat.app.mappers.MessageMapper;

public class LoadChatService {
    private final LoadChatDao loadChatDao;
    public LoadChatService(LoadChatDao loadChatDao){
        this.loadChatDao=loadChatDao;
    }
    public List<MessageDto> getMessageHistory(SenderAndReceiverDto senderAndReceiverDto){
        MessageMapper mapper=new MessageMapper();
        List<Message> messages = loadChatDao.getMessages(senderAndReceiverDto.getSender(), senderAndReceiverDto.getReceiver());
        List<MessageDto> results=new LinkedList<>();
        for(Message message:messages){
            results.add(mapper.entityToDto(message));
        }
        return results;
    }
}// L-3
