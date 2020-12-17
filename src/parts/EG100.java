package parts;


public class EG100 extends Engine
{
	@Override
	protected void initialize() 
	{
		this.gasType = GasType.Diesel;
		this.power = "150KW";
	}
	
}
