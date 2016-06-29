package com.mn.gameassistant.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by XiaoNan on 2016/6/27.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewID());

        findViews();
        init();
        initEvent();
        loadData();
    }

    protected abstract int setViewID();

    protected abstract void findViews();

    protected abstract void init();

    protected abstract void initEvent();

    protected abstract void loadData();


}
