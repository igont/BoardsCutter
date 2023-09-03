package org.example;

import org.example.Comparators.SortBySumLength;
import org.example.DataTypes.Marking;
import org.example.DataTypes.Material;
import org.example.DataTypes.Statistics;

import java.util.*;

public class Calculator
{
	public Set<List<Marking>> allowedCombinations = new HashSet<>();
	public List<Material> materials;
	public List<Marking> markings;
	public List<List<Marking>> splitMarkings = new ArrayList<>();
	public int allowedAmount = 500;
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
			System.out.println("Выполнен расчет для материала: " + markings1.get(0).material.name);
			System.out.println();
		}
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		
		statistics.durationMillis = duration / 1000f;
		
		//printResults();
		return statistics;
	}
	
	private void printResults()
	{
		
		List<List<Marking>> list = new ArrayList<>(allowedCombinations.stream().toList());
		list.sort(new SortBySumLength());
		
		for(List<Marking> combination : list)
		{
			printCombination("", combination);
		}
	}
	
	public void calculateSingleMaterial(List<Marking> markings) // Выполняет все расчеты для одного материала
	{
		List<Marking> sample = new ArrayList<>();
		addNewMark(sample, markings);
	}
	
	private void addNewMark(List<Marking> sample, List<Marking> markings) // Добавляет к существующей раскладке каждую из марок набора
	{
		for(Marking marking : markings)
		{
			List<Marking> sampleNew = new ArrayList<>(sample);
			sampleNew.add(marking);
			
			checkRest(sampleNew, markings);
			
			statistics.combinations++;
		}
	}
	
	private void checkRest(List<Marking> sample, List<Marking> markings) // Проверяет оставшуюся длину и решает что делать с набором марок
	{
		int matLength = sample.get(0).material.length;
		int sampleLength = 0;
		
		for(Marking marking : sample)
		{
			sampleLength += marking.length;
		}
		int amount = matLength - sampleLength;
		
		if(amount < 0)
		{
			//printCombination("[BAD]", sample);
		}
		else if(amount < allowedAmount)
		{
			Collections.sort(sample);
			allowedCombinations.add(sample);
		}
		else
		{
			//printCombination("[CONTINUE]", sample);
			addNewMark(sample, markings);
		}
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
				if(mark.material == mat) splitMarkings.get(count).add(mark);
			}
			count++;
		}
	}
}
