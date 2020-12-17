package impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import api.*;
import exceptions.InvalidParameterException;
import parts.EG100;

public class ConfiguratorImpl implements Configurator
{
	private Configuration config;
	
	private CompatibilityChecker checker;
	
	private HashMap<String, PartTypeImpl> catalog = new HashMap<String, PartTypeImpl>();
	
	private HashMap<CategoryType,Category> categories = new HashMap<CategoryType,Category>();
	
	public ConfiguratorImpl(Configuration configuration) 
	{
		this.checker = new CompatibilityManagerImpl(); 
		this.config = configuration;
		
		for (CategoryType categoryType : CategoryType.values())
		{ 
			categories.put(categoryType, new CategoryImpl(categoryType.name()));
	    }
	}
	
	public void Add(Class<? extends PartImpl> classRef,CategoryType type) 
	{
		String name = classRef.getSimpleName();
		PartTypeImpl partType = new PartTypeImpl(name,classRef,categories.get(type));
		catalog.put(name, partType);
	}
	
	public Part CreateInstance(String partTypeName)
	{
		return catalog.get(partTypeName).newInstance();
	}
	
	@Override
	public Set<Category> getCategories() 
	{
		return (Set<Category>) categories.values();
	}

	@Override
	public Set<PartType> getVariants(Category category)  
	{
		return catalog.values().stream().filter(x->x.getCategory() == category).collect(Collectors.toSet());
	}

	@Override
	public Configuration getConfiguration() 
	{
		return this.config;
	}
	
	 
	public void print()
	{
		Iterator it = catalog.entrySet().iterator();
	    while (it.hasNext()) 
	    {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
	}
	public void SetCompatibilityChecker(CompatibilityChecker checker)
	{
		this.checker = checker;
	}
	@Override
	public CompatibilityChecker getCompatibilityChecker()
	{
		return checker;
	}

}
