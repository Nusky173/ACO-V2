package commands;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import api.Category;
import api.Part;
import api.PartType;
import enums.CategoryType;
import enums.GasType;

import exceptions.InvalidParameterException;
import impl.PartTypeImpl;
import impl.Session;
import utils.HtmlWriter;

public class Commands 
{
	public static final Logger logger =  Logger.getGlobal();
	
	private final static String ExportFilename  = "export.html";
	
	/**
	 * Command that select a part
	 * @param param name of the selected part
	 * @throws InvalidParameterException if the part is invalid
	 */
	@Command(name ="SELECT", role="User")
	public static void SelectPart(String param) throws InvalidParameterException
	{
		Optional<Part> part = Session.INSTANCE.configurator.createInstance(param);
		
		if (!part.isPresent())
		{
			logger.warning("Part not found.");
			return;
		}
		
		Session.INSTANCE.configuration.selectPart(part.get());
		
		logger.info("Part "+part.get().getName()+" selected.");
	}
	/**
	 * Command that write
	 * Configuration.isComplete() in the standart output stream
	 * 
	 * Here we are using System.out.println() instead of 
	 * Logger.info() because GUI is using the standard output
	 */
	@Command(name = "COMPLETE", role="User")
	public static void IsComplete()
	{
		/*
		 * Here we use System.out because the GUI will use the 
		 * jar output stream.
		 */
		System.out.println(Session.INSTANCE.configuration.isComplete());
	}
	/**
	 * Command that write
	 * Configuration.isValid() in the standart output stream
	 * 
	 * Here we are using System.out.println() instead of 
	 * Logger.info() because GUI is using the standard output
	 */
	@Command(name = "VALID", role="User")
	public static void IsValid()
	{
		/*
		 *	Here we use System.out because the GUI will use the 
		 *	jar output stream.
		 */
		System.out.println(Session.INSTANCE.configuration.isValid());
	}
	/**
	 * Export the current configuration to HTML document.
	 * @throws IOException relative to html file
	 */
	@Command(name = "EXPORT",role="User")
	public static void Export() throws IOException
	{
		HtmlWriter writer = new HtmlWriter(ExportFilename);
		writer.writeConfiguration(Session.INSTANCE.configuration);
		writer.save();
		
		File file = new File(ExportFilename);
		Desktop.getDesktop().open(file);
		logger.info("Configuration exported.");
	}
	
