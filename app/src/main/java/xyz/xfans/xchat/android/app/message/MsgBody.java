package xyz.xfans.xchat.android.app.message;

/**
 * Created by xfans on 2015/7/19.
 * <p/>
 * 报文格式 Message format
 * 0000         0000   0000  00000000000000000000000000000000
 * 消息长度     版本   命令  tocken
 */
public class MsgBody {
    public static final int DEFAULT_MSG_LENGTH = 4;
    public static final int DEFAULT_MSG_VERSION = 4;
    public static final int DEFAULT_MSG_CMD = 4;
    public static final int DEFAULT_MSG_TOKEN = 32;
    public static final int DEFAULT_MSG_BODY_LENGTH = DEFAULT_MSG_LENGTH + DEFAULT_MSG_VERSION + DEFAULT_MSG_CMD + DEFAULT_MSG_TOKEN;
    private int msgLength;
    private int msgVersion = 1;
    private int msgCmd;
    private String msgToken;
    private String msgContent;

    public int getMsgLength() {
        return msgLength;
    }

    public void setMsgLength(int msgLength) {
        this.msgLength = msgLength;
    }

    public int getMsgVersion() {
        return msgVersion;
    }

    public void setMsgVersion(int msgVersion) {
        this.msgVersion = msgVersion;
    }

    public int getMsgCmd() {
        return msgCmd;
    }

    public void setMsgCmd(int msgCmd) {
        this.msgCmd = msgCmd;
    }

    public String getMsgToken() {
        return msgToken;
    }

    public void setMsgToken(String msgToken) {
        this.msgToken = msgToken;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
