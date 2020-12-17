package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import impl.Session;

public class PartTypeTest 
{
	
	@Test
	public void test() 
	{
		Session session = new Session();
		System.out.println(session.configurator.CreateInstance("EG100").getName());
		assertTrue(session.configurator.CreateInstance("EG100").getName() == "EG100");
	}
}
