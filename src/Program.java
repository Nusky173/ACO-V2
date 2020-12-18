
import static org.junit.Assert.assertTrue;

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
		
		Session session = new Session();
		
		
		
		session.configuration.selectPart(session.configurator.createInstance("XS").get());
		session.configuration.selectPart(session.configurator.createInstance("IS").get());
	
		
		
		//IS/XS incompatible with TM5 & EG100
	    System.out.println(session.configuration.isValid());
		
		
	    /*
		CommandsManager.initialize();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		String line = null;
		
        while ((line = br.readLine()) != null) 
        {
        	CommandsManager.Handle(line);
        }  
	*/
		
	}
}
