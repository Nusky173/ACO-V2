package api;

import java.util.Set;

import exceptions.ConfigurationException;
import exceptions.InvalidParameterException;

public interface CompatibilityManager extends CompatibilityChecker {

	/* Ajoute des incompatiblit� a une partie de r�f�rence */
    void addIncompatibilities(PartType reference, Set<PartType> target)  throws InvalidParameterException;
    
    /* Supprime une incompatiblité a  une partie de référence */
    void removeIncompatibility(PartType reference, PartType target) throws ConfigurationException ;
    
    /*Ajoute des parties n�cessaire a� la partie de r�f�rence.*/
    void addRequirements(PartType reference, Set<PartType> target) throws InvalidParameterException ;
    
    /*Retire une partie qui été nécessaire à la partie de référence.*/
    void removeRequirement(PartType reference, PartType target)  throws ConfigurationException;


}
