package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Optional;
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

		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		
		Set<Part> partTypes = session.configuration.getSelectedParts();
		
		//on ajoute bien un truc dans la liste.
		assertFalse(session.configuration.getSelectedParts().isEmpty());
		//contient EG100
		for(Part p : partTypes)
		{
			assertEquals(p.getName(), "EG100");
		}
		//ne contient pas d'autre Part.
		assertFalse(session.configuration.getSelectedParts().contains(session.configurator.createInstance("XM")));
	}
	
	@Test
	public void test3() throws InvalidParameterException
	{
		Session session = new Session();

		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		session.configuration.selectPart(session.configurator.createInstance("ED180").get());
		
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
		
		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		session.configuration.selectPart(session.configurator.createInstance("TA5").get());
		session.configuration.selectPart(session.configurator.createInstance("XM").get());
		
		assertFalse(session.configuration.isComplete());
	}
	
	@Test
	public void test5() throws InvalidParameterException, ConfigurationException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		session.configuration.selectPart(session.configurator.createInstance("TA5").get());
		session.configuration.selectPart(session.configurator.createInstance("XM").get());
		session.configuration.selectPart(session.configurator.createInstance("IS").get());
		assertTrue(session.configuration.isComplete());
	}
	
	//unselectPart
	@Test

	public void test6() throws InvalidParameterException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		
		session.configuration.unselectPartType(session.configurator.createInstance("EG100").get().getCategory());
		
		assertTrue(session.configuration.getSelectedParts().isEmpty());
	}
	
	@Test
	//Robsutess
	public void test7() throws InvalidParameterException, ConfigurationException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		session.configuration.selectPart(session.configurator.createInstance("TA5").get());
		session.configuration.selectPart(session.configurator.createInstance("XM").get());
		session.configuration.selectPart(session.configurator.createInstance("IS").get());
		
		session.configuration.unselectPartType(session.configurator.createInstance("EG100").get().getCategory());
		
		assertFalse(session.configuration.isComplete());
	}
	
	@Test
	//Robustess
	public void test8() throws InvalidParameterException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("XM").get());
		
		assertThrows(exceptions.InvalidParameterException.class, () -> {session.configuration.unselectPartType(session.configurator.createInstance("EG100").get().getCategory());});
	}	
	
	
	//getSelectionForCategory
	@Test
	public void test9() throws InvalidParameterException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("XM").get());
		
		assertEquals(session.configuration.getSelectionForCategory(session.configurator.createInstance("XM").get().getCategory()).get().getName(), "XM");		
	}	
	
	@Test
	public void test10() throws InvalidParameterException
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		
		assertEquals(session.configuration.getSelectionForCategory(session.configurator.createInstance("XM").get().getCategory()), Optional.empty());		
	}	
	
	//clear
	@Test
	public void test11() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		session.configuration.selectPart(session.configurator.createInstance("TA5").get());
		
		session.configuration.clear();
		
		assertTrue(session.configuration.getSelectedParts().isEmpty());
	}
	
	public void test12() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.clear();
		
		assertTrue(session.configuration.getSelectedParts().isEmpty());
	}
	
	@Test
	public void test13() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		
		assertTrue(session.configuration.isValid());
	}
	
	@Test
	public void test14() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		session.configuration.selectPart(session.configurator.createInstance("TM5").get());
		session.configuration.selectPart(session.configurator.createInstance("XS").get());
		session.configuration.selectPart(session.configurator.createInstance("IS").get());
		//IS/XS incompatible with TM5 & EG100
		assertFalse(session.configuration.isValid());
	}
	@Test
	public void test15() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG133").get());
		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		session.configuration.selectPart(session.configurator.createInstance("TM6").get());
		session.configuration.selectPart(session.configurator.createInstance("XS").get());
		session.configuration.selectPart(session.configurator.createInstance("IS").get());
		//XS/IS incompatible with EG100 
		assertFalse(session.configuration.isValid());
	}
	
	@Test
	public void test16() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG133").get());
		session.configuration.selectPart(session.configurator.createInstance("TSF7").get());
		
		assertFalse(session.configuration.isValid());
	}
	
	@Test
	public void test17() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("XS").get());
		//EG100 incompatible with TSF7
		assertFalse(session.configuration.isValid());
	}
	
	@Test
	public void test18() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG133").get());
		session.configuration.selectPart(session.configurator.createInstance("TM6").get());
		session.configuration.selectPart(session.configurator.createInstance("XS").get());
		//XS require IS
		assertFalse(session.configuration.isValid());
	}
	
	@Test
	public void test19() throws InvalidParameterException 
	{
		Session session = new Session();
		
		session.configuration.selectPart(session.configurator.createInstance("EG100").get());
		session.configuration.selectPart(session.configurator.createInstance("TM5").get());
		session.configuration.selectPart(session.configurator.createInstance("XS").get());
		session.configuration.selectPart(session.configurator.createInstance("IS").get());
		//IS/XS incompatible with TM5 & EG100
		assertFalse(session.configuration.isValid());
	}
	

}
