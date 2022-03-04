package com.liufx.test.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liufx.delegationadapter.DelegationAdapter;
import com.liufx.test.R;
import com.liufx.test.fallback.FallbackActivity;
import com.liufx.test.footer.FooterActivity;
import com.liufx.test.meishijie.MeishijieActivity;
import com.liufx.test.multidataandtype.binding.BindingGoodsActivity;
import com.liufx.test.samedata.SameDataActivity;

import java.util.Arrays;
import java.util.List;

/**
 * HomeActivity
 *
 * @author liufx
 * Major Function：<b></b>
 * <p/>
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author liufx，Modified Date Modify Content:
 */

public class HomeActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DelegationAdapter mDelegationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initRecyclerView();
        initData();
    }

    private void initRecyclerView() {
        mRecyclerView = this.findViewById(R.id.recycler_view);
        // 设置LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // 添加分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        // 设置Adapter
        mDelegationAdapter = new DelegationAdapter();
        // 向Adapter中注册委托Adapter
        mDelegationAdapter.addDelegate(new HomeAdapterDelegate(this));
        mRecyclerView.setAdapter(mDelegationAdapter);
    }

    private void initData() {
        String[] titles = {
//                "同一类型多种样式(新闻)",
//                "同一类型多种样式(新闻-dataBinding实现)",
//                "同一类型多种样式(聊天)",
//                "同一类型多种样式(聊天-dataBinding实现)",
                "不同数据类型多种样式",
                "同一数据多种类型",
//                "带头部数据的不同数据类型多样式",
                "带尾部数据的不同数据类型多样式",
                "带兜底的委托Adapter(未注册委托时的处理)",
                "美食杰",
//                "刷新加载",
        };
        List<String> titleList = Arrays.asList(titles);
        mDelegationAdapter.setDataItems(titleList);
    }

    public void onItemClick(View v, int position, String item) {
        switch (position) {
//            case 0:
//                startActivity(new Intent(this, NewsActivity.class));
//                break;
//            case 1:
//                startActivity(new Intent(this, BindingNewsActivity.class));
//                break;
//            case 2:
//                startActivity(new Intent(this, ChatActivity.class));
//                break;
//            case 3:
//                startActivity(new Intent(this, BindingChatActivity.class));
//                break;
            case 0:
                startActivity(new Intent(this, BindingGoodsActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, SameDataActivity.class));
                break;
//            case 6:
//                startActivity(new Intent(this, HeaderActivity.class));
//                break;
            case 3:
                startActivity(new Intent(this, FooterActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, FallbackActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, MeishijieActivity.class));
                break;
//            case 10:
//                startActivity(new Intent(this, RefreshLoadActivity.class));
//                break;
            default:
                // Can't reach;
                break;
        }
    }
}
