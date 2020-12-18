package partsCategories;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import impl.PartImpl;

public abstract class Interior extends PartImpl
{
	protected String interior;
	
	protected abstract void initialize();
	
	public Interior()
	{
		createProperties();
		initialize();
	}
	
	private void createProperties()
	{
		Supplier<String> getter = () -> getInterior();
		Consumer<String> setter = (String gas) -> setInterior(gas);
		Set<String> possibleValues = new HashSet<String>();
		super.addProperty("interior", getter,setter,possibleValues);
	}
	
	private String getInterior()
	{
		return interior;
	}
	
	private void setInterior(String value)
	{
		interior = value;
	}
}
