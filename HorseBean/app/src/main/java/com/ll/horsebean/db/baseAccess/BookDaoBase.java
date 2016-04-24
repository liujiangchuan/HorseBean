package com.ll.horsebean.db.baseAccess;

import com.ll.horsebean.db.DaoCreator;
import com.ll.horsebean.db.framework.Book;
import com.ll.horsebean.db.framework.BookDao;
import com.ll.horsebean.db.framework.BookDao.Properties;

import java.util.ArrayList;
import java.util.List;

public class BookDaoBase
{
	private BookDao mBookDao;

	private static class SingletonClassInstance
	{
		private static final BookDaoBase self = new BookDaoBase();
	}

	public static BookDaoBase getInstance()
	{
		return SingletonClassInstance.self;
	}

	private BookDaoBase()
	{
		mBookDao = DaoCreator.getDaoSession().getBookDao();
	}

	//-------------------------------------------------------     Retrieve     -----------------------------------------------------------------
	/**
	 * Get list.
	 * @return List or null
	 */
	public List<Book> queryList()
	{
		List<Book> bookList = new ArrayList<Book>();
		try
		{
			bookList = mBookDao.queryBuilder().list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return bookList;
	}

	/**
	 * Get record by key, the key is content_id.
	 * @param content_id
	 * @return 1 record or null
	 */
	public Book queryUniqueByKey(String content_id)
	{
		Book book = null;
		try
		{
			book = mBookDao.queryBuilder().where(Properties.Content_id.eq(content_id)).unique();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return book;
	}

	/**
	 * Get record count.
	 * @return defalt value is -1l
	 */
	public long count()
	{
		long ret = -1l;
		try
		{
			ret = mBookDao.queryBuilder().count();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}

	//-------------------------------------------------------     Create/Update     -----------------------------------------------------------------
	/**
	 * Insert or replace.
	 * @param book
	 */
	public long insertOrReplace(Book book)
	{
		long ret = -1;
		try
		{
			ret = mBookDao.insertOrReplace(book);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Insert.
	 * @param book
	 */
	public void insert(Book book)
	{
		try
		{
			mBookDao.insert(book);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Insert list in transaction.
	 * @param bookList
	 */
	public void insertInTx(List<Book> bookList)
	{
		try
		{
			mBookDao.insertInTx(bookList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Update.
	 * @param book
	 */
	public void update(Book book)
	{
		try
		{
			mBookDao.update(book);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Update list in transaction.
	 * @param bookList
	 */
	public void updateInTx(List<Book> bookList)
	{
		try
		{
			mBookDao.updateInTx(bookList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//-------------------------------------------------------     Delete     -----------------------------------------------------------------
	/**
	 * Delete by key, the key is content_id.
	 * @param content_id
	 */
	public void deleteByKey(String content_id)
	{
		try
		{
			mBookDao.deleteByKey(content_id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete all.
	 */
	public void deleteAll()
	{
		try
		{
			mBookDao.deleteAll();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
