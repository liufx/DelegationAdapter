package com.liufx.test.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liufx.test.R;
import com.liufx.extras.ClickableAdapterDelegate;


/**
 * HomeAdapterDelegate
 *
 * @author liufx, Created on 2018-04-27 16:16:40
 *         Major Function：<b></b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author liufx，Modified Date Modify Content:
 */

public class HomeAdapterDelegate extends ClickableAdapterDelegate<String, HomeAdapterDelegate.ViewHolder> {

    private HomeActivity mActivity;

    public HomeAdapterDelegate(HomeActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position, final String item) {
        super.onBindViewHolder(holder, position, item);
        holder.tvContent.setText(item);
    }

    @Override
    public void onItemClick(View view, String item, int position) {
        mActivity.onItemClick(view, position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
