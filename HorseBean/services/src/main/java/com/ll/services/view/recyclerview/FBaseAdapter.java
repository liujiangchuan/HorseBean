package com.ll.services.view.recyclerview;

import java.util.List;

/**
 * Created by Liujc on 2016/7/25.
 * Email liujiangchuan@hotmail.com
 */
public abstract class FBaseAdapter<M, VH extends ViewHolder> extends FAbsAdapter<M, VH>
{
    private List<M> mDataList;
}
