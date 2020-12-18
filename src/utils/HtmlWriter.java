package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import api.*;
import impl.ConfigurationImpl;

public class HtmlWriter
{	
	private BufferedWriter writer;
	
	public HtmlWriter(String filename) throws IOException
	{
		File file = new File(filename);
		this.writer = new BufferedWriter(new FileWriter(file));
	}
	
	public void save() throws IOException 
	{
		writer.close();
	}

	public void writeConfiguration(ConfigurationImpl configuration) throws IOException
	{
		Set<Part> parts = configuration.getSelectedParts();
		 
		writer.write("<!DOCTYPE html><html><body>");
		writer.write("<h1>Configuration</h1>");
		
		for (Part part : parts)
		{
			writer.write("<table border=\"1\" cellspacing=\"1\" cellpadding=\"1\" width=\"200\" >");
			writer.write("<thead>");
			writer.write("<tr>");

			writer.write("<th >Nom</th>");
			writer.write("<th >Category</th>");
			
			Set<String> fields = part.getPropertyNames();
	        
			for (String field : fields)
			{
				writer.write("<th>"+field+"</th>");
			}

			writer.write("</tr>");
			writer.write("</thead>");
			writer.write("<tbody>");
		
			writer.write("<tr>");
	
			
			writer.write("<td>"+part.getName()+"</td>");
			writer.write("<td>"+part.getCategory().getName()+"</td>");
        	
			for (String field : fields)
			{
				writer.write("<td>"+part.getProperty(field).get()+"</td>");
			}

			writer.write("</tr>");
			writer.write("</tbody>");
			writer.write("</table>");
			writer.write("</br>");
		
	}
	}
}
