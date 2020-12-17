package impl;

import api.CompatibilityManager;
import enums.CategoryType;
import exceptions.InvalidParameterException;
import parts.*;

public class Session 
{
	public static Session INSTANCE = new Session();
	
	public ConfigurationImpl configuration;
	public CompatibilityManager compatibilityManager;
	public ConfiguratorImpl configurator;
	
	public Session() 
	{
		this.configuration = new ConfigurationImpl();
		this.compatibilityManager = (CompatibilityManager)configuration.getConfigurator().getCompatibilityChecker();
		this.configurator = ((ConfiguratorImpl)configuration.getConfigurator());
		this.initialize();
	}
	
	private void initialize()
	{
		configurator.Add(EG100.class, CategoryType.Engine);
		configurator.Add(EG133.class, CategoryType.Engine);
		configurator.Add(ED110.class, CategoryType.Engine);
		configurator.Add(EG210.class, CategoryType.Engine);
		configurator.Add(ED180.class, CategoryType.Engine);
		configurator.Add(EH120.class, CategoryType.Engine);
		
		configurator.Add(TM5.class, CategoryType.Transmission);
		configurator.Add(TM6.class, CategoryType.Transmission);
		configurator.Add(TA5.class, CategoryType.Transmission);
		configurator.Add(TS6.class, CategoryType.Transmission);
		configurator.Add(TSF7.class, CategoryType.Transmission);
		configurator.Add(TC120.class, CategoryType.Transmission);
		
		configurator.Add(XC.class, CategoryType.Exterior);
		configurator.Add(XM.class, CategoryType.Exterior);
		configurator.Add(XS.class, CategoryType.Exterior);
		
		configurator.Add(IN.class, CategoryType.Interior);
		configurator.Add(IH.class, CategoryType.Interior);
		configurator.Add(IS.class, CategoryType.Interior);	
	}

}
