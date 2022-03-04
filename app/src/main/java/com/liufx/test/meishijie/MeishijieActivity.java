package com.liufx.test.meishijie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.liufx.delegationadapter.DelegationAdapter;
import com.liufx.test.R;
import com.liufx.test.meishijie.adapter.MeishiChannelAdapterDelegate;
import com.liufx.test.meishijie.adapter.MeishiSancanAdapterDelegate;
import com.liufx.test.meishijie.adapter.MeishiZhuantiAdapterDelegate;
import com.liufx.test.meishijie.adapter.MeishiZhuantiTitleAdapterDelegate;
import com.liufx.test.meishijie.bean.Meishi;
import com.liufx.test.util.LocalFileUtils;
import com.liufx.extras.span.SpanDelegationAdapter;
/**
 * MeishijieActivity
 *
 * @author liufx, Created on 2018-06-13 10:59:25
 *         Major Function：<b></b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author liufx，Modified Date Modify Content:
 */

public class MeishijieActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DelegationAdapter delegationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meishijie);
        initRecyclerView();
        initData();
    }

    private void initRecyclerView() {
        recyclerView = this.findViewById(R.id.recycler_view);
        // 设置LayoutManager
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        // 设置Adapter
        delegationAdapter = new SpanDelegationAdapter();
        // 添加委托Adapter
        delegationAdapter.addDelegate(new MeishiChannelAdapterDelegate());
        delegationAdapter.addDelegate(new MeishiSancanAdapterDelegate());
        delegationAdapter.addDelegate(new MeishiZhuantiTitleAdapterDelegate());
        delegationAdapter.addDelegate(new MeishiZhuantiAdapterDelegate());
        recyclerView.setAdapter(delegationAdapter);
    }

    private void initData() {
        String meishiStr = LocalFileUtils.getStringFormAsset(this, "meishi.json");
        Meishi meishi = new Gson().fromJson(meishiStr, Meishi.class);
        delegationAdapter.addDataItems(meishi.channel);
        delegationAdapter.addDataItem(meishi.sancan);
        delegationAdapter.addDataItem(meishi.zhuanti);
        delegationAdapter.addDataItems(meishi.zhuanti.items);
    }
}
