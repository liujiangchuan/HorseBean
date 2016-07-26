package com.ll.horsebean.demo.database.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.ll.horsebean.R;
import com.ll.horsebean.db.baseAccess.BookDaoBase;
import com.ll.horsebean.db.framework.Book;
import com.ll.services.util.FTimeUtil;

import java.util.List;

public class DatabaseActivity extends Activity
{
	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activity_database);

		final EditText editText = (EditText) findViewById(R.id.database_input);
		final TextView textView = (TextView) findViewById(R.id.database_output);

		((TextView) findViewById(R.id.database_add)).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Book book = new Book();
				book.setContent_id(FTimeUtil.getCurrentTime());
				book.setName(editText.getText().toString());
				BookDaoBase.getInstance().insert(book);
			}
		});

		((TextView) findViewById(R.id.database_show)).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				StringBuilder show = new StringBuilder();
				List<Book> bookList = BookDaoBase.getInstance().queryList();
				int size = bookList.size();
				for (int i = 0; i < size; i++)
				{
					Book book = bookList.get(i);
					show.append(book.getName());
					show.append(" , ");
				}
				textView.setText(show);
			}
		});
	}
}
