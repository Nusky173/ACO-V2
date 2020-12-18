package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import api.Category;
import api.Part;
import api.PartType;
import enums.CategoryType;
import enums.GasType;

import exceptions.InvalidParameterException;
import impl.Session;
import utils.HtmlWriter;

public class Commands 
{
	public static final Logger logger =  Logger.getGlobal();
	
	private final static String OutputFilename  = "export.html";
	
	/**
	* Returns an Image object that can then be painted on the screen. 
	* The url argument must specify an absolute <a href="#{@link}">{@link URL}</a>. The name
	* argument is a specifier that is relative to the url argument. 
	* <p>
	* This method always returns immediately, whether or not the 
	* image exists. When this applet attempts to draw the image on
	* the screen, the data will be loaded. The graphics primitives 
	* that draw the image will incrementally paint on the screen. 
	*
	* @param  url  an absolute URL giving the base location of the image
	* @param  name the location of the image, relative to the url argument
	* @return      the image at the specified URL
	* @see         Image
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
	
	@Command(name = "COMPLETE", role="User")
	public static void IsComplete() throws InvalidParameterException
	{
		/*
		 * Here we use System.out because the GUI will use the 
		 * jar output stream.
		 */
		System.out.println(Session.INSTANCE.configuration.isComplete());
	}
	
	@Command(name = "VALID", role="User")
	public static void IsValid() throws InvalidParameterException
	{
		/*
		 *	Here we use System.out because the GUI will use the 
		 *	jar output stream.
		 */
		System.out.println(Session.INSTANCE.configuration.isValid());
	}
	
	@Command(name = "EXPORT",role="User")
	public static void Export() throws IOException
	{
		HtmlWriter writer = new HtmlWriter(OutputFilename);
		writer.writeConfiguration(Session.INSTANCE.configuration);
		writer.save();
		logger.info("Configuration exported.");
	}
	
	
	@Command(name = "CATEGORIES",role="User")
	public static void Categories() throws IOException
	{
		ArrayList<String> cats = new ArrayList<String>();
		
		for (Category category : Session.INSTANCE.configurator.getCategories())
		{
			cats.add(category.getName());
		}
		
		String result = String.join(",", cats);
		System.out.println(result);
	}
	
	
	@Command(name = "VARIANTS",role="User")
	public static void Variants(String rawCategory) throws IOException
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
	
	@Command(name = "UNSELECT",role="User")
	public static void Unselect(String rawCategory) throws InvalidParameterException
	{
		CategoryType type = CategoryType.valueOf(rawCategory);
		
		Category category = Session.INSTANCE.configurator.getCategory(type);
		
		Session.INSTANCE.configuration.unselectPartType(category);
	}
	
	@Command(name = "ADDCOMP",role="Admin")
	public static void AddIncompatibility(String rawCategory) throws InvalidParameterException
	{
		CategoryType type = CategoryType.valueOf(rawCategory);
		
		Category category = Session.INSTANCE.configurator.getCategory(type);
		
		Session.INSTANCE.configuration.unselectPartType(category);
	}
	@Command(name = "RMCOMP",role="Admin")
	public static void RemoveIncomp(String rawCategory) throws InvalidParameterException
	{
		CategoryType type = CategoryType.valueOf(rawCategory);
		
		Category category = Session.INSTANCE.configurator.getCategory(type);
		
		Session.INSTANCE.configuration.unselectPartType(category);
	}
}
