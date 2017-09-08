package com.bawei.myapplication.frag;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.myapplication.MainActivity;
import com.bawei.myapplication.Myappdi.Main3Activity;
import com.bawei.myapplication.R;

import static com.bawei.myapplication.R.id.textView;

/**
 * Created by 蒋六六 on 2017/8/31.
 */

public class fragmrnt1 extends Fragment {

    private View v;
    private TextView tv;
    private SharedPreferences config;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = View.inflate(getActivity(), R.layout.lift_fragment_item,null);
        tv = v.findViewById(R.id.textView);

        ImageView img=v.findViewById(R.id.yejian);
        config = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Main3Activity.class);
                startActivity(intent);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (config.getBoolean("night",false)){
                    config.edit().putBoolean("night",false).commit();
                    ((MainActivity) getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else {
                    config.edit().putBoolean("night", true).commit();
                    ((MainActivity) getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }

            }
        });

        return v;
    }







}


