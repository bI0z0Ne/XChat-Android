package xyz.xfans.xchat.android.app.message;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * Created by xfans on 2015/7/15.
 */
public class MsgCodecFactory implements ProtocolCodecFactory {
    private final static Charset charset = Charset.forName("UTF-8");
    private MsgDecoder decoder;
    private MsgEncoder encoder;

    public MsgCodecFactory() {
        decoder = new MsgDecoder(charset);
        encoder = new MsgEncoder(charset);
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }
}
