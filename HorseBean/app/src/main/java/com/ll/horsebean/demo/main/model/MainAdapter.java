package com.ll.horsebean.demo.main.model;

import android.widget.TextView;

import com.ll.horsebean.R;
import com.ll.horsebean.demo.main.model.bean.ActivityBean;
import com.ll.services.view.scrollview.FBaseAdapter;
import com.ll.services.view.scrollview.ViewHolder;

import java.util.List;

/**
 * Created by Liujc on 2016/5/25.
 * Email liujiangchuan@hotmail.com
 */
public class MainAdapter extends FBaseAdapter<ActivityBean>
{
    public MainAdapter(List<ActivityBean> listData, int layoutId)
    {
        super(listData, layoutId);
    }

    @Override protected void fillData(ViewHolder holder, int position)
    {
        TextView tvActivity = holder.getView(R.id.tv_activity_name);
        ActivityBean activityBean = mListData.get(position);
        tvActivity.setText(activityBean.activityName);
    }
}
