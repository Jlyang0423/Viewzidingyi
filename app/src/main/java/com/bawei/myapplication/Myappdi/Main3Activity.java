package com.bawei.myapplication.Myappdi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.myapplication.R;


import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvMore;
    private EditText mEtPhone;
    private TextView mPhoneMsg;
    private EditText mEtYanzheng;
    private Button mBtnMoreReg;
    private TextView mTvMoreTip;
    private CheckBox mCbMore;
    private TextView mMoreReg;
    private TextView mMore;
    private ImageView mMoreWeixin;
    private ImageView mMoreQq;
    private ImageView mMoreWeibo;
    private ImageView mMoreTianyi;
    private ImageView mMoreEmail;
    private EventHandler eventHandler;

    private int sum=60;
    private final  int Sleep=1000;
    Handler timeHander=new Handler();
        Runnable timeRunable=new Runnable() {
            @Override
            public void run() {
                sum--;
                if(sum==0){
                    //移除线程
                    timeHander.removeCallbacks(this);
                    sum=60;
                    //设置点击
                    mPhoneMsg.setEnabled(true);
                    mPhoneMsg.setText("再次获取");
                }else {
                    mPhoneMsg.setEnabled(false);
                    mPhoneMsg.setText(sum+"s");
                    timeHander.postDelayed(this,Sleep);
                }
            }
        };







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_item);
        initView();
     //短信验证码
        registerS();
       // feikong();

    }

    private void registerS() {
//注册监听器
        eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if(data instanceof Throwable){
                   Throwable throeable= (Throwable) data;
                   String msg = throeable.getMessage();
               }else {
               }
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }
    private void feikong() {
        if(mEtPhone.getText().toString()==null){
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();

        }
    }

    private void initView() {
        mTvMore = (TextView) findViewById(R.id.tv_more);
        mTvMore.setOnClickListener(this);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPhone.setOnClickListener(this);
        mPhoneMsg = (TextView) findViewById(R.id.phone_msg);
        mPhoneMsg.setOnClickListener(this);
        mEtYanzheng = (EditText) findViewById(R.id.et_yanzheng);
        mEtYanzheng.setOnClickListener(this);
        mBtnMoreReg = (Button) findViewById(R.id.btn_more_reg);
        mBtnMoreReg.setOnClickListener(this);
        mTvMoreTip = (TextView) findViewById(R.id.tv_more_tip);
        mTvMoreTip.setOnClickListener(this);
        mCbMore = (CheckBox) findViewById(R.id.cb_more);
        mCbMore.setOnClickListener(this);
        mMoreReg = (TextView) findViewById(R.id.more_reg);
        mMoreReg.setOnClickListener(this);
        mMore = (TextView) findViewById(R.id.more);
        mMore.setOnClickListener(this);
        mMoreWeixin = (ImageView) findViewById(R.id.more_weixin);
        mMoreWeixin.setOnClickListener(this);
        mMoreQq = (ImageView) findViewById(R.id.more_qq);
        mMoreQq.setOnClickListener(this);
        mMoreWeibo = (ImageView) findViewById(R.id.more_weibo);
        mMoreWeibo.setOnClickListener(this);
        mMoreTianyi = (ImageView) findViewById(R.id.more_tianyi);
        mMoreTianyi.setOnClickListener(this);
        mMoreEmail = (ImageView) findViewById(R.id.more_email);
        mMoreEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_more:
                break;
            case R.id.et_phone:
                break;
            case R.id.phone_msg:
               // feikong();
                  SMSSDK.getVerificationCode("86",mEtPhone.getText().toString());
                timeHander.postDelayed(timeRunable, Sleep);


                break;
            case R.id.et_yanzheng:
                break;
            case R.id.btn_more_reg:
                break;
            case R.id.tv_more_tip:
                break;
            case R.id.cb_more:
                break;
            case R.id.more_reg:
                break;
            case R.id.more:
                break;
            case R.id.more_weixin:
                break;
            case R.id.more_qq:
                break;
            //新浪
            case R.id.more_weibo:
                break;
            case R.id.more_tianyi:
                break;
            case R.id.more_email:
                break;
        }
    }
}
