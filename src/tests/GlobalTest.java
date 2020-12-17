package tests;

import org.junit.Test;

import api.*;
import exceptions.InvalidParameterException;
import impl.*;

public class GlobalTest
{
	@Test
	public void Test1() throws InvalidParameterException
	{
		ConfigurationImpl config = TestsContainer.buildConfiguration();
		
		PartImpl eg100priced =  TestsContainer.EG100.newInstance();
		
		eg100priced.setProperty("gas", "Gasoline");
		
		config.selectPart(eg100priced);
	
	}
}
