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
		set.add(session.configurator.CreateInstance("EG100").get().getCategory());
		set.add(session.configurator.CreateInstance("TA5").get().getCategory());
		set.add(session.configurator.CreateInstance("IN").get().getCategory());
		set.add(session.configurator.CreateInstance("XC").get().getCategory());

		assertEquals(session.configurator.getCategories(), set);	
	}

	//getCategory
	@Test
	public void test2()
	{
		Session session = new Session();

		HashSet<Category> set = new HashSet<Category>();
		set.add(session.configurator.CreateInstance("ED110").get().getCategory());
		set.add(session.configurator.CreateInstance("TSF7").get().getCategory());
		set.add(session.configurator.CreateInstance("IS").get().getCategory());
		set.add(session.configurator.CreateInstance("XM").get().getCategory());

		assertEquals(session.configurator.getCategories(), set);	
	}
	//getVariants
	@Test
	public void test3()
	{
		Session session = new Session();
		HashSet<PartType> set = new HashSet<PartType>();
		//getType already test.
		set.add(session.configurator.CreateInstance("EG100").get().getType());
		set.add(session.configurator.CreateInstance("ED180").get().getType());
		set.add(session.configurator.CreateInstance("EH120").get().getType());
		set.add(session.configurator.CreateInstance("EG133").get().getType());
		set.add(session.configurator.CreateInstance("EG210").get().getType());

		assertNotEquals(session.configurator.getVariants(session.configurator.CreateInstance("EG100").get().getCategory()), set);	

		set.add(session.configurator.CreateInstance("ED110").get().getType());		
		//getCategory already valid.
		assertEquals(session.configurator.getVariants(session.configurator.CreateInstance("EG100").get().getCategory()), set);
	}	

}
