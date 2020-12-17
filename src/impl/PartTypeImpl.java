package impl;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

import api.Category;
import api.PartType;

public class PartTypeImpl implements PartType
{
	private String name;
	private Class<? extends PartImpl> classRef;
	private Category category;
	
	public PartTypeImpl(String name, Class<? extends PartImpl> classRef, Category category)
	{
		this.name = name;
		this.classRef = classRef;
		this.category = category;
	}
	@Override
	public String getName()
	{
		return name;
	}
	@Override
	public Category getCategory() 
	{
		return category;
	}
	
	public PartImpl newInstance() 
	{
		Constructor<? extends PartImpl> constructor;
		try 
		{
			constructor = classRef.getConstructor();
			PartImpl part =  (PartImpl)constructor.newInstance();
			part.setType(this);
			return part;
		} 
		catch (Exception e) 
		{
			Logger.getGlobal().log(Level.SEVERE, "constructor call failed", e);
			System.exit(-1);
		}
		return null;
	}
	
	
}
