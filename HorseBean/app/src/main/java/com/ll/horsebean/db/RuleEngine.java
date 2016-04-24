package com.ll.horsebean.db;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ll.horsebean.db.framework.DaoMaster;
import com.ll.services.helper.FLog;
import com.ll.services.util.FFileUtil;

import java.util.List;

public class RuleEngine
{
	private static final String PATH = "databaseRules.txt";

	public void start(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		//params
		SQLiteDatabase _db = db;
		int _oldVersion = oldVersion;
		int _newVersion = newVersion;
		//get json from file
		String string_rules = FFileUtil.readAssetsFile(PATH);
		//analysis rules
		try
		{
			Gson gson = new Gson();
			RuleEngineBean ruleEngineBean = gson.fromJson(string_rules, RuleEngineBean.class);
			List<RuleEngineStepsBean> ruleEngineStepsList = ruleEngineBean.steps;
			int ruleEngineStepsSize = ruleEngineStepsList.size();
			for (int i = 0; i < ruleEngineStepsSize; i++)
			{
				RuleEngineStepsBean ruleEngineStepsBean = ruleEngineStepsList.get(i);
				int iOldVersion = ruleEngineStepsBean.oldVersion;
				int iNewVersion = ruleEngineStepsBean.newVersion;
				FLog.i("_oldVersion: " + _oldVersion + ", _newVersion: " + _newVersion + ", iOldVersion: " + iOldVersion + ", iNewVersion: " + iNewVersion);

				if (_oldVersion == iOldVersion && _newVersion >= iNewVersion)
				{
					//add fields
					List<RuleEngineFieldsBean> ruleEngineFieldsList = ruleEngineStepsBean.newFields;
					int ruleEngineFieldsSize = ruleEngineFieldsList.size();
					for (int j = 0; j < ruleEngineFieldsSize; j++)
					{
						RuleEngineFieldsBean ruleEngineFieldsBean = ruleEngineFieldsList.get(j);
						String tableName = ruleEngineFieldsBean.tableName;
						String fieldName = ruleEngineFieldsBean.fieldName;
						String type = ruleEngineFieldsBean.type;
						String defaultValue = ruleEngineFieldsBean.defaultValue;

						String sql = getAddFieldSQL(tableName, fieldName, type, defaultValue);
						_db.execSQL(sql);
						FLog.i("sql: " + sql);
					}
					//create tables
					boolean hasNewTable = ruleEngineStepsBean.hasNewTable;
					if (hasNewTable)
					{
						DaoMaster.createAllTables(_db, true);
						FLog.i("create tables");
					}
					//upgrade to new version
					_oldVersion = iNewVersion;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			FLog.e("start Exception: " + e.getMessage());
		}
	}

	/**
	 * Get add field sql.
	 * @param tableName
	 * @param fieldName
	 * @param type
	 * @param defaultValue
	 * @return
	 */
	private String getAddFieldSQL(String tableName, String fieldName, String type, String defaultValue)
	{
		String ret = null;
		if (TextUtils.isEmpty(defaultValue))
		{
			String format = "ALTER TABLE %s ADD %s %s;";
			ret = String.format(format, tableName, fieldName, type);
		}
		else
		{
			String format = "ALTER TABLE %s ADD %s %s DEFAULT '%s';";
			ret = String.format(format, tableName, fieldName, type, defaultValue);
		}
		return ret;
	}

	private class RuleEngineBean
	{
		private int engineVersion;
		private List<RuleEngineStepsBean> steps;
	}

	private class RuleEngineFieldsBean
	{
		private String tableName;
		private String fieldName;
		private String type;
		private String defaultValue;
	}

	private class RuleEngineStepsBean
	{
		private int oldVersion;
		private int newVersion;
		private List<RuleEngineFieldsBean> newFields;
		private boolean hasNewTable;
	}
}