package api;

import java.util.Set;

import exceptions.InvalidParameterException;

public interface Configurator
{
	/**
	 * 
	 * @return a set of the categories available for the catalog.
	 */
    Set<Category> getCategories();

    /**
     * 
     * @param category 
     * @return all the variants for the category select, wich are available in the catalog.
     * @throws InvalidParameterException the category is not refer in the catalog.
     */
    Set<PartType> getVariants(Category category)   throws InvalidParameterException; 

    /**
     * 
     * @return a configuration object of the current configuration.
     */
    Configuration getConfiguration();

    /**
     * 
     * @return the checker compatibility relative to the current configuration.
     */
    CompatibilityChecker getCompatibilityChecker();

}
