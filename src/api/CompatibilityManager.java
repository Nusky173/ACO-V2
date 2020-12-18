package api;

import java.util.Set;


import exceptions.InvalidParameterException;

public interface CompatibilityManager extends CompatibilityChecker {

	/**
	 * 
	 * @param reference le type auquel on ajoute l'incompatibiltié
	 * @param target les incompatibilités ajoutés.
	 * @throws InvalidParameterException la réference ne doit pas être null
	 */
    void addIncompatibilities(PartType reference, Set<PartType> target)  throws InvalidParameterException;
    
    /**
     * Supprime une incompatiblité entre deux part type
     * @param reference
     * @param target
     * @throws InvalidParameterException
     */
    void removeIncompatibility(PartType reference, PartType target) throws InvalidParameterException ;
    
    /*Ajoute des parties n�cessaire a� la partie de r�f�rence.*/
    void addRequirements(PartType reference, Set<PartType> target) throws InvalidParameterException ;
    
    /*Retire une partie qui été nécessaire à la partie de référence.*/
    void removeRequirement(PartType reference, PartType target)  throws InvalidParameterException;


}
