package com.mn.gameassistant.module.main.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseActivity;
import com.mn.gameassistant.common.widget.BottomMenu;
import com.mn.gameassistant.module.guess.ui.GuessFragment;
import com.mn.gameassistant.module.home.ui.IndexFragment;
import com.mn.gameassistant.module.me.ui.MeFragment;
import com.mn.gameassistant.module.money.ui.MoneyFragment;
import com.mn.gameassistant.module.shop.ui.ShopFragment;

public class MainActivity extends BaseActivity {

    IndexFragment mIndexFragment;
    GuessFragment mGuessFragment;
    MoneyFragment moneyFragment;
    ShopFragment mShopFragment;
    MeFragment mMeFragment;
    Fragment mLastFragment;

    BottomMenu mLastMenu = null;

    @Override
    protected int setViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
        mLastMenu = (BottomMenu) findViewById(R.id.btm_index);
    }

    @Override
    protected void init() {
        mLastMenu.onSelect();
        mIndexFragment = new IndexFragment();
        mGuessFragment = new GuessFragment();
        moneyFragment = new MoneyFragment();
        mShopFragment = new ShopFragment();
        mMeFragment = new MeFragment();

        mLastFragment = mIndexFragment;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.fl_show, mGuessFragment);
        transaction.hide(mGuessFragment);

        transaction.add(R.id.fl_show, moneyFragment);
        transaction.hide(moneyFragment);

        transaction.add(R.id.fl_show, mShopFragment);
        transaction.hide(mShopFragment);

        transaction.add(R.id.fl_show, mMeFragment);
        transaction.hide(mMeFragment);

        transaction.add(R.id.fl_show, mIndexFragment);
        transaction.commit();

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

    }

    public void onChose(View view) {
        BottomMenu menu = (BottomMenu) view;
        menu.onSelect();
        if (!(menu.equals(mLastMenu))) {
            mLastMenu.onUnSelect();
        }
        mLastMenu = menu;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.btm_index:
                if (!(mLastFragment instanceof IndexFragment)) {
                    transaction.hide(mLastFragment);
                }
                mLastFragment = mIndexFragment;
                transaction.show(mLastFragment);
                break;
            case R.id.btm_money:
                if (!(mLastFragment instanceof MoneyFragment)) {
                    transaction.hide(mLastFragment);
                }
                mLastFragment = moneyFragment;
                transaction.show(mLastFragment);
                moneyFragment.showLoadDialog();
                break;
            case R.id.btm_guess:
                if (!(mLastFragment instanceof GuessFragment)) {
                    transaction.hide(mLastFragment);
                }
                mLastFragment = mGuessFragment;
                transaction.show(mLastFragment);
                break;
            case R.id.btm_shop:
                if (!(mLastFragment instanceof ShopFragment)) {
                    transaction.hide(mLastFragment);
                }
                mLastFragment = mShopFragment;
                transaction.show(mLastFragment);
                break;
            case R.id.btm_me:
                if (!(mLastFragment instanceof MeFragment)) {
                    transaction.hide(mLastFragment);
                }
                mLastFragment = mMeFragment;
                transaction.show(mLastFragment);
                break;
        }
        transaction.commit();
    }
}
