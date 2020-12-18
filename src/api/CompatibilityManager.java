package api;

import java.util.Set;


import exceptions.InvalidParameterException;

public interface CompatibilityManager extends CompatibilityChecker {

	/**
	 * 
	 * @param reference the partType that we want to add an incompatibility.
	 * @param target the PartType to add to the list of the incompatibilities of the reference.
	 * @throws InvalidParameterException the reference and target can't be null. The target must contain at least one element.
	 */
    void addIncompatibilities(PartType reference, Set<PartType> target)  throws InvalidParameterException;
    
    /**
	 * 
	 * @param reference the partType that we want to remove an incompatibility from the incompatibilities map.
	 * @param target the PartType to remove from the list of the incompatibilities of the reference.
	 * @throws InvalidParameterException the reference and target can't be null. The incompatibilities map must contain the reference as a key and 
	 * his value must contain the target into the set. 
	 */
    void removeIncompatibility(PartType reference, PartType target) throws InvalidParameterException ;
    
    /**
	 * 
	 * @param reference the partType that we want to add a requirement.
	 * @param target the PartType to add to the list of the requirements of the reference.
	 * @throws InvalidParameterException the reference and target can't be null. The target must contain at least one element.
	 */
    void addRequirements(PartType reference, Set<PartType> target) throws InvalidParameterException ;
    
    /**
   	 * 
   	 * @param reference the partType that we want to remove a requirement from the requirements map.
   	 * @param target the PartType to remove from the list of the requirements of the reference.
   	 * @throws InvalidParameterException the reference and target can't be null. The requirements map must contain the reference as a key and 
   	 * his value must contain the target into the set. 
   	 */
    void removeRequirement(PartType reference, PartType target)  throws InvalidParameterException;


}
