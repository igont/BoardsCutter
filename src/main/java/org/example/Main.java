package org.example;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
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
		
		saveOrNor();
	}
	
	private static void saveOrNor()
	{
		System.out.println("Скачать файл? (Y/N)");
		System.out.print("- ");
		
		Scanner sc = new Scanner(System.in);
		String answer =  sc.nextLine();
		
		if(answer.equals("Y")) FileSaver.save();
		else if(answer.equals("N")) FileSaver.noSave();
		else
		{
			System.out.println();
			System.out.println("Введите символ \"Y\" для скачаивания или \"N\" для отмены!");
			saveOrNor();
		}
	}
}