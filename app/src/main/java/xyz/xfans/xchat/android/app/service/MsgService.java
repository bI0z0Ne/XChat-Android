package xyz.xfans.xchat.android.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import xyz.xfans.xchat.android.app.message.MsgCodecFactory;

import java.net.InetSocketAddress;

public class MsgService extends Service {
    public MsgService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IoConnector connector=new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(
                        new MsgCodecFactory()
                )
        );
        connector.setHandler(new ClientHandler());
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress("127.0.0.1", 6608));
        connectFuture.awaitUninterruptibly();
        IoSession session = connectFuture.getSession();
    }
}
