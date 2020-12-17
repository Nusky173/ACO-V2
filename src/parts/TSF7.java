package parts;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TSF7 extends Transmission
{
	private String transmissionWheels;
	
	@Override
	protected void initialize() 
	{
		this.type = TransmissionType.Sequential;
		this.gears = "7";
		
		Supplier<String> getter = () -> getWheel();
		Consumer<String> setter = (String value) -> setWheel(value);
		Set<String> possibleValues = new HashSet<String>();
		super.addProperty("wheel", getter, setter, possibleValues);
	}
	
	private String getWheel()
	{
		return transmissionWheels;
	}
	
	private void setWheel(String value)
	{
		transmissionWheels = value;
	}
	
}
