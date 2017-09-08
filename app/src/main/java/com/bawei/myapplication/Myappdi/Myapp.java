package com.bawei.myapplication.Myappdi;

import android.app.Application;

import com.mob.MobSDK;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 蒋六六 on 2017/8/31.
 */

public class Myapp extends Application {
    private String key="209dde57fe028";
    private String ser="4d11145d92f117a0f430a3736189a386";
    @Override
    public void onCreate() {
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        super.onCreate();
        initsli();
        initimg();
        keyz();
        xinlang();

    }

    private void xinlang() {
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        UMShareAPI.get(this);
    }

    private void keyz() {
        MobSDK.init(this,key,ser);
    }

    private void initimg() {
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        ImageLoaderConfiguration con=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(con);
    }

    private void initsli() {
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
