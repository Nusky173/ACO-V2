package parts;

import enums.GasType;
import partsCategories.Engine;

public class EH120 extends Engine
{
	@Override
	protected void initialize() 
	{
		this.gasType = GasType.Hybrid;
		this.power = "120kW";
	}
	
}
