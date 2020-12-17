package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import api.Category;
import api.PartType;
import impl.CategoryImpl;
import impl.PartImpl;
import impl.PartTypeImpl;
import impl.Session;
import parts.EG100;

public class PartTypeTest 
{
	
	@Test
	public void test1() 
	{
		Session session = new Session();
		assertEquals(session.configurator.CreateInstance("EG100").getName(), "EG100");
	}
	
	@Test
	public void test2() 
	{
		Session session = new Session();
		assertEquals(session.configurator.CreateInstance("EG100").getCategory().getName(), "Engine");
	}
	
	@Test
	public void test3() 
	{
		Session session = new Session();
		assertEquals(session.configurator.CreateInstance("EG100").getType(), session.);
	}
}
