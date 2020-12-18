package commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import enums.GasType;
import enums.RoleType;
import impl.Session;

public class CommandsManager 
{
	public static final Logger logger =  Logger.getGlobal();
	
	/**
	 * Command methods mapping.
	 */
	private static Map<String,CommandInfo> handlers = new HashMap<String,CommandInfo>();
	
	/**
	 * Extract all method in the Commands class and retreive the methods
	 * with annotation @Command.
	 * map the results into handlers.
	 */
	public static void initialize()
	{
	    for (final Method method : Commands.class.getDeclaredMethods()) 
	    {
	         if (method.isAnnotationPresent(Command.class)) 
	         {
	        	 Command commandAnnot = (Command)method.getAnnotation(Command.class);
	             
	        	 RoleType role = RoleType.valueOf(commandAnnot.role());
	        	 
	        	 CommandInfo infos = new CommandInfo(method,role);
	        	 
	        	 handlers.put(commandAnnot.name(),infos);
	         }
	   }

	}
	/**
	 * Handle a command from the raw user input (COMMAND ARG1 ARG2 ARG3 etc)
	 * The command arguments should have the exact number target method parameters.
	 * Retreive the command and invoke the method binded to command identifier.
	 * @param raw The user input
	 * @throws IllegalArgumentException method cannot be invoked (bad signature)
	 * @throws InvocationTargetException method cannot be invoked (bad target, (should be static))
	 */
	public static void Handle(String raw) throws IllegalAccessException, InvocationTargetException
	{
		String[] split = raw.split("\\s+");
		
		String name = split[0]; // split return at least one element, wont throw ex.
		 
		CommandInfo infos = handlers.get(name);
		
		if (infos != null)
		{
			if (infos.requiredRole.getValue() <= Session.INSTANCE.getRole().getValue())
			{
				if (infos.method.getParameters().length != split.length-1)
				{
					logger.warning("Invalid method parameters.");
					return;
				}
				
				String[] args = Arrays.copyOfRange(split, 1, split.length);
				
				/*
				 * null -> Method is static, there is no target member.
				 */
				infos.method.invoke(null,args);
				
			}
			else
			{
				logger.warning("Cannot execute command "+name+" access not granted.");
			}
		}
		else
		{
			logger.info("Unknown command "+name);
		}
	}
}
