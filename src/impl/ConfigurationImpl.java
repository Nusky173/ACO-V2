package impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import api.*;

import exceptions.InvalidParameterException;

public class ConfigurationImpl implements Configuration
{
    private HashMap<Category,Part> parts;
    
    private Configurator configurator;

    public ConfigurationImpl() 
    {
    	this.configurator = new ConfiguratorImpl(this);
    	this.parts = new HashMap<Category,Part>();

    }
    
    
    public Configurator getConfigurator()
    {
    	return configurator;
    }
 
	@Override
	public boolean isValid()
	{
		Set<PartType> partTypes = new HashSet<PartType>();
		
		for (Part part : parts.values())
		{
			partTypes.add(part.getType());
		}
		
		for (PartType part : partTypes)
		{
			CompatibilityChecker checker = configurator.getCompatibilityChecker();
			
			
			Set<PartType> incomp = checker.getIncompatibilities(part);
			Set<PartType> requirements = checker.getRequirements(part);
			
			for (PartType p2 : partTypes)
			{
				if (incomp.contains(p2))
				{
					return false;
				}
				
			}

			for (PartType required : requirements)
			{
				if (!partTypes.contains(required))
				{
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean isComplete() throws InvalidParameterException
	{
		for (Category cat : configurator.getCategories())
		{	
			if (!getSelectionForCategory(cat).isPresent()) 
			{
				return false;			
			}
		}
		return true;
	}

	@Override
	public Set<Part> getSelectedParts() 
	{
		return new HashSet<Part>(parts.values()); // ca marchera jamais.
	}

	@Override
	public void selectPart(Part chosenPart) throws InvalidParameterException
	{
		if(this.configurator.getVariants(chosenPart.getCategory()).contains(chosenPart.getType()))
		{
			if (!parts.containsKey(chosenPart.getCategory()))
			{
				parts.put(chosenPart.getCategory(), chosenPart);
			}
			else
			{
				parts.replace(chosenPart.getCategory(), chosenPart);
			}
		}
		else 
			throw new InvalidParameterException("The Part " + chosenPart + " is not refer in our catalog.");
	}

	@Override
	public Optional<Part> getSelectionForCategory(Category category) throws InvalidParameterException 
	{
		if(this.parts.containsKey(category))
		{	
			return Optional.of(parts.get(category));
		}
		else
		{
			return Optional.empty();
		}
	}

	@Override
	public void unselectPartType(Category categoryToClear) throws InvalidParameterException
	{
		if(this.configurator.getCategories().contains(categoryToClear))
		{
			if(this.parts.containsKey(categoryToClear))
			{
				parts.remove(categoryToClear);
			}
			else throw new InvalidParameterException("The category " + categoryToClear + " does not have a reference for your configuration");
		}
		else throw new InvalidParameterException("The category " + categoryToClear + " is not refer in our catalog.");
	}
	

	@Override
	public void clear() 
	{
		parts.clear();
		
	}
	public void print() 
	{
		for (Category cat : parts.keySet())
		{
			System.out.println("Category ("+cat.getName()+") Part ("+parts.get(cat).getName()+")");
		}
		
	}

}
