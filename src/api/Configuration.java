package api;

import exceptions.*;

import java.util.Optional;
import java.util.Set;

public interface Configuration 
{

	/*Check si la configuration est valide.*/
    boolean isValid();

    /*Check si la configuration est complete.*/
    boolean isComplete() throws InvalidParameterException, ConfigurationException;

    /*Renvoie toutes les parties de la configuration.*/
    Set<Part> getSelectedParts();

    /*Definie une partie a� la configuration*/
    void selectPart(Part chosenPart) throws InvalidParameterException;

    /*Retourne la partie correspondante de la configuration � la categorie*/
    Optional<Part> getSelectionForCategory(Category category) throws InvalidParameterException;

    /*Enleve la selection de la categorie.*/
    void unselectPartType(Category categoryToClear) throws InvalidParameterException;

    /*Supprime toutes les parties.*/
    void clear();

}
