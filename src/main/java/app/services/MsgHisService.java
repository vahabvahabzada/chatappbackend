package app.services;

import java.util.List;

import app.dao.MsgHisDAO;
import app.entities.Message;

public class MsgHisService {
    private final MsgHisDAO daoMsgHis;

    public MsgHisService(MsgHisDAO daoMsgHis) {
        this.daoMsgHis = daoMsgHis;
    }

    public List</* String */Message> getMessageHistory(String kimden, String kime) {
        return daoMsgHis.getMessageHistory(kimden, kime);
    }

    public String getUserName(String token) {
        return daoMsgHis.getUserName(token);
    }
}
