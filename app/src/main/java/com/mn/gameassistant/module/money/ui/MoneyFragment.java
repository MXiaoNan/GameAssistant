package com.mn.gameassistant.module.money.ui;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseFragment;

/**
 * Created by XiaoNan on 2016/6/29.
 */
public class MoneyFragment extends BaseFragment {
    private boolean mLoad;
    private PopupWindow mPwLoad;
    private View mviewPw;
    private View mViewFragment;

    public void showLoadDialog() {
        if (!mLoad && (mPwLoad == null)) {
            mPwLoad = new PopupWindow(mviewPw,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    false);
            mPwLoad.showAtLocation(mViewFragment, Gravity.CENTER, 0, 0);
        }
    }

    @Override
    protected int setViewID() {
        return R.layout.layout_fragment_money;
    }

    @Override
    protected void findViews(View view) {
        mViewFragment = view;
    }

    @Override
    protected void init() {
        mviewPw = LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_load_pw, null);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

    }
}
