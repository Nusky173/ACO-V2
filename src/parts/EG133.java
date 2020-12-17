package parts;

import enums.GasType;
import partsCategories.Engine;

public class EG133 extends Engine
{
	@Override
	protected void initialize() 
	{
		this.gasType = GasType.Gasoline;
		this.power = "133kW";
	}
	
}
