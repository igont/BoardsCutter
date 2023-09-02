package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main
{
	public static Scanner sc = new Scanner(System.in);
	
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
			System.out.println("4) Продолжите работу с настройками");
			System.out.println();
			
			if(FileSaver.isFileExist())
			{
				System.out.println("Файл шаблона обнаружен, Вы находитесь на 4 пункте");
			}else
			{
				System.out.println("Файл шаблона не обнаружен, Вы находитесь на 1 пункте");
				saveOrNot();
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
		System.out.println("Скачать файл? (Y/N)");
		System.out.print("- ");
		
		String answer = sc.nextLine();
		
		if(answer.equalsIgnoreCase("Y")) FileSaver.saveWithStreams();
		else if(answer.equalsIgnoreCase("N")) FileSaver.noSave();
		else
		{
			System.out.println();
			System.out.println("Введите символ \"Y\" для скачаивания или \"N\" для отмены!");
			saveOrNot();
		}
	}
}