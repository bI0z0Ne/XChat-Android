package xyz.xfans.xchat.android.app.message;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.xfans.msg.server.entity.UserInfo;
import xyz.xfans.msg.server.utils.Convert;

/**
 * Created by xfans on 2015/7/15.
 */
public class MsgHandler extends IoHandlerAdapter {
    private final static Logger log = LoggerFactory.getLogger(MsgHandler.class);
    private MsgManager msgManager;

    public void setMsgManager(MsgManager msgManager) {
        this.msgManager = msgManager;
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        MsgBody msgBody = (MsgBody) message;
        System.out.println("messageReceived:" +  msgBody.getMsgContent());
        //TODO authentication token
        int cmd = msgBody.getMsgCmd();
        switch (cmd){
            case CmdType.SOCKET_AUTH:
                auth(session, msgBody);
                break;
            case CmdType.SOCKET_SUBMIT:
                forward(session, msgBody);
                break;
            default:
        }
//        MsgContent msgContent = new MsgContent();
//        msgContent.setContent("welcome");
//        MsgBody msgBody1 = new MsgBody();
//        msgBody1.setMsgVersion(1);
//        msgBody1.setMsgCmd(CmdType.SOCKET_HEART);
//        msgBody1.setMsgToken(CmdType.DEFAULT_TOKEN);
//        msgBody1.setMsgContent(msgContent);
//        session.write(msgBody);
//        String msgStr = Convert.ObjectToJson(msgBody.getMsgContent());
//        pushAll(session, msgBody);
//        MsgContent msgContent = new MsgContent();
//        msgContent.setContent("welcome");
//        String msgStr = Convert.ObjectToJson(msgContent);
//        MsgBody msgBody1 = getMessageBody(msgStr);
//        session.write(msgBody1);
    }

//    private static MsgBody getMessageBody(String msgStr) {
//        MsgBody msgBody = new MsgBody();
//        msgBody.setMsgCmd(CmdType.SOCKET_HEART);
//        msgBody.setMsgToken(CmdType.DEFAULT_TOKEN);
//        msgBody.setMsgContent(msgStr);
//        return msgBody;
//    }

    /**
     * authentication
     * save online user
     * @param session
     * @param msgBody
     */
    private void auth(IoSession session, MsgBody msgBody) {
        String token = msgBody.getMsgToken();
        UserInfo userInfo = msgManager.getUser(token);
        if(userInfo != null){
            msgManager.putSession(userInfo.getId(), session);
            msgBody.setMsgCmd(CmdType.SOCKET_AUTH_RSP);
            System.out.println("MsgHandler.auth");
            session.write(msgBody);
        }

    }

    /**
     * forward to user
     * @param session
     * @param msgBody
     */
    private void forward(IoSession session, MsgBody msgBody) {
        MsgContent msgContent = Convert.JsonToObject(msgBody.getMsgContent(), MsgContent.class);
        String toUser = msgContent.getTo();
        IoSession session1 = msgManager.getSession(toUser);
        if(session1 != null){
            session1.write(msgBody);
            System.out.println("MsgHandler.forward");
        }else {
            //TODO write offline message
        }

    }

//    private void pushAll(IoSession session, MsgBody msgBody) {
//        Map<Long,IoSession> sessions = session.getService().getManagedSessions();
//        String str = msgBody.getMsgContent().getContent();
//        msgBody.getMsgContent().setContent(session.getId()+":"+str);
//        for (Map.Entry<Long,IoSession> s:sessions.entrySet()){
//            System.out.println("pushAll:"+s.getKey());
//            if(s.getKey() != session.getId()){
//                s.getValue().write(msgBody);
//            }
//        }
//    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        msgManager.removeSession(session);
        System.out.println("MsgHandler.sessionClosed");

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        msgManager.removeSession(session);
        System.out.println("exceptionCaught:" + cause.getMessage());
    }
}
