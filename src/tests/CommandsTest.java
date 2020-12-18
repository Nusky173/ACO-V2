package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import api.Part;
import api.PartType;
import commands.CommandsManager;
import impl.PartTypeImpl;
import impl.Session;

public class CommandsTest 
{
	@Test
	public void test1() throws IllegalAccessException, InvocationTargetException
	{
		Session.INSTANCE.reset();
		CommandsManager.initialize();
		CommandsManager.Handle("SELECT EG100");
		
		HashSet<PartType> set = new HashSet<PartType>();
		set.add(Session.INSTANCE.configurator.createInstance("EG100").get().getType());
		
		assertTrue(Session.INSTANCE.configuration.getSelectedParts().size() == 1);
	}
	
	@Test
	public void test2() throws IllegalAccessException, InvocationTargetException
	{
		Session.INSTANCE.reset();
		Set<PartType> set = new HashSet<PartType>();
		set = Session.INSTANCE.compatibilityManager.getIncompatibilities(Session.INSTANCE.configurator.createInstance("EG100").get().getType());
		CommandsManager.initialize();
		CommandsManager.Handle("ADDICMP EG100 TM5");
		
		set.add(Session.INSTANCE.configurator.createInstance("TM5").get().getType());
		
		assertEquals(set, Session.INSTANCE.compatibilityManager.getIncompatibilities(Session.INSTANCE.configurator.createInstance("EG100").get().getType()));
	}
}
