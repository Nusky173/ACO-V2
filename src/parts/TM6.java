package parts;

public class TM6 extends Transmission
{
	@Override
	protected void initialize() 
	{
		this.type = TransmissionType.Manual;
		this.gears = "6";
	}
	
}
