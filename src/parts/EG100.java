package parts;

import enums.CategoryType;
import enums.GasType;
import partsCategories.Engine;

public class EG100 extends Engine
{	
	@Override
	protected void initialize() 
	{
		this.gasType = GasType.Gasoline;
		this.power = "100kW";
	}
}
