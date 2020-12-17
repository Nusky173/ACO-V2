package partsCategories;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import enums.GasType;
import impl.PartImpl;

public abstract class Engine extends PartImpl
{
	protected GasType gasType;
	
	protected String power;
	
	public Engine()
	{
		createProperties();
	}
	
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
	
	private String getGas()
	{
		return gasType.toString();
	}
	private void setGasType(String type)
	{
		this.gasType = GasType.valueOf(type);
	}
	private String getPower()
	{
		return power;
	}
	private void setPower(String value)
	{
		this.power = value;
	}
	
}
