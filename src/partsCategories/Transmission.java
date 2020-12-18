package partsCategories;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import enums.TransmissionType;
import impl.PartImpl;

public abstract class Transmission extends PartImpl
{
	/**
	 * The different type of transmission. An enum
	 */
	protected TransmissionType type;
	
	/**
	 * The gears of the part.
	 */
	protected String gears;
		
	public Transmission() 
	{
		createProperties();
		initialize();
	}
	
	/**
	 * create the properties for the given part.
	 */
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
	
	/**
	 * 
	 * @return the Type transmission of the part.
	 */
	private String getTransmissionType()
	{
		return type.toString();
	}
	
	/**
	 * 
	 * @param value set the transmission type of the part.
	 */
	private void setTransmissionType(String value)
	{
		this.type = TransmissionType.valueOf(value);
	}
	
	/**
	 * 
	 * @return the gears of the part.
	 */
	private String getGears()
	{
		return gears.toString();
	}
	
	/**
	 * 
	 * @param value set the gears of the part.
	 */
	private void setGears(String value)
	{
		this.gears = value;
	}
	
	
}
