package impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import api.Category;
import api.CompatibilityChecker;
import api.Configuration;
import api.*;
import exceptions.InvalidParameterException;


public class ConfiguratorImpl implements Configurator
{
	private Configuration config;
	
	private CompatibilityChecker checker;
	
	private HashMap<Category, Set<PartType>> catalog = new HashMap<Category, Set<PartType>>();
	
	public ConfiguratorImpl(Configuration configuration)
	{
		this.checker = new CompatibilityManagerImpl(); 
		this.config = configuration;
	}
	
	@Override
	public Set<Category> getCategories() {
		return catalog.keySet();
	}

	@Override
	public Set<PartType> getVariants(Category category) throws InvalidParameterException 
	{
		if(catalog.containsKey(category))
		{
			return catalog.get(category);	
		}
		else 
		{
			throw new InvalidParameterException("The category " + category + " does not exist in our catalog. Maybe try with an other spell");
		}
	}

	@Override
	public Configuration getConfiguration() {
		return this.config;
	}
	
	public void addToCatalog(Category category,PartType partType)
	{

		if (!catalog.containsKey(category))
		{
			HashSet<PartType> set = new HashSet<PartType>();
			set.add(partType);
			catalog.put(category, set);
		}
		else
		{
			catalog.get(category).add(partType);
		}
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
	public CompatibilityChecker getCompatibilityChecker() {
		return checker;
	}

}
