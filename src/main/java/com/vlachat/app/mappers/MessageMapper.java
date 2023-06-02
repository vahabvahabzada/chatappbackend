package com.vlachat.app.mappers;

import com.vlachat.app.daos.MapperDao;
import com.vlachat.app.dtos.MessageDto;
import com.vlachat.app.entities.Message;

public class MessageMapper {
    public Message dtoToEntity(MessageDto messageDto){
        Message message=new Message(messageDto.getFrom(), messageDto.getTo(), messageDto.getBody());
        MapperDao mapperDao=new MapperDao();
        message.setUser(mapperDao.getUser(message.getFrom()));
        return message;
    }

    public MessageDto entityToDto(Message message){
        if(message.getUser()==null){
            System.out.println("From Message Mapper : "+message.getUser().getName());
            return new MessageDto(message.getFrom(), message.getTo(), message.getBody());
        }
        UserMapper userMapper=new UserMapper();
        return new MessageDto(message.getFrom(), message.getTo(), message.getBody(), userMapper.entityToDto(message.getUser()));// NF-3
    }
}
