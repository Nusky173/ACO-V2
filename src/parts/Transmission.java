package parts;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import impl.PartImpl;

public abstract class Transmission extends PartImpl
{
	protected TransmissionType type;
	
	protected String gears;
		
	public Transmission() 
	{
		createProperties();
	}
		
	private void createProperties() 
	{
		Supplier<String> getter = () -> getTransmissionType();
		Consumer<String> setter = (String value) -> setTransmissionType(value);
		Set<String> possibleValues = new HashSet<String>();
		super.addProperty("transmission", getter,setter,possibleValues);
		
		getter = () -> getGears();
		setter = (String value) -> setGears(value);
		possibleValues = new HashSet<String>();
		super.addProperty("gears", getter,setter,possibleValues);
	}
	
	protected abstract void initialize();
	
	private String getTransmissionType()
	{
		return type.toString();
	}
	
	private void setTransmissionType(String value)
	{
		this.type = TransmissionType.valueOf(value);
	}
	
	private String getGears()
	{
		return gears.toString();
	}
	
	private void setGears(String value)
	{
		this.gears = value;
	}
	
	
}
