package commands;

import java.util.logging.Logger;

import api.Part;
import exceptions.InvalidParameterException;
import impl.Session;

public class Commands 
{
	public static final Logger logger =  Logger.getGlobal();
	
	@Command(name ="SELECT", role="User")
	public static void SelectPart(String param) throws InvalidParameterException
	{
		Part part = Session.INSTANCE.configurator.CreateInstance(param);
		
		if (part == null)
		{
			logger.warning("Part not found.");
			return;
		}
		
		Session.INSTANCE.configuration.selectPart(part);
		
		logger.info("Part "+part.getName()+" selected.");
	}
}
