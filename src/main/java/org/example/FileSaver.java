package org.example;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileSaver
{
	private static String outPath;
	private static final String TEMPLATE_NAME = "Boards Cutter template.xlsx";
	
	static
	{
		try
		{
			outPath = new File(FileSaver.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
			
			if(outPath.endsWith("jar")) outPath = new File(outPath).getParent() + "\\";
			else outPath = "C:\\Users\\Igor\\Desktop\\";
			
		}
		catch(URISyntaxException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void saveWithStreams()
	{
		try
		{
			InputStream is = FileSaver.class.getResourceAsStream("/" + TEMPLATE_NAME);
			OutputStream os = new FileOutputStream(outPath + TEMPLATE_NAME);
			
			byte[] buffer = new byte[1024];
			int length;
			while((length = is.read(buffer)) > 0)
			{
				os.write(buffer, 0, length);
			}
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static boolean isFileExist()
	{
		return new File(outPath).exists();
	}
	
	public static void noSave()
	{
	
	}
}
