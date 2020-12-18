package impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import api.CompatibilityManager;
import api.Part;
import api.PartType;

import exceptions.InvalidParameterException;

public class CompatibilityManagerImpl implements CompatibilityManager
{
	HashMap<PartType,Set<PartType>> incompatibilities;
		
	HashMap<PartType,Set<PartType>> requirements;
	
	public CompatibilityManagerImpl()
	{
		this.incompatibilities = new HashMap<PartType,Set<PartType>>();
		this.requirements  = new HashMap<PartType,Set<PartType>>();
	}
	@Override
	public Set<PartType> getIncompatibilities(PartType reference) 
	{
		Set<PartType> result= this.incompatibilities.get(reference);
		
		if (result == null)
		{
			return new HashSet<PartType>();
		}
		else
		{
			return result;
		}
	}

	@Override
	public Set<PartType> getRequirements(PartType reference) 
	{
		Set<PartType> result= requirements.get(reference);
		
		if (result == null)
		{
			return new HashSet<PartType>();
		}
		else
		{
			return result;
		}
	}

	@Override
	public void addIncompatibilities(PartType reference, Set<PartType> target) throws InvalidParameterException
	{
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		
		if(target.size() == 0)
		{
			throw new InvalidParameterException("The Set of incompatibilities to add to the part " + reference + " is empty.");
		}
		
		incompatibilities.put(reference, target);	
	}

	@Override
	public void removeIncompatibility(PartType reference, PartType target) throws InvalidParameterException 
	{
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		
		if (!incompatibilities.get(reference).contains(target))
		{
			throw new InvalidParameterException("The part " + target.getName() + " is not incompatible with the part " + reference.getName() + ".");
		}
		if(!incompatibilities.containsKey(reference))
		{
			throw new InvalidParameterException("The part " + target.getName() + " do not have incompatibilites for now.");
		}
		incompatibilities.get(reference).remove(target);
	}

	@Override
	public void addRequirements(PartType reference, Set<PartType> target) throws InvalidParameterException 
	{
		Objects.requireNonNull(reference);
		Objects.requireNonNull(target);
		
		if(target.size() == 0)
		{
			throw new InvalidParameterException("The Set of incompatibilities to add to the part " + reference + " is empty.");
		}
		
		if (requirements.containsKey(reference))
		{
			requirements.get(reference).addAll(target);
		}
		else
		{
			requirements.put(reference, target);
		}
		
	}

	@Override
	public void removeRequirement(PartType reference, PartType target) throws InvalidParameterException
	{
		if (!requirements.get(reference).contains(target))
		{
			throw new InvalidParameterException("The Part " + target.getName() + " do not require the part " + reference.getName() + ".");
		}
		if(!requirements.containsKey(reference))
		{
			throw new InvalidParameterException("The part " + target.getName() +" do not have requirements for now.");
		}
		
		requirements.get(reference).remove(target);
	}


}
