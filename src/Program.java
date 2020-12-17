
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import commands.CommandsManager;
import exceptions.InvalidParameterException;
import impl.Session;


public class Program 
{
	public static final Logger logger =  Logger.getGlobal();
	
	public static void main(String[] args) throws InvalidParameterException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CommandsManager.initialize();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		String line = null;
		
        while ((line = br.readLine()) != null) 
        {
        	String[] splited = line.split("\\s+");
        	
        	if (splited.length == 2)
        	{
        		CommandsManager.Handle(splited[0],splited[1]);
        	}
        	else
        	{
        		logger.warning("Unable to parse command " + line);
        	}
        	
        }  
		
		
	}
}