	/**
	 * Return all available categories.
	 */
	@Command(name = "CATEGORIES",role="User")
	public static void Categories()
	{
		ArrayList<String> cats = new ArrayList<String>();
		
		for (Category category : Session.INSTANCE.configurator.getCategories())
		{
			cats.add(category.getName());
		}
		
		String result = String.join(",", cats);
		System.out.println(result);
	}
	/**
	 * Return all parts that has category raw category
	 * @param rawCategory the specified category
	 */
	@Command(name = "VARIANTS",role="User")
	public static void Variants(String rawCategory) 
	{
		CategoryType type = CategoryType.valueOf(rawCategory);
		
		Category category = Session.INSTANCE.configurator.getCategory(type);
		
		Set<PartType> parts = Session.INSTANCE.configurator.getVariants(category);
		
		ArrayList<String> partsStr = new ArrayList<String>();
		
		for (PartType partType : parts)
		{
			partsStr.add(partType.getName());
		}
		
		String result = String.join(",", partsStr);
		System.out.println(result);
	}
	/**
	 * Write all selected part name to the standard output stream
	 */
	@Command(name = "VIEW",role="User")
	public static void View()
	{
		Set<Part> parts =Session.INSTANCE.configuration.getSelectedParts();
		
		ArrayList<String> partsStr = new ArrayList<String>();
		
		for (Part partType : parts)
		{
			partsStr.add(partType.getName()+" ("+partType.getCategory()+")");
		}
		
		String result = String.join(",", partsStr);
		System.out.println(result);
		
	}
	/**
	 * Unselect a part 
	 * @param rawCategory part category to remove
	 * @throws InvalidParameterException category should be member of CategoryType.java
	 */
	@Command(name = "UNSELECT",role="User")
	public static void Unselect(String rawCategory) throws InvalidParameterException
	{
		CategoryType type = CategoryType.valueOf(rawCategory);
		
		Category category = Session.INSTANCE.configurator.getCategory(type);
		
		Session.INSTANCE.configuration.unselectPartType(category);
	}
	/**
	 * Define rawTarget part incompatible with RawRef part.
	 * @param rawRef the PartType reference
	 * @param rawTarget the PartType target
	 * @throws InvalidParameterException rawRef and rawTarget should be valid PartTypes.
	 */
	@Command(name = "ADDICMP",role="Admin")
	public static void AddIncompatibility(String rawRef,String rawTarget) throws InvalidParameterException
	{
		Optional<PartTypeImpl> reference = Session.INSTANCE.configurator.getPartType(rawRef);
		Optional<PartTypeImpl> target = Session.INSTANCE.configurator.getPartType(rawTarget);
		
		if (!reference.isPresent())
		{
			logger.warning("Reference "+rawRef+" cannot be found.");
			return;
		}
		if (!target.isPresent())
		{
			logger.warning("Target "+rawTarget+" cannot be found.");
			return;
		}
		
		Set<PartType> targetSet = new HashSet<PartType>();
		targetSet.add(target.get());
		
		Session.INSTANCE.compatibilityManager.addIncompatibilities(reference.get(), targetSet);
	}
	/**
	 * Remove an incompatibility between RawRef and RawTarget.
	 * @param rawRef the PartType reference
	 * @param rawTarget the PartType target
	 * @throws InvalidParameterException rawRef and rawTarget should be valid PartTypes 
	 * and they should have incompatibility together.
	 */
	@Command(name = "RMICMP",role="Admin")
	public static void RemoveIncomp(String rawRef,String rawTarget) throws InvalidParameterException
	{
		Optional<PartTypeImpl> reference = Session.INSTANCE.configurator.getPartType(rawRef);
		Optional<PartTypeImpl> target = Session.INSTANCE.configurator.getPartType(rawTarget);
		
		if (!reference.isPresent())
		{
			logger.warning("Reference "+rawRef+" cannot be found.");
			return;
		}
		if (!target.isPresent())
		{
			logger.warning("Target "+rawTarget+" cannot be found.");
			return;
		}
		
		Set<PartType> targetSet = new HashSet<PartType>();
		targetSet.add(target.get());
		
		Session.INSTANCE.compatibilityManager.removeIncompatibility(reference.get(),target.get());
	}
	
	@Command(name = "ADDREQ",role="Admin")
	public static void AddRequirement(String rawRef,String rawTarget) throws InvalidParameterException
	{
		Optional<PartTypeImpl> reference = Session.INSTANCE.configurator.getPartType(rawRef);
		Optional<PartTypeImpl> target = Session.INSTANCE.configurator.getPartType(rawTarget);
		
		if (!reference.isPresent())
		{
			logger.warning("Reference "+rawRef+" cannot be found.");
			return;
		}
		if (!target.isPresent())
		{
			logger.warning("Target "+rawTarget+" cannot be found.");
			return;
		}

		Set<PartType> targetSet = new HashSet<PartType>();
		targetSet.add(target.get());
		
		Session.INSTANCE.compatibilityManager.addRequirements(reference.get(), targetSet);
		
	}
	@Command(name = "RMREQ",role="Admin")
	public static void RemoveRequirement(String rawRef,String rawTarget) throws InvalidParameterException
	{
		Optional<PartTypeImpl> reference = Session.INSTANCE.configurator.getPartType(rawRef);
		Optional<PartTypeImpl> target = Session.INSTANCE.configurator.getPartType(rawTarget);
		
		if (!reference.isPresent())
		{
			logger.warning("Reference "+rawRef+" cannot be found.");
			return;
		}
		if (!target.isPresent())
		{
			logger.warning("Target "+rawTarget+" cannot be found.");
			return;
		}
		
		Session.INSTANCE.compatibilityManager.removeRequirement(reference.get(), target.get());
	}
}
