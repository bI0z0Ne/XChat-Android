package xyz.xfans.xchat.android.app.message;

/**
 * Created by xfans on 2015/7/16
 *
 */
public class CmdType {
    public static final String DEFAULT_TOKEN = "00000000000000000000000000000000";
    /**
     * socket 报文
     */
    public static final int SOCKET_AUTH = 0x00000001;// 发送聊天
    public static final int SOCKET_AUTH_RSP = 0x0F000001;// 发送聊天响应
    public static final int SOCKET_SUBMIT = 0x00000002;// 发送聊天
    public static final int SOCKET_SUBMIT_RSP = 0x0F000002;// 发送聊天响应
    public static final int SOCKET_HEART = 0x00000003;// 心跳
    public static final int SOCKET_HEART_RSP = 0x0F000003;// 心跳响应
    public static final int SOCKET_EXIT = 0x00000004;// 强制用户下线
    public static final int SOCKET_EXIT_RSP = 0x0F000004;// 强制用户下线响应

    /**
     * http 接口
     */
    public static final String HTTP_LOGIN = "login"; // 登陆
    public static final String HTTP_LOGOUT = "logout"; // 注销
    public static final String HTTP_FRIEND = "FRIEND"; // 获取好友



}
