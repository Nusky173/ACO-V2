package impl;

import api.Category;

public class CategoryImpl implements Category
{
	/**
	 * the name of the category
	 */
	private String name;
	
	public CategoryImpl(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 
	 * @return the name of the category.
	 */
	@Override
	public String getName() 
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
	
}
