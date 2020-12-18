package partsCategories;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import impl.PartImpl;

public abstract class Exterior extends PartImpl
{
	/**
	 * The pain of the exterior part.
	 */
	protected String paint;
	
	protected abstract void initialize();
	
	public Exterior()
	{
		createProperties();
		initialize();
	}
	
	/**
	 * create the properties for the given part.
	 */
	private void createProperties() 
	{
		Supplier<String> getter = () -> getPaint();
		Consumer<String> setter = (String paint) -> setPaint(paint);
		Set<String> possibleValues = new HashSet<String>();
		super.addProperty("paint", getter,setter,possibleValues);
	}
	
	/**
	 * 
	 * @return retrieve the paint of the part
	 */
	private String getPaint()
	{
		return paint;
	}
	
	/**
	 * 
	 * @param value set the paint of the part.
	 */
	private void setPaint(String value)
	{
		paint = value;
	}
}
