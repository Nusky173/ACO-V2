package parts;

import enums.TransmissionType;
import partsCategories.Transmission;

public class TM5 extends Transmission
{
	@Override
	protected void initialize() 
	{
		this.type = TransmissionType.Manual;
		this.gears = "5";
	}
	
}
