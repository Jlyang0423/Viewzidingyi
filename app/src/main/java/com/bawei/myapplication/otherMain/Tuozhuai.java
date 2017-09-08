package com.bawei.myapplication.otherMain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.bawei.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Tuozhuai extends AppCompatActivity {
    private List<ChannelBean> list;
    private ChannelBean channel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuozhuai);
        getSupportActionBar().hide();
        list=new ArrayList<>();
        for (int i=0;i<30;i++){
            if(i<10){
                channel = new ChannelBean("条目" + i, true);
            }
            else {
                channel=new ChannelBean("条目"+i,true);
            }
            list.add(channel);
        }

        ChannelActivity.startChannelActivity(Tuozhuai.this,list);

    }
    }

