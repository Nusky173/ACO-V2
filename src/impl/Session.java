package impl;

import api.CompatibilityManager;
import enums.CategoryType;
import enums.RoleType;
import exceptions.InvalidParameterException;
import parts.EG100;

public class Session 
{
	public static Session INSTANCE = new Session();
	
	public ConfigurationImpl configuration;
	public CompatibilityManager compatibilityManager;
	public ConfiguratorImpl configurator;
	
	private RoleType Role;
	
	public Session() 
	{
		this.Role = RoleType.User;
		this.configuration = new ConfigurationImpl();
		this.compatibilityManager = (CompatibilityManager)configuration.getConfigurator().getCompatibilityChecker();
		this.configurator = ((ConfiguratorImpl)configuration.getConfigurator());
		this.initialize();
	}
	
	private void initialize()
	{
		configurator.Add(EG100.class, CategoryType.Engine);
	}
	
	public RoleType getRole()
	{
		return this.Role;
	}

}
