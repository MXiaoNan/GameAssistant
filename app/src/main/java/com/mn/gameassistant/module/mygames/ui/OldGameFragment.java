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
import com.mn.gameassistant.module.mygames.bean.OldGameInfo;
import com.mn.gameassistant.module.mygames.dao.OldGameDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoNan on 2016/6/30.
 */
public class OldGameFragment extends BaseFragment {
    private PullToRefreshListView mpull_lv;
    private List<OldGameInfo.InfoBean> moldGList;
    private CommonAdapter<OldGameInfo.InfoBean> moldAdapter;
    private int miPage = 1;
//    private String mstate = "1";

    @Override
    protected int setViewID() {
        return R.layout.layout_game_all;
    }

    @Override
    protected void findViews(View view) {
//        view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        mpull_lv = (PullToRefreshListView) view.findViewById(R.id.pull_lv);
    }

    @Override
    protected void init() {
        mpull_lv.setMode(PullToRefreshBase.Mode.BOTH);

        moldGList = new ArrayList<>();

        moldAdapter = new CommonAdapter<OldGameInfo.InfoBean>(this.getActivity(),
                moldGList,
                R.layout.layout_gameall_item) {
            @Override
            public void convert(ViewHolder helper, int position, OldGameInfo.InfoBean item) {
                helper.setImageByUrl(R.id.iv_gameall_icon, item.getIcon(), getActivity());
                helper.setText(R.id.tv_gameall_title, item.getName());
                helper.setRating(R.id.rb_gameall_star, (int) Double.parseDouble(item.getScore()));
                helper.setText(R.id.tv_gameall_download, item.getCount_dl() + "人下载");
                //helper.setText(R.id.tv_gameall_size, item.getSize());
                helper.setText(R.id.btn_game_money, String.valueOf("奖" + item.getAll_prize() + "U币"));
            }
        };
        mpull_lv.setAdapter(moldAdapter);
    }

    @Override
    protected void initEvent() {
        mpull_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                miPage = 1;
                moldGList.clear();
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
        OldGameDao.getOldGameInfo(miPage, new ListViewCallBack() {
            @Override
            public void updateListView(Object object) {
                List<OldGameInfo.InfoBean> datas = (List<OldGameInfo.InfoBean>) object;
                moldGList.addAll(datas);
                moldAdapter.notifyDataSetChanged();
                mpull_lv.onRefreshComplete();
            }
        });
    }
}
