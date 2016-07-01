package com.mn.gameassistant.module.mygames.ui;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseFragment;
import com.mn.gameassistant.base.ListViewCallBack;
import com.mn.gameassistant.common.adapter.CommonAdapter;
import com.mn.gameassistant.common.adapter.ViewHolder;
import com.mn.gameassistant.module.mygames.bean.MyGameInfo;
import com.mn.gameassistant.module.mygames.dao.MyGameDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoNan on 2016/6/30.
 */
public class AllGameFragment extends BaseFragment {

    private PullToRefreshListView mpull_lv;
    private CommonAdapter<MyGameInfo.InfoBean> mAdapter;
    private List<MyGameInfo.InfoBean> mGameList;
    private int miPage = 1;
    private TextView mbtn_game_money;

    @Override
    protected int setViewID() {
        return R.layout.layout_game_all;
    }

    @Override
    protected void findViews(View view) {
//        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mpull_lv = (PullToRefreshListView) view.findViewById(R.id.pull_lv);
    }

    @Override
    protected void init() {

        mpull_lv.setMode(PullToRefreshBase.Mode.BOTH);

        mGameList = new ArrayList<>();
        mAdapter = new CommonAdapter<MyGameInfo.InfoBean>(this.getActivity(),
                mGameList,
                R.layout.layout_gameall_item) {
            @Override
            public void convert(ViewHolder helper, int position, MyGameInfo.InfoBean item) {
                helper.setImageByUrl(R.id.iv_gameall_icon, item.getIcon(), getActivity());
                helper.setText(R.id.tv_gameall_title, item.getName());
                helper.setRating(R.id.rb_gameall_star, (int) Double.parseDouble(item.getScore()));
                helper.setText(R.id.tv_gameall_download, item.getCount_dl() + "人下载");
                helper.setText(R.id.tv_gameall_size, item.getSize());
                if ((item.getLimit_number()).equals(item.getDl_num())) {
//                    mbtn_game_money.setTextColor(Color.RED);

                    helper.setText(R.id.btn_game_money, "已招募满");
                } else {
                    helper.setText(R.id.btn_game_money, String.valueOf("奖" + item.getAll_prize() + "U币"));
                    //mbtn_game_money.setTextColor(Color.BLUE);
                }
            }
        };
        mpull_lv.setAdapter(mAdapter);
    }

    @Override
    protected void initEvent() {
        mpull_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                miPage = 1;
                mGameList.clear();
                loadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                miPage++;
                loadData();
            }
        });

    }

    @Override
    protected void loadData() {
        MyGameDao.getMyGameAllInfo(miPage, new ListViewCallBack() {
            @Override
            public void updateListView(Object object) {
                List<MyGameInfo.InfoBean> datas = (List<MyGameInfo.InfoBean>) object;
                mGameList.addAll(datas);
                mAdapter.notifyDataSetChanged();
                mpull_lv.onRefreshComplete();
            }
        });
    }
}
