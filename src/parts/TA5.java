package parts;

import enums.TransmissionType;
import partsCategories.Transmission;

public class TA5 extends Transmission
{
	@Override
	protected void initialize() 
	{
		this.type = TransmissionType.Automatic;
		this.gears = "5";
	}
	
}
