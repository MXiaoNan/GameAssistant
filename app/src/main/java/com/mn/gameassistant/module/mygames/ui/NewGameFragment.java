package com.mn.gameassistant.module.mygames.ui;

import android.view.View;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseFragment;
import com.mn.gameassistant.base.ListViewCallBack;
import com.mn.gameassistant.common.adapter.CommonAdapter;
import com.mn.gameassistant.common.adapter.ViewHolder;
import com.mn.gameassistant.module.mygames.bean.NewGameInfo;
import com.mn.gameassistant.module.mygames.dao.NewGameDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoNan on 2016/6/30.
 */
public class NewGameFragment extends BaseFragment {
    private PullToRefreshListView mpull_lv;
    private List<NewGameInfo.InfoBean> mNewList;
    private CommonAdapter<NewGameInfo.InfoBean> mNewAdapter;
    private int miPage = 1;

    @Override
    protected int setViewID() {
        return R.layout.layout_game_all;
    }

    @Override
    protected void findViews(View view) {
        //view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mpull_lv = (PullToRefreshListView) view.findViewById(R.id.pull_lv);
    }

    @Override
    protected void init() {
        mpull_lv.setMode(PullToRefreshBase.Mode.BOTH);

        mNewList = new ArrayList<>();

        mNewAdapter = new CommonAdapter<NewGameInfo.InfoBean>(this.getActivity(),
                mNewList,
                R.layout.layout_gameall_item) {
            @Override
            public void convert(ViewHolder helper, int position, NewGameInfo.InfoBean item) {
                helper.setImageByUrl(R.id.iv_gameall_icon, item.getIcon(), getActivity());
                helper.setText(R.id.tv_gameall_title, item.getName());
                helper.setRating(R.id.rb_gameall_star, (int) Double.parseDouble(item.getScore()));
                helper.setText(R.id.tv_gameall_download, item.getCount_dl() + "人下载");
                helper.setText(R.id.tv_gameall_size, item.getSize());
                helper.setText(R.id.btn_game_money, String.valueOf("奖" + item.getAll_prize() + "U币"));
            }
        };
        mpull_lv.setAdapter(mNewAdapter);

    }

    @Override
    protected void initEvent() {
        mpull_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                miPage = 1;
                mNewList.clear();
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
        NewGameDao.getNewGameInfo(miPage, new ListViewCallBack() {
            @Override
            public void updateListView(Object object) {
                List<NewGameInfo.InfoBean> datas = (List<NewGameInfo.InfoBean>) object;
                mNewList.addAll(datas);
                mNewAdapter.notifyDataSetChanged();
                mpull_lv.onRefreshComplete();
            }
        });
    }
}
