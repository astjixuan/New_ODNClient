package com.cn.nj.putian.newodnclient.ui.main.view;

import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.cn.nj.putian.newodnclient.R;
import com.cn.nj.putian.newodnclient.base.mvp.BaseMvpActivity;
import com.cn.nj.putian.newodnclient.ui.main.adapter.ViewPagerAdapter;
import com.cn.nj.putian.newodnclient.ui.main.contract.FirstContract;
import com.cn.nj.putian.newodnclient.ui.main.presenter.FirstPresenter;
import com.cn.nj.putian.newodnclient.ui.main.view.fragment.AlarmFragment;
import com.cn.nj.putian.newodnclient.ui.main.view.fragment.OrderFragment;
import com.cn.nj.putian.newodnclient.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 */
public final class FirstActivity extends BaseMvpActivity<FirstPresenter> implements FirstContract.View,BottomNavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener{

    private BottomNavigationView bottomNavigationView;
    private NoScrollViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBottomNavigation();
        initViewPageListener();
    }

    @Override
    public void initView() {
        bottomNavigationView = findViewById(R.id.first_act_bottom);
        viewPager = findViewById(R.id.first_act_viewPage);
    }

    @Override
    public void initData() {
        initViewPageFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_first;
    }

    @Override
    public void initPresenter() {
        mPresenter = new FirstPresenter();
    }

    private void initViewPageFragment() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        List<Fragment> list = new ArrayList<>();
        list.add(OrderFragment.newFragment());
        list.add(AlarmFragment.newFragment());
        //list.add(ViewPageFragment.newFragment("卡片"));
        //list.add(ViewPageFragment.newFragment("个人"));
        viewPagerAdapter.setList(list);
        viewPager.setAdapter(viewPagerAdapter);
        //默认时ViewPager初始化时会预加载前后的2个页面
        viewPager.setOffscreenPageLimit(1);//预加载几个，至少加载1个
    }

    private void initBottomNavigation(){
        //动画效果
        //labeled : 保持所有文字便签显示
        //unlabeled ：只显示图标
        //selected ：在选中的时候显示文字标签，有动画效果
        //auto : 在 1-3 个按钮时使用 labeled ，大于 3 个按钮使用 selected
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setItemIconTintList(null);  //去掉默认点击背景色
        bottomNavigationView.setOnNavigationItemSelectedListener(this);//点击事件
    }

    private void initViewPageListener() {
        viewPager.addOnPageChangeListener(this);
        //禁止ViewPager滑动
        viewPager.setNoScroll(true);
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public void finishFragment() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bottom_nav_home:
                //首页
                viewPager.setCurrentItem(0);
                return true;

            case R.id.bottom_nav_alarm:
                //告警
                viewPager.setCurrentItem(1);
                return true;

            case R.id.bottom_nav_res:
                //资源
                viewPager.setCurrentItem(2);
                return true;

            case R.id.bottom_nav_my:
                //个人
                viewPager.setCurrentItem(3);
                return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        bottomNavigationView.getMenu().getItem(i).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }
}
