package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import impl.Session;

public class CategoryImplTest 
{
	@Test
	public void test1()
	{
		Session session = new Session();
		assertEquals(session.configurator.CreateInstance("EG100").getCategory().getName(), "Engine");
	}
}
