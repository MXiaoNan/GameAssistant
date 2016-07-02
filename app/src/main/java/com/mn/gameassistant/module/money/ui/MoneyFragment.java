package com.mn.gameassistant.module.money.ui;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseFragment;
import com.mn.gameassistant.base.ListViewCallBack;
import com.mn.gameassistant.common.adapter.CommonAdapter;
import com.mn.gameassistant.common.adapter.ViewHolder;
import com.mn.gameassistant.module.money.adapter.MyMutilAdapter;
import com.mn.gameassistant.module.money.bean.TaskGameInfo;
import com.mn.gameassistant.module.money.dao.MoneyDao;
import com.mn.gameassistant.module.mygames.ui.MyGameActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoNan on 2016/6/29.
 */
public class MoneyFragment extends BaseFragment {
    private boolean mLoad;
    private PopupWindow mPwLoad;
    View mviewPw;
    View mViewFragment;
    ListView mlvTackGame;
    //    CommonAdapter<TaskGameInfo.InfoBean> mAdapter;
    MyMutilAdapter myMutilAdapter;
    private List<TaskGameInfo.InfoBean> mListInfo;
    private View headView;

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
        mlvTackGame = (ListView) view.findViewById(R.id.lv_money_view);
    }

    @Override
    protected void init() {
        /*headView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_tasklist_head, null);
        mlvTackGame.addHeaderView(headView);*/

        mviewPw = LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_load_pw, null);
        mListInfo = new ArrayList<>();

        /*mAdapter = new CommonAdapter<TaskGameInfo.InfoBean>(getActivity(),
                mListInfo,
                R.layout.layout_moneylist_item) {
            @Override
            public void convert(ViewHolder helper, int position, TaskGameInfo.InfoBean item) {
                helper.setImageByUrl(R.id.iv_game_icon, item.getAd_img(), getActivity());
                helper.setText(R.id.tv_game_title, item.getPlatform_name());
                helper.setRating(R.id.rb_game_star, Integer.parseInt(item.getRank()));
                helper.setText(R.id.tv_game_text, item.getDesc());
                helper.setText(R.id.btn_game_money, item.getReward());
            }
        };*/
        /*mlvTackGame.setAdapter(mAdapter);*/

        myMutilAdapter = new MyMutilAdapter(mListInfo, this.getActivity());
        mlvTackGame.setAdapter(myMutilAdapter);
    }

    @Override
    protected void initEvent() {
        mlvTackGame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "点击了..." + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 1:
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), MyGameActivity.class);
                        startActivity(intent);
                        break;
                }
                for (int i = 2; i < position; i++) {
                    Toast.makeText(getActivity(), "点击了..." + position, Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }

    @Override
    protected void loadData() {
        MoneyDao.getMoneyDao(new ListViewCallBack() {
            @Override
            public void updateListView(Object object) {
                List<TaskGameInfo.InfoBean> datas = (List<TaskGameInfo.InfoBean>) object;
                mListInfo.addAll(datas);

                myMutilAdapter.notifyDataSetChanged();

                mLoad = true;
                if (mPwLoad != null) {
                    mPwLoad.dismiss();
                    mPwLoad = null;
                }
            }
        });
    }
}
