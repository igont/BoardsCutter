package org.example;

import org.example.Comparators.Markings.SortMarksByAmount;
import org.example.Comparators.Markings.SortMarksByLength;
import org.example.Comparators.Print.SortBySumLength;
import org.example.DataTypes.Marking;
import org.example.DataTypes.Material;
import org.example.DataTypes.Statistics;

import java.util.*;

public class Calculator
{
	public List<List<Marking>> solution = new ArrayList<>();
	public List<Material> materials;
	public List<Marking> markings;
	public List<List<Marking>> splitMarkings = new ArrayList<>();
	public Statistics statistics = new Statistics();
	
	public Calculator(List<Material> materials, List<Marking> markings)
	{
		this.materials = materials;
		this.markings = markings;
	}
	
	public Statistics calculateAll()
	{
		long startTime = System.nanoTime();
		
		splitMarkings();
		
		for(List<Marking> markings1 : splitMarkings)
		{
			calculateSingleMaterial(markings1);
		}
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		
		statistics.durationMillis = duration / 1000f;
		
		printResults();
		return statistics;
	}
	
	private void printResults()
	{
		List<List<Marking>> list = new ArrayList<>(solution.stream().toList());
		list.sort(new SortBySumLength());
		
		for(List<Marking> combination : list)
		{
			printCombination("", combination);
		}
	}
	
	public void calculateSingleMaterial(List<Marking> markings) // Выполняет все расчеты для одного материала
	{
		List<Marking> sample = new ArrayList<>();
		int sum = 0;
		int maxLen = markings.get(0).material.length;
		
		while(!markings.isEmpty())
		{
			Marking m = getMark(markings); // Пытаемся добавить самые частые марки
			
			if(sum + m.length > maxLen) // Частая не подходит, пробуем самую маленькую
			{
				m = getSmallestMark(markings);
				
				if(sum + m.length > maxLen) // Маленькая не подходит, значит доска уже забита
				{
					solution.add(sample);
					sample = new ArrayList<>();
					statistics.combinations++;
					sum = 0;
				}
				else // Для маленькой есть место
				{
					sample.add(m);
					sum += m.length;
					markings.remove(m);
				}
			}
			else // Для частой есть место
			{
				sample.add(m);
				sum += m.length;
				markings.remove(m);
			}
		}
	}
	
	private Marking getSmallestMark(List<Marking> markings)
	{
		markings.sort(new SortMarksByLength());
		return markings.get(0);
	}
	
	private Marking getMark(List<Marking> markings)
	{
		markings.sort(new SortMarksByAmount());
		return markings.get(0);
	}
	
	
	private void printCombination(String info, List<Marking> sample)
	{
		System.out.print(info + sample.get(0).material.length);
		
		int sum = 0;
		for(Marking marking : sample)
		{
			sum += marking.length;
			System.out.print(" - " + marking.length);
		}
		
		System.out.print(" = " + (sample.get(0).material.length - sum) + "\n");
	}
	
	public void splitMarkings()
	{
		int count = 0;
		for(Material mat : materials)
		{
			splitMarkings.add(new ArrayList<>());
			
			for(Marking mark : markings)
			{
				for(int i = 0; i < mark.amount; i++)
					if(mark.material == mat) splitMarkings.get(count).add(mark);
			}
			count++;
		}
	}
}
