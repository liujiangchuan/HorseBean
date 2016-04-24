package com.ll.horsebean.daogenerator.daofactory.ObjDaoFactorys;

import com.ll.horsebean.daogenerator.daofactory.AbsObjDaoFactory;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class BookDaoFactory extends AbsObjDaoFactory
{
	private static final String ENTITY_NAME = "Book";
	private static final String TABLE_NAME = "T_BOOK";

	@Override
	public void createDaoObject(Schema schema)
	{
		if (schema != null)
		{
			Entity entity = schema.addEntity(ENTITY_NAME);
			entity.setTableName(TABLE_NAME);
			entity.addStringProperty("content_id").primaryKey();
			entity.addStringProperty("name");
			entity.addStringProperty("cover_url");
			entity.addStringProperty("brief");
			entity.addStringProperty("author");
		}
	}
}
