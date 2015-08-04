package xyz.xfans.xchat.android.app.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONObject;
import xyz.xfans.xchat.android.app.R;
import xyz.xfans.xchat.android.app.config.UrlConfig;
import xyz.xfans.xchat.android.app.entity.UserInfo;
import xyz.xfans.xchat.android.app.ui.activity.ChatActivity;
import xyz.xfans.xchat.android.app.ui.adapter.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    private CustomAdapter mAdapter;
    private List<UserInfo> mLists;
    private View view;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static FriendsFragment newInstance() {
        FriendsFragment fragment = new FriendsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();

    }

    private void getData() {
        //TODO 获取好友
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(getActivity(), UrlConfig.GETFIRENDS, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resp = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(resp);
                    int status = jsonObject.getInt("status");
                    Gson gson = new Gson();
                    String listStr = jsonObject.getString("data");
                    List<UserInfo> lists = gson.fromJson(listStr, new TypeToken<List<UserInfo>>() {
                    }.getType());
                    if (lists != null) {
                        mLists.clear();
                        mLists.addAll(lists);
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("LoginActivity.onSuccess:" + resp);
                swipeRefreshLayout.setRefreshing(false);
                Snackbar.make(view, "获取好友列表成功", Snackbar.LENGTH_LONG)
                        .show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("LoginActivity.onFailure:" + error.getMessage());
                swipeRefreshLayout.setRefreshing(false);
                Snackbar.make(view, "获取好友列表失败", Snackbar.LENGTH_LONG)
                        .show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_friends, container, false);;
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mLists = new ArrayList<UserInfo>();
        mAdapter = new CustomAdapter(mLists);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new CustomAdapter.OnClickListener() {
            @Override
            public void OnItemClickListener(View v, int position) {
                Intent intent= new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


}
