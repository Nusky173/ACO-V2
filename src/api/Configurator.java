package api;

import java.util.Set;

import exceptions.InvalidParameterException;

public interface Configurator {

	/*Retourne les categories.*/
    Set<Category> getCategories();

    /*Retourne les variantes possible pour les categories.*/
    Set<PartType> getVariants(Category category)   throws InvalidParameterException; 

    /*Renvoie la configuration actuelle.*/
    Configuration getConfiguration();

    /*
     * Renvoie le verificateur de compatibilite.
     */
    CompatibilityChecker getCompatibilityChecker();

}
