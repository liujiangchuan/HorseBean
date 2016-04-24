package com.ll.horsebean.daogenerator;

import java.util.ArrayList;
import java.util.List;

import com.ll.horsebean.daogenerator.daofactory.AbsObjDaoFactory;
import com.ll.horsebean.daogenerator.daofactory.ObjDaoFactorys.BookDaoFactory;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator
{
	private static final int DATABASE_VERSION = 1;
	private static final String PKG_NAME = "com.ll.horsebean.bll.db.framework";
	private static final String OUT_DIR = "../Horsebean/src";

	private static ArrayList<AbsObjDaoFactory> daoObjectList = new ArrayList<AbsObjDaoFactory>();

	static
	{
		daoObjectList.add(new BookDaoFactory());
	}

	public static void main(String[] args) throws Exception
	{
		Schema schema = new Schema(DATABASE_VERSION, PKG_NAME);
		schema.enableKeepSectionsByDefault();
		schema.enableActiveEntitiesByDefault();

		for (AbsObjDaoFactory daoObject : daoObjectList)
		{
			daoObject.createDaoObject(schema);
		}

		try
		{
			new DaoGenerator().generateAll(schema, OUT_DIR);

			List<Entity> entities = schema.getEntities();
			for (Entity entity : entities)
			{
				System.out.printf("Generating dao object: success, entity: %s, dbversion: %d, pkgName: %s, outDir: %s\n", entity.getClassName(),
				        DATABASE_VERSION, PKG_NAME, OUT_DIR);
			}
		}
		catch (Exception e)
		{
			System.out.printf("Generating dao object: fail, dbversion: %d, pkgName: %s, outDir: %s, error: %s\n", DATABASE_VERSION, PKG_NAME, OUT_DIR,
			        e.toString());
		}
	}
}
