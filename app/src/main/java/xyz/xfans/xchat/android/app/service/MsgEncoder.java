package xyz.xfans.xchat.android.app.service;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/**
 * Created by xfans on 2015/7/15.
 */
public class MsgEncoder extends ProtocolEncoderAdapter {
    private Charset charset;

    public MsgEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        MsgBody msgBody = (MsgBody) message;
        IoBuffer buf = IoBuffer.allocate(MsgBody.DEFAULT_MSG_BODY_LENGTH).setAutoExpand(true);
        int length = msgBody.getMsgContent().getBytes(charset).length + MsgBody.DEFAULT_MSG_BODY_LENGTH;
        buf.putInt(length);
        buf.putInt(msgBody.getMsgVersion());
        buf.putInt(msgBody.getMsgCmd());
        buf.putString(msgBody.getMsgToken(), charset.newEncoder());
        buf.putString(msgBody.getMsgContent(), charset.newEncoder());
        buf.flip();
        out.write(buf);
    }
}
