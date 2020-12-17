package commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandsManager 
{
	private static Map<String,Method> handlers = new HashMap<String,Method>();
	
	public static void initialize()
	{

	    for (final Method method : Commands.class.getDeclaredMethods()) 
	    {
	         if (method.isAnnotationPresent(Command.class)) 
	         {
	        	 Command commandAnnot = (Command)method.getAnnotation(Command.class);
	                
	        	 handlers.put(commandAnnot.name(),method);
	         }
	   }

	}
	public static void Handle(String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		handlers.get(name).invoke(null);
	}
}
