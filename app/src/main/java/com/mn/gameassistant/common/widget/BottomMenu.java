package com.mn.gameassistant.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mn.gameassistant.R;

/**
 * Created by XiaoNan on 2016/6/29.
 */
public class BottomMenu extends LinearLayout {

    private int mNormalpic;
    private int mPressPic;
    private TextView mtv_btm_menu_text;
    private ImageView miv_btm_menu_pic;
    private View mview;
    boolean mIsSelect = false;

    public BottomMenu(Context context) {
        super(context);
    }

    public BottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        mview = LayoutInflater.from(context).inflate(R.layout.layout_bottommenu, this, true);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BottomMenu);
        String text = (String) array.getText(R.styleable.BottomMenu_text);

        mNormalpic = array.getResourceId(R.styleable.BottomMenu_normalpic, -1);
        mPressPic = array.getResourceId(R.styleable.BottomMenu_presspic, -1);

        mtv_btm_menu_text = (TextView) mview.findViewById(R.id.tv_btm_menu_text);
        miv_btm_menu_pic = (ImageView) mview.findViewById(R.id.iv_btm_menu_pic);

        mtv_btm_menu_text.setText(text);
        miv_btm_menu_pic.setImageResource(mNormalpic);

        array.recycle();

    }

    public void onSelect() {
        if (mIsSelect) {
            return;
        }
        mIsSelect = true;

        miv_btm_menu_pic.setImageResource(mPressPic);

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                1f);

        translateAnimation.setDuration(200);
        translateAnimation.setFillAfter(true);
        mtv_btm_menu_text.startAnimation(translateAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1f,
                1.5f,
                1f,
                1.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0f);
        scaleAnimation.setDuration(200);
        scaleAnimation.setFillAfter(true);
        miv_btm_menu_pic.startAnimation(scaleAnimation);
    }

    public void onUnSelect() {
        mIsSelect = false;
        miv_btm_menu_pic.setImageResource(mNormalpic);

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                1f,
                Animation.RELATIVE_TO_SELF,
                0f);

        translateAnimation.setDuration(200);
        translateAnimation.setFillAfter(true);
        mtv_btm_menu_text.startAnimation(translateAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.5f,
                1f,
                1.5f,
                1f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0f);
        scaleAnimation.setDuration(200);
        miv_btm_menu_pic.startAnimation(scaleAnimation);
    }
}
