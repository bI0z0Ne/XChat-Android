package xyz.xfans.xchat.android.app.app;

import android.app.Application;
import xyz.xfans.xchat.android.app.entity.UserInfo;

/**
 * Created by zhu on 2015/8/4.
 */
public class BaseApp extends Application {
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("BaseApp.onCreate");
    }
}
