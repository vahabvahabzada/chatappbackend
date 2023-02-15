package app.services;

import java.util.List;

import app.dao.PartialMsgHisDAO;
import app.entities.Message;

public class PartialMsgHisService {
    private PartialMsgHisDAO daoPartialMsgHis;

    public PartialMsgHisService(PartialMsgHisDAO daoPartialMsgHis) {
        this.daoPartialMsgHis = daoPartialMsgHis;
    }

    public List</* String */Message> getPartialMessageHistory(String kimden, String kime, long latestmsgId) {
        return daoPartialMsgHis.getPartialMessageHistory(kimden, kime, latestmsgId);
    }

    public String getUserName(String token) {
        return daoPartialMsgHis.getUserName(token);
    }
}
