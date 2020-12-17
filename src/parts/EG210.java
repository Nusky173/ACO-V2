package parts;

public class EG210 extends Engine
{
	@Override
	protected void initialize() 
	{
		this.gasType = GasType.Gasoline;
		this.power = "210kW";
	}
	
}
