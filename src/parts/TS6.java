package parts;

public class TS6 extends Transmission
{
	@Override
	protected void initialize() 
	{
		this.type = TransmissionType.Sequential;
		this.gears = "6";
	}
	
}
