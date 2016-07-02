package com.mn.gameassistant.module.mygames.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoNan on 2016/6/30.
 */
public class MyGameActivity extends BaseActivity {

    private ViewPager mvp_allgame;
    private AllGameFragment mAllGameFragment;
    private NewGameFragment mNewGameFragment;
    private OldGameFragment mOldGameFragment;
    private List<Fragment> mGameList;
    private ImageButton mib_money_back;
    private EditText met_search;
    private RadioGroup mrg_group;


    @Override
    protected int setViewID() {
        return R.layout.layout_mygame;
    }

    @Override
    protected void findViews() {
        mvp_allgame = (ViewPager) findViewById(R.id.vp_allgame);
        mib_money_back = (ImageButton) findViewById(R.id.ib_money_back);
        met_search = (EditText) findViewById(R.id.et_search);
        mrg_group = (RadioGroup) findViewById(R.id.rg_group);
    }

    @Override
    protected void init() {
        mAllGameFragment = new AllGameFragment();
        mNewGameFragment = new NewGameFragment();
        mOldGameFragment = new OldGameFragment();

        mGameList = new ArrayList<>();
        mGameList.add(mAllGameFragment);
        mGameList.add(mNewGameFragment);
        mGameList.add(mOldGameFragment);

        mvp_allgame.setAdapter(new myFragmentAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void initEvent() {
        mib_money_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        met_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyGameActivity.this, "点击了搜索框", Toast.LENGTH_SHORT).show();
            }
        });

        mrg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_game_all:
                        mvp_allgame.setCurrentItem(0);
                        break;
                    case R.id.rb_game_new:
                        mvp_allgame.setCurrentItem(1);
                        break;
                    case R.id.rb_game_old:
                        mvp_allgame.setCurrentItem(2);
                        break;
                }
            }
        });

        mvp_allgame.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mrg_group.check(R.id.rb_game_all);
                        break;
                    case 1:
                        mrg_group.check(R.id.rb_game_new);
                        break;
                    case 2:
                        mrg_group.check(R.id.rb_game_old);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void loadData() {

    }

    class myFragmentAdapter extends FragmentPagerAdapter {


        public myFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mGameList.get(position);
        }

        @Override
        public int getCount() {
            return mGameList.size();
        }
    }
}
