package partsCategories;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import enums.GasType;
import impl.PartImpl;

public abstract class Engine extends PartImpl
{
	/**
	 * The different gas of engines part.  An enum
	 */
	protected GasType gasType;
	
	/**
	 * Power of the engine
	 */
	protected String power;
	
	public Engine()
	{
		createProperties();
		initialize();
	}
	
	/**
	 * create the properties for the given part.
	 */
	private void createProperties()
	{
		Supplier<String> getter = () -> getGas();
		Consumer<String> setter = (String gas) -> setGasType(gas);
		Set<String> possibleValues = new HashSet<String>();
		super.addProperty("gas", getter,setter,possibleValues);
		
		getter = () -> getPower();
		setter = (String value) -> setPower(value);
		possibleValues = new HashSet<String>();
		super.addProperty("consumption", getter,setter,possibleValues);
	}
	
	protected abstract void initialize();
	
	/**
	 * 
	 * @return retrieve the gas of the part.
	 */
	private String getGas()
	{
		return gasType.toString();
	}
	
	/**
	 * 
	 * @param type set the GasType of the part.
	 */
	private void setGasType(String type)
	{
		this.gasType = GasType.valueOf(type);
	}
	
	/**
	 * 
	 * @return retrieve the power of the part.
	 */
	private String getPower()
	{
		return power;
	}
	
	/**
	 * 
	 * @param type set the power of the part.
	 */
	private void setPower(String value)
	{
		this.power = value;
	}
	
}
