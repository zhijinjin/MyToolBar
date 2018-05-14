package com.zjj.mytoolbarlibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by zhijinjin (951507056@qq.com)
 * on 2018/4/14.
 */

public class MyToolBar extends Toolbar  implements View.OnClickListener{
    private OnMyToolBarClickLisener toolBarClickLisener;
    //左侧按钮
    private TextView mLeftTitle;
    private boolean mLeftVisible;

    //标题
    private TextView mTitle;
    private boolean mTitleVisible;
    //右侧按钮
    private TextView mRightTitle;
    private boolean mRightVisible;

    private static final int DEFAULT_BACK_MARGIN_RIGHT = 8;
    private static final int DEFAULT_RIGHT_MARGIN_RIGHT = 8;

    public MyToolBar(Context context) {
        this(context,null);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.toolbarStyle);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomView(context, attrs, defStyleAttr);
    }


    @SuppressLint("RestrictedApi")
    private void initCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        @SuppressLint("RestrictedApi")
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyToolBar);

        if(!isChild(mLeftTitle)){
            mLeftTitle = new TextView(context);
            mLeftTitle.setId(R.id.back);
            mLeftTitle.setSingleLine();
            mLeftTitle.setEllipsize(TextUtils.TruncateAt.END);
            mLeftTitle.setGravity(Gravity.CENTER_VERTICAL);

            if(typedArray.hasValue(R.styleable.MyToolBar_leftAppearance)){
                mLeftTitle.setTextAppearance(context, typedArray.getResourceId(R.styleable.MyToolBar_leftAppearance,0));
            }
            if(typedArray.hasValue(R.styleable.MyToolBar_leftColor)){
                mLeftTitle.setTextColor(typedArray.getColor(R.styleable.MyToolBar_leftColor, Color.WHITE));
            }
            if(typedArray.hasValue(R.styleable.MyToolBar_leftSize)){
                mLeftTitle.setTextSize(typedArray.getDimensionPixelSize(R.styleable.MyToolBar_leftSize,0));
            }

            Drawable drawable = typedArray.getDrawable(R.styleable.MyToolBar_leftIcon);
            if(null!=drawable){
                mLeftTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            }
            setLeftText(typedArray.getString(R.styleable.MyToolBar_leftText));
            setLeftVisible(typedArray.getBoolean(R.styleable.MyToolBar_leftVisible,true));

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT, Gravity.LEFT | Gravity.CENTER_VERTICAL);

            mLeftTitle.setClickable(true);
            mLeftTitle.setOnClickListener(this);

