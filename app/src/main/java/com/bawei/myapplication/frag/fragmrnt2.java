package com.bawei.myapplication.frag;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.bawei.myapplication.R;
import com.bawei.myapplication.otherMain.Lixianxiazai;

/**
 * Created by 蒋六六 on 2017/8/31.
 */

public class fragmrnt2 extends Fragment {

    private View v;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = View.inflate(getActivity(), R.layout.right_fragment_item,null);
        RelativeLayout xiazai = v.findViewById(R.id.lx_xz);
        RelativeLayout Xt = v.findViewById(R.id.wl_ll);
        Switch switch_push = v.findViewById(R.id.switch_push);

        sp = getActivity().getSharedPreferences("kai", Context.MODE_PRIVATE);
        xiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Lixianxiazai.class);
                startActivity(intent);
            }
        });

        Xt.setOnClickListener(new View.OnClickListener() {

            private AlertDialog alertDialog;

            @Override
            public void onClick(View view) {

               new AlertDialog.Builder(getActivity()).setSingleChoiceItems(new String[]{"大图", "无图"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }

                }).show();
            }
        });


        if(switch_push.isChecked()){

             sp.edit().putBoolean("",false).commit();

        }else {

        }


        return v;
    }
}
