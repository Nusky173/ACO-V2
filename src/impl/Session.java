package impl;

import api.CompatibilityManager;
import enums.CategoryType;
import exceptions.InvalidParameterException;
import parts.EG100;

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
	}

}