//            layoutParams.leftMargin = typedArray.getDimensionPixelSize(
//                    R.styleable.MyToolBar_leftMarginLeft, dp2px(DEFAULT_BACK_MARGIN_RIGHT));

            addView(mLeftTitle, layoutParams);
        }

        if(!isChild(mTitle)){
            mTitle = new TextView(context);
            mTitle.setSingleLine();
            mTitle.setId(R.id.mtitle);
            mTitle.setEllipsize(TextUtils.TruncateAt.END);
            mTitle.setGravity(Gravity.CENTER_VERTICAL);

            if(typedArray.hasValue(R.styleable.MyToolBar_titleAppearance)){
                mTitle.setTextAppearance(context, typedArray.getResourceId(R.styleable.MyToolBar_titleAppearance,0));
            }
            if(typedArray.hasValue(R.styleable.MyToolBar_titleColor)){
                mTitle.setTextColor(typedArray.getColor(R.styleable.MyToolBar_titleColor, Color.WHITE));
            }
            if(typedArray.hasValue(R.styleable.MyToolBar_titleSize)){
                mTitle.setTextSize(typedArray.getDimensionPixelSize(R.styleable.MyToolBar_titleSize,0));
            }

            Drawable drawable = typedArray.getDrawable(R.styleable.MyToolBar_titleIcon);
            if(null!=drawable){
                mTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            }

            setTitleText(typedArray.getString(R.styleable.MyToolBar_titleText));
            setTitleVisible(typedArray.getBoolean(R.styleable.MyToolBar_rightVisible,true));

            mTitle.setClickable(true);
            mTitle.setOnClickListener(this);

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT, Gravity.CENTER);

            addView(mTitle, layoutParams);
        }


        if(!isChild(mRightTitle)){
            mRightTitle = new TextView(context);
            mRightTitle.setSingleLine();
            mRightTitle.setId(R.id.right);
            mRightTitle.setEllipsize(TextUtils.TruncateAt.END);
            mRightTitle.setGravity(Gravity.CENTER_VERTICAL);

            if(typedArray.hasValue(R.styleable.MyToolBar_rightAppearance)){
                mRightTitle.setTextAppearance(context, typedArray.getResourceId(R.styleable.MyToolBar_rightAppearance,0));
            }
            if(typedArray.hasValue(R.styleable.MyToolBar_rightColor)){
                mRightTitle.setTextColor(typedArray.getColor(R.styleable.MyToolBar_rightColor, Color.WHITE));
            }
            if(typedArray.hasValue(R.styleable.MyToolBar_rightSize)){
                mRightTitle.setTextSize(typedArray.getDimensionPixelSize(R.styleable.MyToolBar_rightSize,0));
            }

            Drawable drawable = typedArray.getDrawable(R.styleable.MyToolBar_rightIcon);
            if(null!=drawable){
                mRightTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            }

            setRightText(typedArray.getString(R.styleable.MyToolBar_rightText));
            setRightVisible(typedArray.getBoolean(R.styleable.MyToolBar_rightVisible,true));

            mRightTitle.setClickable(true);
            mRightTitle.setOnClickListener(this);

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT, Gravity.RIGHT|Gravity.CENTER_VERTICAL);
            layoutParams.rightMargin = typedArray.getDimensionPixelSize(
                    R.styleable.MyToolBar_rightMarginRight, dp2px(DEFAULT_BACK_MARGIN_RIGHT));

            addView(mRightTitle, layoutParams);
        }
        typedArray.recycle();
//        tintTypedArray.recycle();
    }

    public void setLeftText(CharSequence title){
        if (mLeftTitle != null) {
            mLeftTitle.setText(title);
        }
    }

    public void setLeftDrawable(Drawable drawable){
        if(null!=drawable){
            mLeftTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
    }

    public void setLeftTextSize(float size){
        if (mLeftTitle != null) {
            mLeftTitle.setTextSize(size);
        }
    }

    public void setLeftVisible(boolean visible){
        mLeftVisible = visible;
        if (mLeftTitle != null) {
            mLeftTitle.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    public void setTitleText(CharSequence title){
        if (mTitle != null) {
            mTitle.setText(title);
        }
    }

    public void setTitleTextSize(float size){
        if (mTitle != null) {
            mTitle.setTextSize(size);
        }
    }

    public void setTitleDrawable(Drawable drawable){
        if(null!=drawable){
            mTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
    }

    public void setTitleVisible(boolean visible){
        mTitleVisible = visible;
        if (mTitle != null) {
            mTitle.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    public void setRightText(CharSequence title){
        if (mRightTitle != null) {
            mRightTitle.setText(title);
        }
    }

    public void setRightDrawable(Drawable drawable){
        if(null!=drawable){
            mRightTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        }
    }


    public void setRightTextSize(float size){
        if (mRightTitle != null) {
            mRightTitle.setTextSize(size);
        }
    }

    public void setRightVisible(boolean visible){
        mRightVisible = visible;
        if (mRightTitle != null) {
            mRightTitle.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    public int dp2px(float dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }

    @Override
    public void onClick(View v) {
        if(null==toolBarClickLisener){
            return;
        }
        int i = v.getId();
        if (i == R.id.back) {
            toolBarClickLisener.onLeftClick();
        }else if(i == R.id.right){
            toolBarClickLisener.onRightClick();
        }else if(i == R.id.mtitle){
            toolBarClickLisener.onTitleClick();
        }
    }

    public boolean isChild(View view) {
        return view != null && view.getParent() == this;
    }

    public void setOnMyToolBarClickLisener(OnMyToolBarClickLisener toolBarClickLisener){
        this.toolBarClickLisener = toolBarClickLisener;
    }

    public interface OnMyToolBarClickLisener{
        void onLeftClick();
        void onRightClick();
        void onTitleClick();
    }
}
