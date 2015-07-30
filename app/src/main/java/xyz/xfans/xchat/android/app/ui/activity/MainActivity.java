package xyz.xfans.xchat.android.app.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import xyz.xfans.xchat.android.app.R;
import xyz.xfans.xchat.android.app.app.BaseActivity;
import xyz.xfans.xchat.android.app.ui.adapter.MyViewPagerAdapter;
import xyz.xfans.xchat.android.app.ui.fragment.FriendsFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements FriendsFragment.OnFragmentInteractionListener{

    protected Toolbar toolbar;
    List<Fragment> fragments;
    MyViewPagerAdapter adapter;
    ViewPager viewPager;
    TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setSupportActionBar(toolbar);
        tabs= (TabLayout) findViewById(R.id.tabs);
        fragments = new ArrayList<Fragment>();
        fragments.add(FriendsFragment.newInstance());
        fragments.add(FriendsFragment.newInstance());
        adapter = new MyViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        tabs.addTab(tabs.newTab().setText("Tab1"));
        tabs.addTab(tabs.newTab().setText("Tab1"));
        tabs.setupWithViewPager(viewPager);
        tabs.setTabsFromPagerAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
