package xyz.xfans.xchat.android.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MsgService extends Service {
    public MsgService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
