package com.cn.nj.putian.newodnclient.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止Viewpager左右滑动功能
 */
public final class NoScrollViewPager extends ViewPager {

    private boolean noScroll = false;//默认滑动

    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //重写这个方法
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //重写这个方法
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(ev);
    }
}
