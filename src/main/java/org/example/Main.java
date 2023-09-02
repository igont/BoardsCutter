package org.example;

import org.example.Excel.ExcelParser;
import org.example.Excel.ExcelReader;

import java.sql.SQLOutput;
import java.util.Scanner;


public class Main
{
	public static Scanner sc = new Scanner(System.in);
	public static ExcelReader excelReader = new ExcelReader();
	
	public static void main(String[] args)
	{
		try
		{
			System.out.println("This is Board Cutter by Igor Gontarenko");
			System.out.println("---------------------------------------");
			System.out.println();
			System.out.println("Последовательность работы:");
			System.out.println("1) Скачайте шаблон рабочего файла в ту же папку, откуда запускается программа (Board Cutter ниже предложит его скачать)");
			System.out.println("2) Внесите необходимые данные в шаблон (Board Cutter закроется)");
			System.out.println();
			System.out.println("3) Запустите Board Cutter еще раз, он увидит изменения в файле (Перемещать или переименовывать файл нельзя, Board Cutter его потеряет)");
			System.out.println("4) Подтвердите начало вычислений");
			System.out.println();
			
			if(FileSaver.isFileExist())
			{
				System.out.println("Файл шаблона обнаружен, Вы находитесь на 4 пункте");
				System.out.println("Расположение файла: " + FileSaver.outPath + FileSaver.TEMPLATE_NAME);
				excelReader.readAllData();
				System.out.println();
				System.out.println("Почитаны данные:");
				System.out.println("Материалы: " + excelReader.materials.size() + " шт");
				System.out.println("Марки: " + excelReader.markings.size() + " шт");
				
				System.out.println();
				System.out.println("Начать расчет оптимального раскроя? (Y/N)");
				
			}
			else
			{
				System.out.println("Файл шаблона не обнаружен, Вы находитесь на 1 пункте");
				
				FileSaver.saveWithStreams();
				System.out.println();
				System.out.println("Файл успешно получен и расположен по пути: " + FileSaver.outPath + FileSaver.TEMPLATE_NAME);
				System.out.println("Внесите в него изменения и снова зайдите в Board Cutter");
				
			}
			
			sc.nextLine();
		}
		catch(Exception e)
		{
			System.out.println();
			System.out.println("FATAL EXCEPTION:");
			e.printStackTrace();
			sc.nextLine();
		}
		
	}
	
	private static void saveOrNot()
	{
		System.out.println("");
		System.out.print("- ");
		
		String answer = sc.nextLine();
		
		if(answer.equalsIgnoreCase("Y"))
		{
		
		}
		else if(answer.equalsIgnoreCase("N")) FileSaver.noSave();
		else
		{
			System.out.println();
			System.out.println("Введите символ \"Y\" для скачаивания или \"N\" для отмены!");
			saveOrNot();
		}
	}
	
	private static boolean change(String text)
	{
		System.out.println(text);
		System.out.print("- ");
		String answer = sc.nextLine();
		
		if(answer.equalsIgnoreCase("Y")) return true;
		else if(answer.equalsIgnoreCase("N")) return false;
		else
		{
			System.out.println();
			System.out.println("Введите символ \"Y\" для скачаивания или \"N\" для отмены!");
			change(text);
		}
		return true;
	}
}