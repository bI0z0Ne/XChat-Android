package xyz.xfans.xchat.android.app.service;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.xfans.xchat.android.app.message.CmdType;
import xyz.xfans.xchat.android.app.message.MsgBody;
import xyz.xfans.xchat.android.app.message.MsgContent;
import xyz.xfans.xchat.android.app.utils.Convert;

public class ClientHandler extends IoHandlerAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void sessionOpened(IoSession session) throws Exception {
//        System.out.println("sessionOpened" + session.getRemoteAddress());
        MsgContent msgContent = new MsgContent();
        msgContent.setContent("hello");
        String msgStr = Convert.ObjectToJson(msgContent);
        MsgBody msgBody = getMessageBody(msgStr);
        session.write(msgBody);
    }

    private MsgBody getMessageBody(String msgStr) {

        MsgBody msgBody = new MsgBody();
        msgBody.setMsgVersion(1);
        msgBody.setMsgCmd(CmdType.SOCKET_AUTH);
        msgBody.setMsgToken("1d0185e2d29b36fa7f54c6dd45b5c3bb");
        msgBody.setMsgContent(msgStr);
        return msgBody;
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
//        System.out.println("ClientHandler.exceptionCaught");
//        MsgBody msgBody = getMessageBody("exceptionCaught");
//        session.write(msgBody);
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        MsgBody msgBody = (MsgBody) message;
        System.out.println("Received:" +  msgBody.getMsgContent());
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
//        System.out.println("ClientHandler.sessionClosed");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
//        System.out.println("ClientHandler.sessionCreated");
//        MsgBody msgBody = getMessageBody("sessionCreated");
//        session.write(msgBody);
    }
}