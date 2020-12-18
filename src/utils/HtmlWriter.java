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
		writer.write("<!DOCTYPE html><html><body>");
		writer.write("<h1>Configuration</h1>");
		
        Set<Part> parts = configuration.getSelectedParts();

		
	}

}
