package partsCategories;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import impl.PartImpl;

public abstract class Exterior extends PartImpl
{
	protected String paint;
	
	protected abstract void initialize();
	
	public Exterior()
	{
		createProperties();
	}
	
	private void createProperties() 
	{
		Supplier<String> getter = () -> getPaint();
		Consumer<String> setter = (String paint) -> setPaint(paint);
		Set<String> possibleValues = new HashSet<String>();
		super.addProperty("paint", getter,setter,possibleValues);
	}
	
	private String getPaint()
	{
		return paint;
	}
	
	private void setPaint(String value)
	{
		paint = value;
	}
}
