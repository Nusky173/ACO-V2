package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import impl.Session;
import api.*;
import exceptions.ConfigurationException;
import exceptions.InvalidParameterException;
import impl.*;

public class CompatibilityManagerTest 
{
	//getInocmpatibilities
	@Test
	public void test1()
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		set.add(session.configurator.createInstance("EG100").get().getType());
		
		assertEquals(set, session.compatibilityManager.getIncompatibilities(session.configurator.createInstance("TA5").get().getType()));
	}
	
	@Test
	public void test2()
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		set.add(session.configurator.createInstance("EG100").get().getType());
		set.add(session.configurator.createInstance("EG133").get().getType());
		set.add(session.configurator.createInstance("ED110").get().getType());

		assertEquals(set, session.compatibilityManager.getIncompatibilities(session.configurator.createInstance("TSF7").get().getType()));
	}
	
	//getIncompatibilities error
	@Test
	public void test3()
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		set.add(session.configurator.createInstance("EG100").get().getType());
		set.add(session.configurator.createInstance("EG133").get().getType());

		assertNotEquals(set, session.compatibilityManager.getIncompatibilities(session.configurator.createInstance("TSF7").get().getType()));
	}
	
	//getRequirements
	@Test 
	public void test4()
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		set.add(session.configurator.createInstance("EH120").get().getType());
		
		assertEquals(set, session.compatibilityManager.getRequirements(session.configurator.createInstance("TC120").get().getType()));
	}
	
	@Test 
	public void test5()
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		set.add(session.configurator.createInstance("TC120").get().getType());
		
		assertEquals(set, session.compatibilityManager.getRequirements(session.configurator.createInstance("EH120").get().getType()));
	}
	
	//getRequirements error
	@Test 
	public void test6()
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		set.add(session.configurator.createInstance("EG100").get().getType());
		
		assertNotEquals(set, session.compatibilityManager.getRequirements(session.configurator.createInstance("EH120").get().getType()));
	}
	
	//addIncompatibilities
	@Test 
	public void test7() throws InvalidParameterException
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		set.add(session.configurator.createInstance("EG100").get().getType());
		set.add(session.configurator.createInstance("XM").get().getType());
		session.compatibilityManager.addIncompatibilities(session.configurator.createInstance("TA5").get().getType(), set);
		assertEquals(set, session.compatibilityManager.getIncompatibilities(session.configurator.createInstance("TA5").get().getType()));
	}
	
	//addIncompatibilities error
	@Test 
	public void test8() throws InvalidParameterException
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		HashSet<PartType> set2 = new HashSet<PartType>();
		
		set.add(session.configurator.createInstance("EG100").get().getType());
		set.add(session.configurator.createInstance("XM").get().getType());
		set2 = (HashSet<PartType>) session.compatibilityManager.getIncompatibilities(session.configurator.createInstance("TA5").get().getType());
		
		session.compatibilityManager.addIncompatibilities(session.configurator.createInstance("TA5").get().getType(), set);
		
		assertNotEquals(set2, session.compatibilityManager.getIncompatibilities(session.configurator.createInstance("TA5").get().getType()));
	}
	
	//robustess
	@Test 
	public void test9() throws InvalidParameterException
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		
		
		assertThrows(exceptions.InvalidParameterException.class, () -> { 
				session.compatibilityManager.addIncompatibilities(session.configurator.createInstance("TA5").get().getType(), set); });
	}
	
	//addRequirements
	@Test 
	public void test10() throws InvalidParameterException
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		set.add(session.configurator.createInstance("TM5").get().getType());
		session.compatibilityManager.addRequirements(session.configurator.createInstance("TA5").get().getType(), set);
		assertEquals(set, session.compatibilityManager.getRequirements(session.configurator.createInstance("TA5").get().getType()));
	}

	//addRequirements error
	@Test 
	public void test11() throws InvalidParameterException
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		HashSet<PartType> set2 = new HashSet<PartType>();

		set.add(session.configurator.createInstance("IS").get().getType());
		set.add(session.configurator.createInstance("XM").get().getType());
		set2 = (HashSet<PartType>) session.compatibilityManager.getRequirements(session.configurator.createInstance("XS").get().getType());

		session.compatibilityManager.addRequirements(session.configurator.createInstance("TA5").get().getType(), set);

		assertNotEquals(set2, session.compatibilityManager.getRequirements(session.configurator.createInstance("TA5").get().getType()));
	}

	//robustess
	@Test 
	public void test12() throws InvalidParameterException
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();


		assertThrows(exceptions.InvalidParameterException.class, () -> { 
			session.compatibilityManager.addRequirements(session.configurator.createInstance("TA5").get().getType(), set); });
	}
	
	//removeIncompatibility
	@Test 
	public void test13() throws ConfigurationException
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		session.compatibilityManager.removeIncompatibility(session.configurator.createInstance("TA5").get().getType(), session.configurator.createInstance("EG100").get().getType());
		
		assertEquals(set, session.compatibilityManager.getIncompatibilities(session.configurator.createInstance("TA5").get().getType()));
	}
	
	//robustess can't remove an incompatibility unexistant.
	@Test 
	public void test14() throws ConfigurationException
	{
		Session session = new Session();
		assertThrows(exceptions.ConfigurationException.class, () -> { 
			session.compatibilityManager.removeIncompatibility(session.configurator.createInstance("TA5").get().getType(), session.configurator.createInstance("EG133").get().getType()) ; });
	}
	
	@Test 
	public void test15() throws ConfigurationException
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		set.add(session.configurator.createInstance("EG100").get().getType());
		session.compatibilityManager.removeIncompatibility(session.configurator.createInstance("IS").get().getType(), session.configurator.createInstance("TM5").get().getType());
		
		assertEquals(set, session.compatibilityManager.getIncompatibilities(session.configurator.createInstance("TA5").get().getType()));
	}
	
	//removeRequirements
	@Test 
	public void test16() throws ConfigurationException
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		session.compatibilityManager.removeRequirement(session.configurator.createInstance("IS").get().getType(), session.configurator.createInstance("XS").get().getType());

		assertEquals(set, session.compatibilityManager.getRequirements(session.configurator.createInstance("IS").get().getType()));
	}

	//robustess can't remove a require unexistant.
	@Test 
	public void test17() throws ConfigurationException
	{
		Session session = new Session();
		assertThrows(exceptions.ConfigurationException.class, () -> { 
			session.compatibilityManager.removeRequirement(session.configurator.createInstance("XS").get().getType(), session.configurator.createInstance("EG100").get().getType()) ; });
	}
}
