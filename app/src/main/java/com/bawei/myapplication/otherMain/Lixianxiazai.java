package com.bawei.myapplication.otherMain;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.myapplication.CategoryBean;
import com.bawei.myapplication.MainActivity;
import com.bawei.myapplication.R;
import com.bawei.myapplication.sqlite;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蒋六六 on 2017/9/5.
 */

public class Lixianxiazai extends AppCompatActivity{
    private List<CategoryBean> list;
    private RecyclerView rv;

    private TextView xiazai;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lixian_item);

        initview();
        list = new ArrayList<>();
        CategoryBean bean = new CategoryBean();
        bean.id = "top";
        bean.name = "头条";
        list.add(bean);
        bean = new CategoryBean();
        bean.name = "娱乐";
        bean.id = "yule";
        list.add(bean);
        bean = new CategoryBean();
        bean.name = "社会";
        bean.id = "shehui";
        list.add(bean);
        bean = new CategoryBean();
        bean.name = "体育";
        bean.id = "tiyu";
        list.add(bean);
        bean = new CategoryBean();
        bean.name = "科技";
        bean.id = "keji";
        list.add(bean);
        bean = new CategoryBean();
        bean.name = "财经";
        bean.id = "caijing";
        list.add(bean);

        System.out.println(list.size());
        rv.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter=new Adapter();
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos, View view) {
                CheckBox checkbox = view.findViewById(R.id.checkbox);
                CategoryBean c = list.get(pos);
                if (checkbox.isChecked()) {
                    checkbox.setChecked(false);
                    c.state = false;
                } else {
                    checkbox.setChecked(true);
                    c.state = true;
                }

                //修改原有list的数据，根据pos，设置新的对象，然后更新list
                list.set(pos, c);

            }
        });

    }

    private void loadData(final String type) {
        String murl = "http://v.juhe.cn/toutiao/index";
        RequestParams params = new RequestParams(murl);
        params.addParameter("key","22a108244dbb8d1f49967cd74a0c144d");
        params.addParameter("type", type);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {


                //下载成功后保存到数据库
                saveData(type, result);

            }

            private void saveData(String type, String result) {
                sqlite dbhelper = new sqlite(Lixianxiazai.this);
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                //此处如果有10万条，这么操作合适吗？--不合适，怎么办？-事务提交机制
                ContentValues values = new ContentValues();
                values.put("type", type);
                values.put("content", result);
                db.insert("news", null, values);
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

    private void initview() {
        rv = (RecyclerView) findViewById(R.id.recyclerview);
        xiazai = (TextView) findViewById(R.id.xiazai);
        xiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list != null && list.size() > 0) {
                    for (CategoryBean catogray : list) {
                        if (catogray.state) {//判断是否是选中状态，如果选中则执行下载操作
                            loadData(catogray.id);
                        }

                    }
                }
                for (CategoryBean catogray : list) {

                    System.out.println("state====" + catogray.state);

                }
            }
        });


    }
    class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

        private OnItemClickListener onItemClickListener;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = View.inflate(Lixianxiazai.this, R.layout.lx_lv_item, null);
            MyViewHolder myViewHolder = new MyViewHolder(v);

            v.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    onItemClickListener.onItemClickListener((Integer) view.getTag(),view);
                }
            });

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.name.setText(list.get(position).name);
            holder.itemView.setTag(position);

        }



        @Override
        public int getItemCount() {
            return list.size();
        }



        class MyViewHolder extends RecyclerView.ViewHolder {

            private final TextView name;
            private final CheckBox check;

            public MyViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                check = itemView.findViewById(R.id.checkbox);
            }
        }
        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.onItemClickListener=onItemClickListener;
        }
    }



    //写接口


    public interface OnItemClickListener {
        void onItemClickListener(int pos, View view);
    }
}

