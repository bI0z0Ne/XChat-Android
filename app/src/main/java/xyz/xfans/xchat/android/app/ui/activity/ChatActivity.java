package xyz.xfans.xchat.android.app.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import xyz.xfans.xchat.android.app.R;
import xyz.xfans.xchat.android.app.app.BaseActivity;
import xyz.xfans.xchat.android.app.entity.ChatInfo;
import xyz.xfans.xchat.android.app.ui.adapter.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhu on 2015/8/4.
 */
public class ChatActivity extends BaseActivity {
    private List<ChatInfo> mChatInfos = new ArrayList<ChatInfo>();
    private ChatAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar mToolbar;
    private TextView mSendBtn;
    private EditText mMsgEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSendBtn = (TextView) findViewById(R.id.tv_send);
        mMsgEditText = (EditText) findViewById(R.id.et_msg);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ChatAdapter(mChatInfos);
        mRecyclerView.setAdapter(mAdapter);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
