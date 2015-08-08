package xyz.xfans.xchat.android.app.message;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.mina.core.session.IoSession;
import xyz.xfans.msg.server.dao.UserDao;
import xyz.xfans.msg.server.entity.UserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xfans on 2015/7/22.
 */

public class MsgManager {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * userId  session
     */
    private final BidiMap sessions = new DualHashBidiMap();

    /**
     * token userInfo
     */
    private final Map<String,UserInfo> users = new HashMap<String,UserInfo>();


    public UserInfo getUser(String token) {
        UserInfo user = userDao.getUserInfoByToken(token);
        return user;
    }

    public void putSession(String userId, IoSession session) {
        sessions.put(userId,session);
    }

    public IoSession getSession(String userId) {
        return (IoSession) sessions.get(userId);
    }

    public void removeSession(IoSession session) {
        System.out.println("MsgManager.removeSession"+session.getId());
        sessions.removeValue(session);
    }

    public Map<String, IoSession> getSessions() {
        return sessions;
    }
}
