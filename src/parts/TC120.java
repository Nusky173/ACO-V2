package parts;

public class TC120 extends Transmission
{
	@Override
	protected void initialize() 
	{
		this.type = TransmissionType.Converter;
		this.gears = "120kW";
	}
	
}
