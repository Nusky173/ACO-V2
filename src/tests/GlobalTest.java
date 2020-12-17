package tests;

import org.junit.Test;

import api.*;
import exceptions.InvalidParameterException;
import impl.*;
import parts.*;

public class GlobalTest
{
	@Test
	public void Test1() throws InvalidParameterException
	{
		ConfigurationImpl config = TestsContainer.buildConfiguration();
		
		PartImpl eg100 = new EG100();
		
		eg100.getPropertyNames();
	
	}
}
