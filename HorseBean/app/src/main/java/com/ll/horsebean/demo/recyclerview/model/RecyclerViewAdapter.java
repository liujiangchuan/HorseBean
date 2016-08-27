package com.ll.horsebean.demo.recyclerview.model;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ll.horsebean.R;
import com.ll.horsebean.db.framework.Book;
import com.ll.services.imageload.FImageLoader;
import com.ll.services.view.recyclerview.FBaseAdapter;
import com.ll.services.view.recyclerview.FViewHolder;

import java.util.List;

/**
 * Created by Liujc on 2016/7/26.
 * Email liujiangchuan@hotmail.com
 */
public class RecyclerViewAdapter extends FBaseAdapter<Book, FViewHolder>
{
    public RecyclerViewAdapter(List list)
    {
        super(list);
    }

    @Override public int getCustomViewType(int position)
    {
        return 0;
    }

    @Override public FViewHolder createCustomViewHolder(ViewGroup parent, int viewType)
    {
        return new FViewHolder(R.layout.listitem_recyclerview);
    }

    @Override public void bindCustomViewHolder(FViewHolder holder, int position)
    {
        //get data
        Book book = getItem(position);
        String cover = book.getCover_url();
        String name = book.getName();
        //get view
        ImageView ivCover = holder.getView(R.id.iv_cover);
        TextView tvName = holder.getView(R.id.tv_name);
        //bind
        FImageLoader.getInstance().loadImage(cover, ivCover);
        tvName.setText(name);
    }
}
