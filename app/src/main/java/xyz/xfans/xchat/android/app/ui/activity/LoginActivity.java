package xyz.xfans.xchat.android.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;
import org.json.JSONObject;
import xyz.xfans.xchat.android.app.R;
import xyz.xfans.xchat.android.app.app.BaseActivity;
import xyz.xfans.xchat.android.app.app.BaseApp;
import xyz.xfans.xchat.android.app.config.UrlConfig;
import xyz.xfans.xchat.android.app.entity.UserInfo;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    // UI references.
    private EditText mNameView;
    private EditText mPasswordView;
    private ScrollView loginForm;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mNameView = (EditText) findViewById(R.id.name);
        loginForm = (ScrollView) findViewById(R.id.login_form);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        String name = mNameView.getText().toString().trim();
        String pwd = mPasswordView.getText().toString().trim();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
            Snackbar.make(loginForm, "请输入用户名密码", Snackbar.LENGTH_LONG)
                    .show();
            return;
        }
        //TODO 登录
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("name",name);
        params.put("pwd", pwd);
        asyncHttpClient.post(this, UrlConfig.LOGIN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resp = new String(responseBody);
                System.out.println("LoginActivity.onSuccess:" + resp);
                try {
                    JSONObject jsonObject = new JSONObject(resp);
                    int status = jsonObject.getInt("status");
                    if(status == 1){
                        Gson gson = new Gson();
                        String userStr = jsonObject.getString("data");
                        UserInfo userInfo = gson.fromJson(userStr, UserInfo.class);
                        BaseApp baseApp = (BaseApp) getApplication();
                        baseApp.setUserInfo(userInfo);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        Snackbar.make(loginForm, "用户名或密码错误", Snackbar.LENGTH_LONG)
                                .show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Snackbar.make(loginForm, "用户名或密码错误", Snackbar.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("LoginActivity.onFailure:"+statusCode+":"+error.getMessage());
                Snackbar.make(loginForm, "登录失败", Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }

}



