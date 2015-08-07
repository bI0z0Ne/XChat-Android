package xyz.xfans.xchat.android.app.service;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

/**
 * Created by xfans on 2015/7/15.
 */
public class MsgDecoder extends CumulativeProtocolDecoder {
    private Charset charset;

    public MsgDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
//        if (in.remaining() > MsgBody.DEFAULT_MSG_LENGTH) {
//            in.mark();
//            int msgLength = in.getInt();
//            int inSize = in.remaining();
//            if (msgLength > inSize + MsgBody.DEFAULT_MSG_LENGTH) {
//                in.reset();
//                return false;
//            } else {
//                int version = in.getInt();
//                int cmd = in.getInt();
//                String token = in.getString(MsgBody.DEFAULT_MSG_TOKEN, charset.newDecoder());
//                String msgStr = in.getString(msgLength - MsgBody.DEFAULT_MSG_BODY_LENGTH, charset.newDecoder());
////                System.out.println("msgLength = " + msgLength + " version = " + version + " cmd = " + cmd + " token = " + token + " msgStr = " + msgStr);
//                MsgBody msgBody = new MsgBody();
//                msgBody.setMsgLength(msgLength);
//                msgBody.setMsgVersion(version);
//                msgBody.setMsgCmd(cmd);
//                msgBody.setMsgToken(token);
//                msgBody.setMsgContent(msgStr);
//                out.write(msgBody);
//                return true;
//            }
//        }
        return false;
    }
}
