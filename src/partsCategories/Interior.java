package partsCategories;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import impl.PartImpl;

public abstract class Interior extends PartImpl
{
	/**
	 * The paint of the interior part.
	 */
	protected String interior;
	
	protected abstract void initialize();
	
	public Interior()
	{
		createProperties();
		initialize();
	}
	
	/**
	 * create the properties for the given part.
	 */
	private void createProperties()
	{
		Supplier<String> getter = () -> getInterior();
		Consumer<String> setter = (String gas) -> setInterior(gas);
		Set<String> possibleValues = new HashSet<String>();
		super.addProperty("interior", getter,setter,possibleValues);
	}
	
	/**
	 * 
	 * @return retrieve the paint of the part
	 */
	
	private String getInterior()
	{
		return interior;
	}
	
	/**
	 * 
	 * @param value set the paint of the part.
	 */
	private void setInterior(String value)
	{
		interior = value;
	}
}
