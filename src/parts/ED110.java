package parts;

import enums.GasType;
import partsCategories.Engine;

public class ED110 extends Engine
{
	@Override
	protected void initialize() 
	{
		this.gasType = GasType.Diesel;
		this.power = "110kW";
	}
	
}
