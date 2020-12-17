package tests;

import java.util.HashSet;
import java.util.Set;

import api.CompatibilityManager;
import api.PartType;
import exceptions.InvalidParameterException;
import impl.CategoryImpl;
import impl.ConfigurationImpl;
import impl.ConfiguratorImpl;
import impl.PartImpl;
import parts.Engine;
import impl.PartTypeImpl;

public class TestsContainer
{	
	public static CategoryImpl engine = new CategoryImpl("Engine");
	public static CategoryImpl transmission = new CategoryImpl("Transmission");
	public static CategoryImpl exterior = new CategoryImpl("Exterior");
	public static CategoryImpl interior = new CategoryImpl("Interior");
	  
	public static PartTypeImpl EG100 = new PartTypeImpl("EG100",Engine.class,engine);
	public static PartTypeImpl EG133 = new PartTypeImpl("EG133",Engine.class,engine);
	public static PartTypeImpl EG210 = new PartTypeImpl("EG210",Engine.class,engine);
	public static PartTypeImpl ED110 = new PartTypeImpl("ED110",Engine.class,engine);
	public static PartTypeImpl EH120 = new PartTypeImpl("EH120",Engine.class,engine);
	
	public static PartTypeImpl TA5 = new PartTypeImpl("TA5",PartImpl.class, transmission);
	public static PartTypeImpl TM5 = new PartTypeImpl("TM5", PartImpl.class,transmission);
	public static PartTypeImpl TSF7 = new PartTypeImpl("TSF7",PartImpl.class, transmission);
	public static PartTypeImpl TC120 = new PartTypeImpl("TC120",PartImpl.class, transmission);
	
	public static PartTypeImpl XC = new PartTypeImpl("XC",PartImpl.class,exterior);
	public static PartTypeImpl XM = new PartTypeImpl("XM",PartImpl.class,exterior);
	public static PartTypeImpl XS = new PartTypeImpl("XS",PartImpl.class,exterior);
	
	public static PartTypeImpl IN = new PartTypeImpl("IN",PartImpl.class,interior);
	public static PartTypeImpl IH = new PartTypeImpl("IH",PartImpl.class,interior);
	public static PartTypeImpl IS = new PartTypeImpl("IS",PartImpl.class,interior);
	
	public static Set<PartType> incompEG100 = new HashSet<PartType>();
	public static Set<PartType> incompEG133 = new HashSet<PartType>();
	public static Set<PartType> incompED110 = new HashSet<PartType>();
	public static Set<PartType> incompEG210 = new HashSet<PartType>();
	public static Set<PartType> incompTM5 = new HashSet<PartType>();
	
	public static Set<PartType> reqEH120 = new HashSet<PartType>();
	public static Set<PartType> reqTC120 = new HashSet<PartType>();
	public static Set<PartType> reqXS = new HashSet<PartType>();
	public static Set<PartType> reqIS = new HashSet<PartType>();
	
	public static ConfigurationImpl buildConfiguration() throws InvalidParameterException
	{
		ConfigurationImpl configuration = new ConfigurationImpl();
		CompatibilityManager compatibilityManager = (CompatibilityManager)configuration.getConfigurator().getCompatibilityChecker();
		
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(engine,EG100);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(engine,EG133);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(engine,EG210);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(engine,ED110);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(engine,EH120);
	
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(transmission,TA5);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(transmission,TM5);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(transmission,TSF7);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(transmission,TC120);
	
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(exterior,XC);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(exterior,XM);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(exterior,XS);
	
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(interior,IN);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(interior,IH);
		((ConfiguratorImpl) configuration.getConfigurator()).addToCatalog(interior,IS);
	

		incompEG100.add(TA5);
		incompEG100.add(TSF7);
		incompEG100.add(XM);
		incompEG100.add(XS);
		incompEG100.add(IS);
	
		incompEG133.add(TSF7);		
	
		incompED110.add(TSF7);	
	
		incompEG210.add(XC);	
	
		incompTM5.add(IS);
	

		reqEH120.add(TC120);
		reqTC120.add(EH120);
		reqXS.add(IS);
		reqIS.add(XS);
	
		compatibilityManager.addIncompatibilities(EG100, incompEG100);
		compatibilityManager.addIncompatibilities(EG133, incompEG133);
		compatibilityManager.addIncompatibilities(ED110, incompED110);
		compatibilityManager.addIncompatibilities(EG210, incompEG210);
		compatibilityManager.addIncompatibilities(TM5, incompTM5);
		compatibilityManager.addRequirements(IS, reqIS);
		compatibilityManager.addRequirements(XS, reqXS);
		compatibilityManager.addRequirements(EH120, reqEH120);
		compatibilityManager.addRequirements(TC120, reqTC120);
	
		return configuration;
	}
}

