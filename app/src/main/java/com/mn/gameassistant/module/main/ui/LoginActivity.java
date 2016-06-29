package com.mn.gameassistant.module.main.ui;

import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseActivity;
import com.mn.gameassistant.base.NetCallback;
import com.mn.gameassistant.common.constant.Constant;
import com.mn.gameassistant.common.net.HttpNet;
import com.mn.gameassistant.module.main.bean.LoginInfo;
import com.se7en.utils.SystemUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XiaoNan on 2016/6/28.
 */
public class LoginActivity extends BaseActivity {

    private long mTimeMillis;
    private long mLastTime = 0;
    private Button mbtn_login;
    private TextView mtv_login_reg;
    private EditText met_login_phone;
    private EditText met_login_pwd;
    private CheckBox mcb_login;


    @Override
    protected int setViewID() {
        return R.layout.layout_login;
    }

    @Override
    protected void findViews() {
        mbtn_login = (Button) findViewById(R.id.btn_login);
        mtv_login_reg = (TextView) findViewById(R.id.tv_login_reg);
        met_login_phone = (EditText) findViewById(R.id.et_login_phone);
        met_login_pwd = (EditText) findViewById(R.id.et_login_pwd);
        mcb_login = (CheckBox) findViewById(R.id.cb_login);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {
        mbtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPhone = met_login_phone.getText().toString();
                String strPwd = met_login_pwd.getText().toString();

                strPwd = new String(Base64.encode(strPwd.getBytes(), Base64.DEFAULT));

                Map<String, String> stringMap = new HashMap<String, String>();
                stringMap.put("username", strPhone);
                stringMap.put("password", strPwd);

                HttpNet.doHttpRequest("POST",
                        Constant.LOGIN_URL,
                        stringMap,
                        new NetCallback() {
                            @Override
                            public void success(String strResult) {
                                Log.d("print", "数据解析成功！" + strResult);
                                doLoginInfo(strResult);
                            }

                            @Override
                            public void fail(String strMsg) {
                                Log.d("print", "数据解析失败！" + strMsg);
                            }
                        });
            }
        });

        mtv_login_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void doLoginInfo(String strLoginInfo) {
        Gson gson = new Gson();
        LoginInfo loginInfo = gson.fromJson(strLoginInfo, LoginInfo.class);
        String state = loginInfo.getState();
        if ("success".equals(state)) {
            Log.d("print", "登录成功！");
            if (mcb_login.isChecked()) {
                SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG, true);
            } else {
                SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG, false);
            }

            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG, false);
            Log.d("print", "登录失败！");
        }


    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mTimeMillis = System.currentTimeMillis();
            if ((mTimeMillis - mLastTime) > 2000) {
                Toast.makeText(LoginActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mLastTime = mTimeMillis;
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
