package com.ll.horsebean.demo.database.model;

import com.ll.horsebean.db.baseAccess.BookDaoBase;
import com.ll.horsebean.db.framework.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liujc on 2016/8/12.
 * Email liujiangchuan@hotmail.com
 */
public class DatabaseModel
{
    public List<Book> mBooks;

    public DatabaseModel()
    {
        mBooks = new ArrayList<>();
    }

    public void reloadData()
    {
        mBooks.clear();
        mBooks.addAll(BookDaoBase.getInstance().queryList());
    }
}
