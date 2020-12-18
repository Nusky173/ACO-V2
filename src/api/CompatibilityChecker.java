package api;

import java.util.HashMap;
import java.util.Set;

public interface CompatibilityChecker 
{

	/**
	 * 
	 * @param reference a PartType
	 * @return all the incompatibilities of the reference 
	 */
    Set<PartType> getIncompatibilities(PartType reference);
    
    /*
     * Retourne les parties nécessaire à la partie de référence
     */
    Set<PartType> getRequirements(PartType reference);
    
    

}
