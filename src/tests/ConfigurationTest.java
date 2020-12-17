package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import api.Category;
import api.Part;
import api.PartType;
import enums.CategoryType;
import exceptions.*;
import impl.CategoryImpl;
import impl.PartImpl;
import impl.PartTypeImpl;
import impl.Session;
import partsCategories.Engine;

public class ConfigurationTest 
{
	//getSelectedParts
	@Test
	public void test1()
	{
		Session session = new Session();
		HashSet<Part> set = new HashSet<Part>() ;
		assertEquals(session.configuration.getSelectedParts(), set);	
	}
	
	//selecPart
	@Test
	public void test2() throws InvalidParameterException
	{
		Session session = new Session();

		session.configuration.selectPart(session.configurator.CreateInstance("EG100"));
		
		Set<Part> partTypes = session.configuration.getSelectedParts();
		
		//on ajoute bien un truc dans la liste.
		assertFalse(session.configuration.getSelectedParts().isEmpty());
		//contient EG100
		for(Part p : partTypes)
		{
			assertEquals(p.getName(), "EG100");
		}
		//ne contient pas d'autre Part.
		assertFalse(session.configuration.getSelectedParts().contains(session.configurator.CreateInstance("XM")));
	}
	
	@Test
	public void test3() throws InvalidParameterException
	{
		Session session = new Session();

		session.configuration.selectPart(session.configurator.CreateInstance("EG100"));
		session.configuration.selectPart(session.configurator.CreateInstance("ED180"));
		
		Set<Part> partTypes = session.configuration.getSelectedParts();
				
		for(Part p : partTypes)
		{
			assertNotEquals(p.getName(), "EG100");
			assertEquals(p.getName(), "ED180");
		}
	}
	
	//isComplete
	@Test
	//robustess
	public void test4() throws InvalidParameterException, ConfigurationException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.CreateInstance("EG100"));
		session.configuration.selectPart(session.configurator.CreateInstance("TA5"));
		session.configuration.selectPart(session.configurator.CreateInstance("XM"));
		
		assertThrows(exceptions.InvalidParameterException.class, () -> {session.configuration.isComplete();});
	}
	
	@Test
	public void test5() throws InvalidParameterException, ConfigurationException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.CreateInstance("EG100"));
		session.configuration.selectPart(session.configurator.CreateInstance("TA5"));
		session.configuration.selectPart(session.configurator.CreateInstance("XM"));
		session.configuration.selectPart(session.configurator.CreateInstance("IS"));
		assertTrue(session.configuration.isComplete());
	}
	
	//unselectPart
	@Test

	public void test6() throws InvalidParameterException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.CreateInstance("EG100"));
		
		session.configuration.unselectPartType(session.configurator.CreateInstance("EG100").getCategory());
		
		assertTrue(session.configuration.getSelectedParts().isEmpty());
	}
	
	@Test
	//Robsutess
	public void test7() throws InvalidParameterException, ConfigurationException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.CreateInstance("EG100"));
		session.configuration.selectPart(session.configurator.CreateInstance("TA5"));
		session.configuration.selectPart(session.configurator.CreateInstance("XM"));
		session.configuration.selectPart(session.configurator.CreateInstance("IS"));
		
		session.configuration.unselectPartType(session.configurator.CreateInstance("EG100").getCategory());
		
		assertThrows(exceptions.InvalidParameterException.class, () -> {session.configuration.isComplete();});
	}
	
	@Test
	//Robustess
	public void test8() throws InvalidParameterException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.CreateInstance("XM"));
		
		assertThrows(exceptions.InvalidParameterException.class, () -> {session.configuration.unselectPartType(session.configurator.CreateInstance("EG100").getCategory());});
	}	
	
	
	//getSelectionForCategory
	@Test
	public void test9() throws InvalidParameterException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.CreateInstance("XM"));
		
		assertEquals(session.configuration.getSelectionForCategory(session.configurator.CreateInstance("XM").getCategory()).get().getName(), "XM");		
	}	
	
	@Test
	public void test10() throws InvalidParameterException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.CreateInstance("EG100"));
		
		assertThrows(exceptions.InvalidParameterException.class, () -> { session.configuration.getSelectionForCategory(session.configurator.CreateInstance("XM").getCategory()).get().getName(); });		
	}	
	
	//clear
	@Test
	public void test11() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.CreateInstance("EG100"));
		session.configuration.selectPart(session.configurator.CreateInstance("TA5"));
		
		session.configuration.clear();
		
		assertTrue(session.configuration.getSelectedParts().isEmpty());
	}
	
	public void test12() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.clear();
		
		assertTrue(session.configuration.getSelectedParts().isEmpty());
	}
	

}
