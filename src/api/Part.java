package api;


public interface Part extends PropertyManager 
{
	default String getName() 
	{
		return this.getClass().getSimpleName();
	};
	Category getCategory();
	PartType getType();
}