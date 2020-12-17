package commands;

import java.lang.annotation.Annotation;
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
		final List<Method> methods = new ArrayList<Method>();
	   
	    for (final Method method : Commands.class.getDeclaredMethods()) 
	    {
	            if (method.isAnnotationPresent(annotation)) 
	            {
	                Annotation annotInstance = method.getAnnotation(annotation);
	                // TODO process annotInstance
	                methods.add(method);
	            }
	        }
	        // move to the upper class in the hierarchy in search for more methods
	        klass = klass.getSuperclass();
	    }
	    return methods;
	}
}
