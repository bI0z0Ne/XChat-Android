package xyz.xfans.xchat.android.app.service;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends IoHandlerAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void sessionOpened(IoSession session) throws Exception {

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