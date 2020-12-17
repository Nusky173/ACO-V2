package api;

import java.util.HashMap;
import java.util.Set;

public interface CompatibilityChecker 
{

	/* 
	 * Retourne les parties incompatibles avec la partie de référence.
	 */
    Set<PartType> getIncompatibilities(PartType reference);
    
    /*
     * Retourne les parties nécessaire à la partie de référence
     */
    Set<PartType> getRequirements(PartType reference);
    
    

}
