package com.mn.gameassistant.module.money.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mn.gameassistant.R;
import com.mn.gameassistant.module.money.bean.TaskGameInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by XiaoNan on 2016/7/1.
 */
public class MyMutilAdapter extends BaseAdapter {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_PLAYFROM = 1;
    private final List<TaskGameInfo.InfoBean> mdatas;
    private final Context mcontext;

    public MyMutilAdapter(List<TaskGameInfo.InfoBean> datas, Context context) {
        mdatas = datas;
        mcontext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 2) {
            return TYPE_TITLE;
        } else {
            return TYPE_PLAYFROM;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mdatas.size() + 3;
    }

    @Override
    public Object getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (convertView == null) {
            if (type == TYPE_TITLE) {
                convertView = LayoutInflater.from(mcontext).inflate(R.layout.layout_money_mymutil, null);
                TitleHolder titleHolder = new TitleHolder();
                titleHolder.mtvTitleName = (TextView) convertView.findViewById(R.id.tv_head_title);
                titleHolder.mivImageView = (ImageView) convertView.findViewById(R.id.iv_head_icon);

                setTitle(position, titleHolder);

                convertView.setTag(titleHolder);
            } else {
                convertView = LayoutInflater.from(mcontext).inflate(R.layout.layout_moneylist_item, null);
                PlatFormHolder platFormHolder = new PlatFormHolder();
                platFormHolder.mivicon = (ImageView) convertView.findViewById(R.id.iv_game_icon);
                platFormHolder.mtvPlatFormName = (TextView) convertView.findViewById(R.id.tv_game_title);
                platFormHolder.mRatingBar = (RatingBar) convertView.findViewById(R.id.rb_game_star);
                platFormHolder.mtvText = (TextView) convertView.findViewById(R.id.tv_game_text);
                platFormHolder.mtvJiangLi = (TextView) convertView.findViewById(R.id.btn_game_money);

                setOlatForm(position, platFormHolder);

                convertView.setTag(platFormHolder);
            }
        } else {
            if (type == TYPE_TITLE) {
                TitleHolder titleHolder = (TitleHolder) convertView.getTag();

                setTitle(position, titleHolder);

            } else {
                PlatFormHolder platFormHolder = (PlatFormHolder) convertView.getTag();

                setOlatForm(position, platFormHolder);

            }
        }
        return convertView;
    }

    private void setOlatForm(int position, PlatFormHolder platFormHolder) {
        if (position == 1) {
            platFormHolder.mivicon.setImageResource(R.mipmap.ic_about_app);
            platFormHolder.mtvPlatFormName.setText("游戏任务");
            platFormHolder.mRatingBar.setRating(3.5f);
            platFormHolder.mtvText.setText("官方任务，奖励丰厚");
            platFormHolder.mtvJiangLi.setText("奖励888元");
        } else {
            Picasso.with(mcontext).load(mdatas.get(position - 3).getAd_img()).into(platFormHolder.mivicon);
            platFormHolder.mtvPlatFormName.setText(mdatas.get(position - 3).getPlatform_name());
//            platFormHolder.mRatingBar.setRating((int)Double.parseDouble((mdatas.get(position-3).getRank())));
            platFormHolder.mRatingBar.setRating(4.6f);
            platFormHolder.mtvText.setText(mdatas.get(position - 3).getDesc());
            platFormHolder.mtvJiangLi.setText(mdatas.get(position - 3).getReward());
        }
    }

    private void setTitle(int position, TitleHolder titleHolder) {
        if (position == 0) {
            titleHolder.mtvTitleName.setText("官方推荐（1元=1000U币）");
            titleHolder.mivImageView.setImageResource(R.mipmap.ic_icon);
        } else {
            titleHolder.mtvTitleName.setText("联盟任务（1元=1000U币）");
            titleHolder.mivImageView.setImageResource(R.mipmap.ic_default_pressed);
        }
    }

    class TitleHolder {
        TextView mtvTitleName;
        ImageView mivImageView;
    }

    class PlatFormHolder {
        ImageView mivicon;
        TextView mtvPlatFormName;
        RatingBar mRatingBar;
        TextView mtvText;
        TextView mtvJiangLi;
    }
}
