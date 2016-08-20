package com.ll.horsebean.demo.recyclerview.model;

import com.ll.horsebean.db.framework.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class RecyclerViewModel
{
    public List<Book> mBooks;

    public RecyclerViewModel()
    {
        mBooks = new ArrayList<>();
    }

    public void reloadData()
    {
        mBooks.clear();
        for (int i = 0; i < 100; i++)
        {
            Book book = new Book();
            book.setName("Index-" + i);
            book.setCover_url("");
            mBooks.add(book);
        }
    }
}
