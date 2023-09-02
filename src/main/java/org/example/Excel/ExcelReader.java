package org.example.Excel;

import org.example.DataTypes.Marking;
import org.example.DataTypes.Material;
import org.example.FileSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader
{
	public List<Material> materials = new ArrayList<>();
	public List<Marking> markings = new ArrayList<>();
	public void readAllData()
	{
		File excelBookFile = new File(FileSaver.outPath + FileSaver.TEMPLATE_NAME);
		ExcelParser parser = null;
		try
		{
			parser = new ExcelParser(excelBookFile);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
		int row = 4;
		
		while (true)
		{
			Material material = new Material();
			try
			{
				material.name = parser.getCell(row,2).getStringCellValue();
			}
			catch(IllegalStateException e)
			{
				break;
			}
			
			material.length = (int) parser.getCell(row,3).getNumericCellValue();
			
			materials.add(material);
			row++;
		}
		
		row = 4;
		while (true)
		{
			Marking marking = new Marking();
			marking.name = parser.getCell(row,5).getStringCellValue();
			String matName = parser.getCell(row,6).getStringCellValue();
			
			for(Material findMat : materials)
			{
				if(findMat.name.equals(matName)) marking.material = findMat;
			}
			
			marking.length = (int) parser.getCell(row,7).getNumericCellValue();
			marking.amount = (int) parser.getCell(row,8).getNumericCellValue();
			
			
			if(marking.name.isEmpty()) break;
			markings.add(marking);
			row++;
		}
	}
}
