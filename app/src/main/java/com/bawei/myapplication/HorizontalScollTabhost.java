package com.bawei.myapplication;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蒋六六 on 2017/8/30.
 */

public class HorizontalScollTabhost extends LinearLayout implements ViewPager.OnPageChangeListener {

    private final Context mContext;
    private  Context mcontext;

    private List<CategoryBean> list;
    private List<Fragment> fragmentList;
    private int count;
   // private MyPager myPager;

    private LinearLayout mMenuLayout;
    private int mBgColor;
    private HorizontalScrollView hscrollview;
    private ViewPager viewpager;
    private LinearLayout viewById;
    private List<TextView> topViews;
    private Mypager myPager;

    public HorizontalScollTabhost(Context context) {
        this(context,null);
    }
    public HorizontalScollTabhost(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalScollTabhost(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(context,attrs);
    }
//初始化自定义属性和view
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalScollTabhost);
        mBgColor = typedArray.getColor(R.styleable.HorizontalScollTabhost_top_background, 0xffffff);
       typedArray.recycle();
        initview();
    }
//初始化view
    private void initview() {
        View view=LayoutInflater.from(mContext).inflate(R.layout.horizontal_scroll_tabhost,this,true);
        mMenuLayout = view.findViewById(R.id.layout_menu);
        hscrollview = view.findViewById(R.id.horizontalScrollView);
        viewpager = view.findViewById(R.id.viewpager_fragment);
        viewpager.addOnPageChangeListener(this);
        viewById = view.findViewById(R.id.layout_menu);
    }

    /**
     * 供调用者调用，保证数据独立
     * @param list
     * @param fragments
     */

    public void diaplay(List<CategoryBean> list, List<Fragment> fragments){
        this.list=list;
        this.count=list.size();
        this.fragmentList=fragments;
        topViews=new ArrayList<>(count);
        drawUi();
    }
/*
* 绘制页面所有元素
* */
    private void drawUi() {
        drawHorizontal();

        drawViewpager();

    }
//绘制viewpager
    private void drawViewpager() {
    myPager=new Mypager(((FragmentActivity)mContext).getSupportFragmentManager());
        viewpager.setAdapter(myPager);

    }

    private void drawHorizontal() {
        mMenuLayout.setBackgroundColor(mBgColor);

        for (int i=0;i<count;i++){
            CategoryBean bean = (CategoryBean) list.get(i);
            final TextView tv= (TextView) View.inflate(mContext,R.layout.news_top_tv_item,null);
            tv.setText(bean.name);
            final int finalI=i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击移动到当前fragment
                    viewpager.setCurrentItem(finalI);
                    //文字居中
                    moveItemToCenter(tv);
                }
            });
            mMenuLayout.addView(tv);
            topViews.add(tv);
            topViews.get(0).setSelected(true);

        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    private void moveItemToCenter(TextView tv) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int[] locations = new int[2];
        tv.getLocationInWindow(locations);
        int rbWidth = tv.getWidth();
        hscrollview.smoothScrollBy((locations[0] + rbWidth / 2 - screenWidth / 2),
                0);
    }
    @Override
    public void onPageSelected(int position) {
        if(mMenuLayout!=null && mMenuLayout.getChildCount()>0){

            for (int i=0;i<mMenuLayout.getChildCount();i++){
                if(i==position){
                    mMenuLayout.getChildAt(i).setSelected(true);
                }else {
                    mMenuLayout.getChildAt(i).setSelected(false);
                }
            }
        }
//移动view水平居中
moveItemToCenter(topViews.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }




    class Mypager extends FragmentPagerAdapter {

        public Mypager(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
