package xyz.xfans.xchat.android.app.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import xyz.xfans.xchat.android.app.R;
import xyz.xfans.xchat.android.app.app.BaseActivity;

/**
 * Created by zhu on 2015/8/4.
 */
public class ChatActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }
}
