package api;

import exceptions.*;

import java.util.Optional;
import java.util.Set;

public interface Configuration 
{

	/**
	 * It's Here that we check if the user as select some conflicting parts or haven't select all the parts which are require.
	 * @return true if the configuration is Valid, false otherwise.
	 */
    boolean isValid();

    /**
     * 
     * @return true if the configuration of the user has one instance of part of each categories.
     * @throws InvalidParameterException 
     */
    boolean isComplete();

    /**
     * 
     * @return the a Set of Part that contain all the Part that the user has selected before.
     */
    Set<Part> getSelectedParts();

    /**
     * 
     * @param chosenPart : the part that the user select to put in the current configuration.
     * @throws InvalidParameterException the chosen part is not refer in the catalog.
     */
    void selectPart(Part chosenPart) throws InvalidParameterException;

    /**
     * 
     * @param category 
     * @return the Part that the user have select for the current configuration. Can be null if the user haven't select a part for the category.
     * @throws InvalidParameterException the category is not refer in the catalog.
     */
    Optional<Part> getSelectionForCategory(Category category) throws InvalidParameterException;

    /**
     * 
     * @param categoryToClear 
     * @throws InvalidParameterException the category has no instance in the current selection.
     */
    void unselectPartType(Category categoryToClear) throws InvalidParameterException;

    /**
     * remove all the selected part from the configuration.
     */
    void clear();

}
