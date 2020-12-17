package commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
	
	private static Map<String,CommandInfo> handlers = new HashMap<String,CommandInfo>();
	
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
	public static void Handle(String name,String param) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CommandInfo infos = handlers.get(name);
		
		if (infos != null)
		{
			if (infos.requiredRole.getValue() <= Session.INSTANCE.getRole().getValue())
			{
				infos.method.invoke(null,param);
				
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
