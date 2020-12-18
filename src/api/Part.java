package api;


public interface Part extends PropertyManager 
{
	/**
	 * 
	 * @return the name of the part, similar to PartType.getName()
	 */
	default String getName() 
	{
		return this.getClass().getSimpleName();
	};
	/**
	 * 
	 * @return the category of the part, similar to PartType.getCategory()
	 */
	Category getCategory();
	
	/**
	 * 
	 * @return a PartType object of the part.
	 */
	PartType getType();
}