package parts;

public class EH120 extends Engine
{
	@Override
	protected void initialize() 
	{
		this.gasType = GasType.Hybrid;
		this.power = "120kW";
	}
	
}
