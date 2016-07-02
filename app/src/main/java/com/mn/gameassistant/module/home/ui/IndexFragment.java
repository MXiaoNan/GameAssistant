package com.mn.gameassistant.module.home.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseFragment;
import com.mn.gameassistant.common.adapter.CommonAdapter;
import com.mn.gameassistant.common.constant.Constant;
import com.mn.gameassistant.module.home.bean.UserDataInfo;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

/**
 * Created by XiaoNan on 2016/6/29.
 */
public class IndexFragment extends BaseFragment {
    private CommonAdapter<UserDataInfo.InfoBean> adapter;
    private ImageView mIv_imageView;
    private TextView mTv_index_username;
    private TextView mTv_user_balance;
    private TextView mTv_index_count;
    private TextView mTv_index_money_count;
    private TextView mTv_index_today;

    @Override
    protected int setViewID() {
        return R.layout.layout_fragment_index;
    }

    @Override
    protected void findViews(View view) {
        mIv_imageView = (ImageView) view.findViewById(R.id.imageView);
        mTv_index_username = (TextView) view.findViewById(R.id.tv_index_username);
        mTv_user_balance = (TextView) view.findViewById(R.id.tv_user_balance);
        mTv_index_count = (TextView) view.findViewById(R.id.tv_index_count);
        mTv_index_money_count = (TextView) view.findViewById(R.id.tv_index_money_count);
        mTv_index_today = (TextView) view.findViewById(R.id.tv_index_today);

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

        Gson gson = new Gson();
        UserDataInfo userDataInfo = gson.fromJson(Constant.USER_URL_JSON, UserDataInfo.class);
        UserDataInfo.InfoBean info = userDataInfo.getInfo();

        String score = info.getScore();
        String today_profit = info.getToday_profit();
        double v = Double.parseDouble(score) / 1000;
        double v1 = Double.parseDouble(today_profit) / 1000;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(v);
        String format1 = decimalFormat.format(v1);

        Picasso.with(getActivity()).load(info.getHpic()).into(mIv_imageView);
        mTv_index_username.setText(info.getNickname());
        mTv_user_balance.setText(format);
        mTv_index_count.setText(info.getExpend());
        mTv_index_money_count.setText(format);
        mTv_index_today.setText(format1);

    }
}