package com.ll.horsebean.daogenerator.daofactory;

import de.greenrobot.daogenerator.Schema;

public abstract class AbsObjDaoFactory
{
	/**
	 * This abstract method must be implemented in derived class to create substantial dao object.
	 * @param schema
	 */
	public abstract void createDaoObject(Schema schema);
}