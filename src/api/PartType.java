package api;

public interface PartType 
{
	/**
	 * 
	 * @return le nom de la partie.
	 */
    String getName();

    /**
     * 
     * @return la catégorie de la partie.
     */
    Category getCategory();
}
