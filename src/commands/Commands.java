package commands;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import api.Part;
import exceptions.ConfigurationException;
import exceptions.InvalidParameterException;
import impl.Session;
import utils.HtmlWriter;

public class Commands 
{
	public static final Logger logger =  Logger.getGlobal();
	
	private final static String OutputFilename  = "export.html";
	
	
	@Command(name ="SELECT", role="User")
	public static void SelectPart(String param) throws InvalidParameterException
	{
		Optional<Part> part = Session.INSTANCE.configurator.createInstance(param);
		
		if (!part.isPresent())
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
	
	@Command(name = "EXPORT",role="User")
	public static void Export() throws IOException
	{
		HtmlWriter writer = new HtmlWriter(OutputFilename);
		writer.writeConfiguration(Session.INSTANCE.configuration);
		writer.save();
		
		logger.info("Configuration exported.");
	}
}
