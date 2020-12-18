package api;

import java.util.HashMap;
import java.util.Set;

public interface CompatibilityChecker 
{

	/**
	 * The incompatibiities are made by the addIncompatibilities/removeIncompatibilities command.
	 * @param reference a PartType
	 * @return all the incompatibilities of the reference 
	 */
    Set<PartType> getIncompatibilities(PartType reference);
    
    /**
	 * The requirements are made by the addRequirements/removeRequirements command.
	 * @param reference a PartType
	 * @return all the requirements of the reference 
	 */
    Set<PartType> getRequirements(PartType reference);
    
    

}
