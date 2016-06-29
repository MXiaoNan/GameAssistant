package com.mn.gameassistant.module.main.ui;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseActivity;

/**
 * Created by XiaoNan on 2016/6/27.
 */
public class RBActivity extends BaseActivity {

    PopupWindow mrb_windown;
    private View mRbView;
    private Button mbtn_pw_rb_reg;
    private TextView mtv_pw_rb_login;


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mrb_windown == null) {
            setAlpha(0.5f);
            mrb_windown = new PopupWindow(mRbView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    false);
            mrb_windown.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
    }

    protected void setAlpha(float fAlpha) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = fAlpha;
        getWindow().setAttributes(attributes);
    }

    @Override
    protected int setViewID() {
        return R.layout.layout_rbactivity;
    }

    @Override
    protected void findViews() {
        mRbView = LayoutInflater.from(this).inflate(R.layout.layout_pw_rb, null);
        mbtn_pw_rb_reg = (Button) mRbView.findViewById(R.id.btn_pw_rb_reg);
        mtv_pw_rb_login = (TextView) mRbView.findViewById(R.id.tv_pw_rb_login);

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {
        mbtn_pw_rb_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RBActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });

        mtv_pw_rb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RBActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onDestroy() {
        if (mrb_windown != null) {
            mrb_windown.dismiss();
            mrb_windown = null;
        }
        super.onDestroy();
    }
}
