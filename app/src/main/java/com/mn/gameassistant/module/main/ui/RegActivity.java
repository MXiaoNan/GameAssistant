package com.mn.gameassistant.module.main.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseActivity;

/**
 * Created by XiaoNan on 2016/6/28.
 */
public class RegActivity extends BaseActivity {

    private TextView magree_text;
    private TextView mtv_getcode;
    private int millFuture = 20;
    private ImageButton mtv_reg_back;

    @Override
    protected int setViewID() {
        return R.layout.layout_reg;
    }

    @Override
    protected void findViews() {
        magree_text = (TextView) findViewById(R.id.agree_text);
        magree_text.setMovementMethod(new LinkMovementMethod());

        mtv_reg_back = (ImageButton) findViewById(R.id.tv_reg_back);

        mtv_getcode = (TextView) findViewById(R.id.tv_getcode);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {
        mtv_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtv_getcode.setEnabled(false);

                new CountDownTimer(millFuture * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        millFuture--;
                        mtv_getcode.setText(millFuture + "");
                    }

                    @Override
                    public void onFinish() {
                        millFuture = 20;
                        mtv_getcode.setEnabled(true);
                        mtv_getcode.setText("重新获取验证码");
                        mtv_getcode.setTextColor(Color.BLUE);
                    }
                }.start();
            }
        });

        mtv_reg_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegActivity.this, RBActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
