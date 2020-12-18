package impl;

import api.CompatibilityManager;
import enums.CategoryType;
import enums.RoleType;
import exceptions.InvalidParameterException;
import parts.*;

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
		configurator.add(EG100.class, CategoryType.Engine);
		configurator.add(EG133.class, CategoryType.Engine);
		configurator.add(ED110.class, CategoryType.Engine);
		configurator.add(EG210.class, CategoryType.Engine);
		configurator.add(ED180.class, CategoryType.Engine);
		configurator.add(EH120.class, CategoryType.Engine);
		
		configurator.add(TM5.class, CategoryType.Transmission);
		configurator.add(TM6.class, CategoryType.Transmission);
		configurator.add(TA5.class, CategoryType.Transmission);
		configurator.add(TS6.class, CategoryType.Transmission);
		configurator.add(TSF7.class, CategoryType.Transmission);
		configurator.add(TC120.class, CategoryType.Transmission);
		
		configurator.add(XC.class, CategoryType.Exterior);
		configurator.add(XM.class, CategoryType.Exterior);
		configurator.add(XS.class, CategoryType.Exterior);
		
		configurator.add(IN.class, CategoryType.Interior);
		configurator.add(IH.class, CategoryType.Interior);
		configurator.add(IS.class, CategoryType.Interior);
		
	}
	
	public RoleType getRole()
	{
		return this.Role;
	}

}
