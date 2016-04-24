package com.ll.horsebean.module.main.model;

import android.widget.TextView;

import com.ll.horsebean.R;
import com.ll.services.adapter.FBaseAdapter;
import com.ll.services.adapter.ViewHolder;
import com.ll.services.http.FHttpBean;

import java.util.List;

/**
 * Created by User on 2016/4/25.
 */
public class MainAdapter extends FBaseAdapter<FHttpBean>
{
    public MainAdapter(List<FHttpBean> listData, int layoutId)
    {
        super(listData, layoutId);
    }

    @Override protected void fillData(ViewHolder holder, int position)
    {
        //get view
        TextView test = holder.getView(R.id.f_titlebar_id);
        //get data
        FHttpBean bean = mListData.get(position);
        //set
        test.setText(bean.toString());
    }
}
