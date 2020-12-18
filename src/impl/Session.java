package impl;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import api.CompatibilityManager;
import api.PartType;
import enums.CategoryType;
import enums.RoleType;
import exceptions.InvalidParameterException;
import parts.*;

public class Session 
{
	public static Session INSTANCE = new Session();
	private static Logger logger = Logger.getGlobal();
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
		
		try 
		{
			this.initialize();
		} 
		catch (InvalidParameterException e) 
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * The current session of our carTaylorAPI. Here you can see all the categories and all the partType that we have in our catalog.
	 * You can also manage the incompatibilities or requirements.
	 * @throws InvalidParameterException
	 */
	private void initialize() throws InvalidParameterException
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
		
		//incompatibilities
		Set<PartType> incompTA5 = new HashSet<PartType>();
		Set<PartType> incompTSF7 = new HashSet<PartType>();
		Set<PartType> incompXC = new HashSet<PartType>();
		Set<PartType> incompXM = new HashSet<PartType>();
		Set<PartType> incompXS = new HashSet<PartType>();
		Set<PartType> incompIS = new HashSet<PartType>();
		//requirements
		Set<PartType> requirementsEH120 = new HashSet<PartType>();
		Set<PartType> requirementsTC120 = new HashSet<PartType>();
		Set<PartType> requirementsXS = new HashSet<PartType>();
		Set<PartType> requirementsIS = new HashSet<PartType>();
		
		
		incompTA5.add(configurator.getPartType("EG100").get());
		incompTSF7.add(configurator.getPartType("EG100").get());
		incompTSF7.add(configurator.getPartType("EG133").get());
		incompTSF7.add(configurator.getPartType("ED110").get());
		incompXC.add(configurator.getPartType("EG210").get());
		incompXM.add(configurator.getPartType("EG210").get());
		incompXS.add(configurator.getPartType("EG100").get());
		incompIS.add(configurator.getPartType("EG100").get());
		incompIS.add(configurator.getPartType("TM5").get());
		
		requirementsEH120.add(configurator.getPartType("TC120").get());
		requirementsTC120.add(configurator.getPartType("EH120").get());
		requirementsXS.add(configurator.getPartType("IS").get());
		requirementsIS.add(configurator.getPartType("XS").get());
		
		
		//ajouter à compatibilityManager
		compatibilityManager.addIncompatibilities(configurator.getPartType("TA5").get(), incompTA5);
		compatibilityManager.addIncompatibilities(configurator.getPartType("TSF7").get(), incompTSF7);
		compatibilityManager.addIncompatibilities(configurator.getPartType("XC").get(), incompXC);
		compatibilityManager.addIncompatibilities(configurator.getPartType("XM").get(), incompXM);
		compatibilityManager.addIncompatibilities(configurator.getPartType("XS").get(), incompXS);
		compatibilityManager.addIncompatibilities(configurator.getPartType("IS").get(), incompIS);
		
		compatibilityManager.addRequirements(configurator.getPartType("IS").get(), requirementsIS);
		compatibilityManager.addRequirements(configurator.getPartType("XS").get(), requirementsXS);
		compatibilityManager.addRequirements(configurator.getPartType("TC120").get(), requirementsTC120);
		compatibilityManager.addRequirements(configurator.getPartType("EH120").get(), requirementsEH120);
		
		
	}
	
	public RoleType getRole()
	{
		return this.Role;
	}

}
