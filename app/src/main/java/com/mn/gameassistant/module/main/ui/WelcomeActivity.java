package com.mn.gameassistant.module.main.ui;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.mn.gameassistant.R;
import com.mn.gameassistant.base.BaseActivity;
import com.mn.gameassistant.common.constant.Constant;
import com.se7en.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoNan on 2016/6/27.
 */
public class WelcomeActivity extends BaseActivity {

    ViewPager mViewPager;
    List<ImageView> mListPic;
    Button mBtn_start;
    private int miCurVersion;
    private int miLastVersion;

    private ImageView iv_textLogo;
    private ImageView iv_picLogo;

    boolean misLogin = false;

    @Override
    protected int setViewID() {
        return R.layout.layout_welcome;
    }

    @Override
    protected void findViews() {
        mViewPager = (ViewPager) findViewById(R.id.vp_show);
        mBtn_start = (Button) findViewById(R.id.btn_welcome);
        iv_textLogo = (ImageView) findViewById(R.id.iv_textlogo);
        iv_picLogo = (ImageView) findViewById(R.id.iv_piclogo);
    }

    protected void addImageView(int iResID) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(iResID);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mListPic.add(imageView);
    }

    @Override
    protected void init() {
        misLogin = SystemUtil.getSharedBoolean(Constant.LOGIN_FLAG, false);

        miCurVersion = SystemUtil.getSystemVersionCode();
        miLastVersion = SystemUtil.getSharedInt(Constant.VERSION_STRING, -1);
        if ((miLastVersion == -1) || (miCurVersion > miLastVersion)) {
            mListPic = new ArrayList<>();
            addImageView(R.mipmap.bg_guide_01);
            addImageView(R.mipmap.bg_guide_02);
            addImageView(R.mipmap.bg_guide_03);
            addImageView(R.mipmap.bg_guide_04);

            mViewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return mListPic.size();
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    //return super.instantiateItem(container, position);
                    container.addView(mListPic.get(position));
                    return mListPic.get(position);
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                    container.removeView(mListPic.get(position));
                }
            });
        } else {
            mViewPager.setVisibility(View.GONE);
            mBtn_start.setVisibility(View.GONE);

            iv_textLogo.setVisibility(View.VISIBLE);

            setIv_textLogoAnim();
        }
    }

    protected void setIv_textLogoAnim() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                -1.0f,
                Animation.RELATIVE_TO_PARENT,
                0f,
                Animation.RELATIVE_TO_PARENT,
                0,
                Animation.RELATIVE_TO_PARENT,
                0);
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        iv_textLogo.startAnimation(translateAnimation);

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv_picLogo.setVisibility(View.VISIBLE);
                setIv_PicLogoAnim();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    protected void setIv_PicLogoAnim() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0f,
                Animation.RELATIVE_TO_PARENT,
                0f,
                Animation.RELATIVE_TO_PARENT,
                -1.0f,
                Animation.RELATIVE_TO_PARENT,
                0);
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(new BounceInterpolator());
        iv_picLogo.startAnimation(translateAnimation);

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showNextActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    protected void showNextActivity() {

        Intent intent = new Intent();
        if (misLogin) {
            intent.setClass(this, MainActivity.class);
        } else {
            intent.setClass(this, RBActivity.class);
        }
        startActivity(intent);

        finish();
    }

    @Override
    protected void initEvent() {
        if ((miLastVersion == -1) || (miCurVersion > miLastVersion)) {
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == mListPic.size() - 1) {
                        mBtn_start.setVisibility(View.VISIBLE);
                    } else {
                        mBtn_start.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            mBtn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SystemUtil.setSharedInt(Constant.VERSION_STRING, miCurVersion);
                    showNextActivity();
                }
            });
        }
    }

    @Override
    protected void loadData() {

    }
}
