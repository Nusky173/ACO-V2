import java.util.HashMap;

import api.Category;
import api.CompatibilityManager;
import api.PartType;
import exceptions.InvalidParameterException;
import impl.CategoryImpl;
import impl.CategoryType;
import impl.ConfigurationImpl;
import impl.ConfiguratorImpl;
import impl.PartTypeImpl;
import parts.EG100;
import parts.Engine;

public class Program 
{
	 
	
	public static void main(String[] args) throws InvalidParameterException
	{
		ConfigurationImpl configuration = new ConfigurationImpl();
		CompatibilityManager compatibilityManager = (CompatibilityManager)configuration.getConfigurator().getCompatibilityChecker();
		ConfiguratorImpl impl = ((ConfiguratorImpl)configuration.getConfigurator());
		
		
		
		impl.Add(EG100.class, CategoryType.Engine);
		
		
		
		
		configuration.selectPart(impl.CreateInstance("EG100"));
	
		
		configuration.print();
		
	}
}
