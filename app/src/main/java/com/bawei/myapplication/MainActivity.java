package com.bawei.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;

import com.bawei.myapplication.frag.fragmrnt1;
import com.bawei.myapplication.frag.fragmrnt2;
import com.bawei.myapplication.otherMain.Tuozhuai;
import com.kson.slidingmenu.SlidingMenu;
import com.kson.slidingmenu.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private HorizontalScollTabhost mTabhost;
    private List<CategoryBean> beans;
    private List<Fragment> fragmentList;
    private SlidingMenu menu;
    private ImageView yejian;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabhost = (HorizontalScollTabhost) findViewById(R.id.tabhost);
        initData();
        mune();
        initview();
        getSupportActionBar().hide();

        // getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
    private void initview() {
        ImageView img1= (ImageView) findViewById(R.id.img1);
        ImageView img2= (ImageView) findViewById(R.id.img2);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        yejian = (ImageView) findViewById(R.id.yejian);
       ImageView jihao = (ImageView) findViewById(R.id.img);
        jihao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Tuozhuai.class);
                startActivity(intent);
            }
        });


    }private void mune() {
        menu=new SlidingMenu(this);
        menu.setMenu(R.layout.lift_fragment);

        getSupportFragmentManager().beginTransaction().replace(R.id.le,new fragmrnt1()).commit();

        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.SLIDING_CONTENT);
        menu.setBehindOffsetRes(R.dimen.bbbb);

        menu.setSecondaryMenu(R.layout.right_fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.right,new fragmrnt2()).commit();
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);

    }

    private void initData() {
        fragmentList = new ArrayList<>();
        beans = new ArrayList<>();
        CategoryBean bean = new CategoryBean();
        bean.id = "top";
        bean.name = "头条";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "娱乐";
        bean.id = "yule";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "社会";
        bean.id = "shehui";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "体育";
        bean.id = "tiyu";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "科技";
        bean.id = "keji";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "财经";
        bean.id = "caijing";
        beans.add(bean);
        for (int i = 0; i < 6; i++) {
            MyFragment fragment1=new MyFragment();
            Bundle bundle=new Bundle();


            String url = beans.get(i).id;
            bundle.putString("u",url);
            fragment1.setArguments(bundle);
            fragmentList.add(fragment1);

        }



        mTabhost.diaplay(beans, fragmentList);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img1:
                menu.showMenu();
                break;
            case R.id.img2:
                menu.showSecondaryMenu();
                break;
        }
    }
}
