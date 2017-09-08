package com.bawei.myapplication;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bawei.myapplication.otherMain.WebViewActivity;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;


public class MyFragment extends Fragment implements XListView.IXListViewListener {
    private adapter ada;
    private View v;
    private XListView xlv;
    private String murl = "http://v.juhe.cn/toutiao/index";
    private List<bean.ResultBean.DataBean> list = new ArrayList<>();
    private String u;
    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (v == null) {
            v = View.inflate(getActivity(), R.layout.fragment_layout, null);
        }
        xlv = v.findViewById(R.id.xlistview);
        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        Bundle bundle=getArguments();
        if(bundle!=null){
            u = bundle.getString("u");

        }
        xlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url",list.get(i).url);

                startActivity(intent);
            }
        });

        xutilpo();
        return v;
    }


    private void xutilpo() {
        RequestParams pa = new RequestParams(murl);
        pa.addParameter("key", "22a108244dbb8d1f49967cd74a0c144d");
        pa.addParameter("type",u);
        x.http().get(pa, new Callback.CommonCallback<String>() {



            @Override
            public void onSuccess(String result) {


                Gson gson = new Gson();
                bean bean = gson.fromJson(result.toString(), bean.class);
                list = bean.result.data;
                ada = new adapter();
                xlv.setAdapter(new adapter());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {

            }
        });

    }


    @Override
    public void onRefresh() {

        xlv.stopRefresh();


    }

    @Override
    public void onLoadMore() {

    }

    class adapter extends BaseAdapter {
        private View v;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
             v =  View.inflate(getActivity(), R.layout.xlv_item, null);
            TextView tv = v.findViewById(R.id.texteeeeeeeeeee);
            ImageView img = v.findViewById(R.id.img);
            tv.setText(list.get(i).title);
            ImageLoader.getInstance().displayImage(list.get(i).thumbnail_pic_s, img);
            return v;
        }

    }
}

