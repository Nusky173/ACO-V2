package impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import api.CompatibilityManager;
import api.Part;
import api.PartType;
import exceptions.InvalidParameterException;

public class CompatibilityManagerImpl implements CompatibilityManager
{
	HashMap<String,Set<PartType>> incompatibilities;
		
	HashMap<String,Set<PartType>> requirements;
	
	
	public CompatibilityManagerImpl()
	{
		this.incompatibilities = new HashMap<String,Set<PartType>>();
		this.requirements  = new HashMap<String,Set<PartType>>();
	}
	@Override
	public Set<PartType> getIncompatibilities(PartType reference) 
	{
		return this.incompatibilities.get(reference.getName());
	}

	@Override
	public Set<PartType> getRequirements(PartType reference) 
	{
		return requirements.get(reference.getName());
	}

	@Override
	public void addIncompatibilities(PartType reference, Set<PartType> target) throws InvalidParameterException
	{
		if(target.size() == 0)
		{
			throw new InvalidParameterException("The Set of incompatibilities to add to the part " + reference + " is empty.");
		}
		
		incompatibilities.put(reference.getName(), target);	
	}

	@Override
	public void removeIncompatibility(PartType reference, PartType target) throws InvalidParameterException 
	{
		if (reference == null)
		{
			throw new InvalidParameterException("The Set of incompatibilities to add to the part " + reference + " is empty.");
		}
		incompatibilities.remove(reference.getName(), target);
	}

	@Override
	public void addRequirements(PartType reference, Set<PartType> target) throws InvalidParameterException 
	{
		if(target.size() == 0)
		{
			throw new InvalidParameterException("The Set of incompatibilities to add to the part " + reference + " is empty.");
		}
		
		requirements.put(reference.getName(), target);
		
	}

	@Override
	public void removeRequirement(PartType reference, PartType target)
	{
		requirements.remove(reference.getName(), target);
	}


}
