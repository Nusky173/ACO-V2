package commands;

import java.util.Optional;
import java.util.logging.Logger;

import api.Part;
import exceptions.ConfigurationException;
import exceptions.InvalidParameterException;
import impl.Session;

public class Commands 
{
	public static final Logger logger =  Logger.getGlobal();
	
	@Command(name ="SELECT", role="User")
	public static void SelectPart(String param) throws InvalidParameterException
	{
		Optional<Part> part = Session.INSTANCE.configurator.CreateInstance(param);
		
		if (part.isEmpty())
		{
			logger.warning("Part not found.");
			return;
		}
		
		Session.INSTANCE.configuration.selectPart(part.get());
		
		logger.info("Part "+part.get().getName()+" selected.");
	}
	
	@Command(name ="COMPLETE", role="User")
	public static void IsComplete() throws InvalidParameterException, ConfigurationException
	{
		System.out.println(Session.INSTANCE.configuration.isComplete());
	}
}
