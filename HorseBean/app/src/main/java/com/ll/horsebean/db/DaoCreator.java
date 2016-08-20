package com.ll.horsebean.db;

import android.database.sqlite.SQLiteDatabase;

import com.ll.horsebean.MyApp;
import com.ll.horsebean.db.framework.DaoMaster;
import com.ll.horsebean.db.framework.DaoMaster.OpenHelper;
import com.ll.horsebean.db.framework.DaoSession;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class DaoCreator
{
	private static final String PATH = "database.db";

	private static class InnerInstance
	{
		private static DaoMaster daoMaster;
		private static DaoSession daoSession;

		static
		{
			OpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.getAppContext(), PATH, null);
			daoMaster = new DaoMaster(openHelper.getWritableDatabase());
			daoSession = daoMaster.newSession();
		}
	}

	public static DaoMaster getDaoMaster()
	{
		return InnerInstance.daoMaster;
	}

	public static DaoSession getDaoSession()
	{
		return InnerInstance.daoSession;
	}

	public static void close()
	{
		if (InnerInstance.daoSession != null)
		{
			InnerInstance.daoSession = null;
		}
		if (InnerInstance.daoMaster != null)
		{
			if (InnerInstance.daoMaster.getDatabase().isOpen())
			{
				InnerInstance.daoMaster.getDatabase().close();
			}
			InnerInstance.daoMaster = null;
		}
	}

	public static void createAllIndex(SQLiteDatabase db, boolean ifNotExists)
	{
		//		final String constraint = ifNotExists ? "IF NOT EXISTS " : "";
		//		db.execSQL("CREATE INDEX " + constraint + "INDEX_DOWNLOAD ON T_DOWNLOAD(CONTENT_ID, CHAPTER_ID)");
	}

	public static void dropAllIndex(SQLiteDatabase db, boolean ifExists)
	{
		//		final String constraint = ifExists ? "IF EXISTS " : "";
		//		db.execSQL("DROP INDEX " + constraint + "INDEX_DOWNLOAD");
	}
}
