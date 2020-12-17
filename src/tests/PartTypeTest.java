package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import impl.CategoryImpl;
import impl.PartTypeImpl;
import impl.Session;
import parts.EG100;
import partsCategories.Engine;

public class PartTypeTest 
{
	@Test
	public void test1() 
	{
		Session session = new Session();
		assertEquals(session.configurator.CreateInstance("EG100").get().getName(), "EG100");
	}
	
	@Test
	public void test2() 
	{
		Session session = new Session();
		assertEquals(session.configurator.CreateInstance("EG100").get().getCategory().getName(), "Engine");
	}

}
