package com.cn.nj.putian.newodnclient.widget;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * 简单的封装Snackbar(测试可用)
 * @author zhaol
 */
public final class SnackbarTools {

    public static final int LENGTH_INDEFINITE = Snackbar.LENGTH_INDEFINITE;//自定义，无限时间，直到你选择关闭
    public static final int LENGTH_SHORT      = Snackbar.LENGTH_SHORT;
    public static final int LENGTH_LONG       = Snackbar.LENGTH_LONG;

    //默认的一些颜色
    private static final int COLOR_DEFAULT = 0xFEFFFFFF;
    private static final int COLOR_SUCCESS = 0xFF2BB600;
    private static final int COLOR_WARNING = 0xFFFFC100;
    private static final int COLOR_ERROR   = 0xFFFF0000;
    private static final int COLOR_MESSAGE = 0xFFFFFFFF;

    private static WeakReference<Snackbar> sReference;//

    private View mView;
    private CharSequence message;
    private int messageColor; //文字颜色，目前先不用
    private int bgColor;
    private int bgResource;
    private int duration;
    private CharSequence actionText;
    private int actionTextColor;
    private View.OnClickListener actionListener;

    private SnackbarTools() {
        throw new RuntimeException("SnackbarTools 工具类不能创建无参的构造方法");
    }

    /**
     * 初始化工具
     * @param parent 父实图 用于控件显示的位置放哪
     */
    private SnackbarTools(View parent) {
        this.mView = parent;
        message = "";
        messageColor = COLOR_DEFAULT;
        bgColor = COLOR_DEFAULT;
        bgResource = -1;
        duration = LENGTH_SHORT;//默认很短
    }

    /**
     * 得到工具类
     * @param view 父实图（注意：DecorView尽量别用，这样会把下面的工具栏覆盖，使用）
     * @return
     */
    public static SnackbarTools with(@NonNull View view) {
        return new SnackbarTools(view);
    }

    public SnackbarTools setMessage(@NonNull CharSequence msg) {
        this.message = msg;
        return this;
    }

//    public SnackbarTools setMessageColor(@ColorInt int color) {
//        this.messageColor = color;
//        return this;
//    }

    public SnackbarTools setBgColor(@ColorInt int color) {
        this.bgColor = color;
        return this;
    }

    public SnackbarTools setBgResource(@DrawableRes int bgResource) {
        this.bgResource = bgResource;
        return this;
    }

    public SnackbarTools setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public SnackbarTools setAction(@NonNull final CharSequence text,
                                   @NonNull final View.OnClickListener listener) {
        this.actionText = text;
        this.actionTextColor = COLOR_DEFAULT;
        this.actionListener = listener;
        return this;
    }

    public SnackbarTools setActionTextColor(@ColorInt final int color) {
        this.actionTextColor = color;
        return this;
    }

    public void show() {
        if (this.mView == null) {
            return;
        }
        //新建个Snackbar
        sReference = new WeakReference<>(Snackbar.make(mView, message, duration));
        final Snackbar snackbar = sReference.get();
        final View snackbarView = snackbar.getView();

        //设置背景，背景颜色
        if (bgResource != -1) {
            snackbarView.setBackgroundResource(bgResource);
        } else if (bgColor != COLOR_DEFAULT) {
            snackbarView.setBackgroundColor(bgColor);
        }

        //设置点击事件
        if(actionListener != null && actionText.length() > 0) {
            if (actionTextColor != COLOR_DEFAULT) {
                snackbar.setActionTextColor(actionTextColor);
            }
            snackbar.setAction(actionText, actionListener);
        }

        //显示
        snackbar.show();
    }

    /**
     * Show the snackbar with success style.
     */
    public void showSuccess(String msg) {
        bgColor = COLOR_SUCCESS;
        messageColor = COLOR_MESSAGE;
        actionTextColor = COLOR_MESSAGE;
        if(!"".equals(msg)) {
            setMessage(msg);
        }
        show();
    }

    /**
     * Show the snackbar with warning style.
     */
    public void showWarning(String msg) {
        bgColor = COLOR_WARNING;
        messageColor = COLOR_MESSAGE;
        actionTextColor = COLOR_MESSAGE;
        if(!"".equals(msg)) {
            setMessage(msg);
        }
        show();
    }

    /**
     * Show the snackbar with error style.
     */
    public void showError(String msg) {
        bgColor = COLOR_ERROR;
        messageColor = COLOR_MESSAGE;
        actionTextColor = COLOR_MESSAGE;
        if(!"".equals(msg)) {
            setMessage(msg);
        }
        show();
    }

    /**
     * Dismiss the snackbar.
     */
    public static void dismiss() {
        if (sReference != null && sReference.get() != null) {
            sReference.get().dismiss();
            sReference = null;
        }
    }

}
