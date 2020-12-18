package impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import api.*;
import enums.CategoryType;
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
	/**
	 * 
	 * @param classRef the part class.
	 * @param type the type of the category, an enum.
	 */
	public void add(Class<? extends PartImpl> classRef,CategoryType type) 
	{
		String name = classRef.getSimpleName();
		PartTypeImpl partType = new PartTypeImpl(name,classRef,categories.get(type));
		catalog.put(name, partType);
	}
	
	/**
	 * 
	 * @param partTypeName the name of the partType. A string
	 * @return a PartType object, if the name is correctly spelled and the partType is refered in the catalog.
	 */
	public Optional<PartTypeImpl> getPartType(String partTypeName)
	{
		PartTypeImpl partType = catalog.get(partTypeName);
		
		if (partType != null)
		{
			return Optional.of(partType);
		}
		else
		{
			return Optional.empty();
		}
	}
	
	/**
	 * 
	 * @param partTypeName the name of the partType. A string
	 * @return a Part Object, if the name is correctly spelled and the partType is refered in the catalog. 
	 */
	public Optional<Part> createInstance(String partTypeName)
	{
		Optional<PartTypeImpl> partType = getPartType(partTypeName);
		
		if (!partType.isPresent())
		{
			return Optional.empty();
		}
		else
		{
			return Optional.of(partType.get().newInstance());
		}
	}
	
	@Override
	public Set<Category> getCategories() 
	{
		return new HashSet<>(categories.values());
	}
	
	public Category getCategory(CategoryType category)
	{
		return categories.get(category);
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
