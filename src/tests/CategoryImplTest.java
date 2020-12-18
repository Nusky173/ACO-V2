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
		assertEquals(session.configurator.createInstance("EG100").get().getCategory().getName(), "Engine");
	}
}
