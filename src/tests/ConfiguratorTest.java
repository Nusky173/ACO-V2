package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;

import org.junit.Test;

import api.Category;
import api.PartType;
import impl.Session;

public class ConfiguratorTest 
{
	//getCategory
	@Test
	public void test1()
	{
		Session session = new Session();

		HashSet<Category> set = new HashSet<Category>();
		set.add(session.configurator.CreateInstance("EG100").getCategory());
		set.add(session.configurator.CreateInstance("TA5").getCategory());
		set.add(session.configurator.CreateInstance("IN").getCategory());
		set.add(session.configurator.CreateInstance("XC").getCategory());

		assertEquals(session.configurator.getCategories(), set);	
	}

	//getCategory
	@Test
	public void test2()
	{
		Session session = new Session();

		HashSet<Category> set = new HashSet<Category>();
		set.add(session.configurator.CreateInstance("ED110").getCategory());
		set.add(session.configurator.CreateInstance("TSF7").getCategory());
		set.add(session.configurator.CreateInstance("IS").getCategory());
		set.add(session.configurator.CreateInstance("XM").getCategory());

		assertEquals(session.configurator.getCategories(), set);	
	}
	//getVariants
	@Test
	public void test3()
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		//getType already test.
		set.add(session.configurator.CreateInstance("EG100").getType());
		set.add(session.configurator.CreateInstance("ED180").getType());
		set.add(session.configurator.CreateInstance("EH120").getType());
		set.add(session.configurator.CreateInstance("EG133").getType());
		set.add(session.configurator.CreateInstance("EG210").getType());

		assertNotEquals(session.configurator.getVariants(session.configurator.CreateInstance("EG100").getCategory()), set);	

		set.add(session.configurator.CreateInstance("ED110").getType());		
		//getCategory already valid.
		assertEquals(session.configurator.getVariants(session.configurator.CreateInstance("EG100").getCategory()), set);
	}	

}
