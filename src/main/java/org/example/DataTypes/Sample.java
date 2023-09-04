package org.example.DataTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sample
{
	private List<Marking> marks = new ArrayList<>();
	
	public Sample()
	{
	
	}
	
	public Sample(List<Marking> marks)
	{
		this.marks = marks;
	}
	
	public void addMark(Marking m)
	{
		if(getSumLength() + m.length <= m.material.length) marks.add(m);
		else throw new RuntimeException("Доска " + m + "не поместилась в шаблон " + this);
	}
	
	public int getSumLength()
	{
		int len = 0;
		for(Marking marking : marks) len += marking.length;
		return len;
	}
	
	public int getCountMarks()
	{
		return new HashSet<>(marks).size();
	}
	
	public int getCountDesks()
	{
		return marks.size();
	}
	
	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder(marks.get(0).material.length + "");
		int sum = 0;
		
		for(Marking marking : marks)
		{
			sum += marking.length;
			s.append(" - ").append(marking.length);
		}
		
		s.append(" = ").append(marks.get(0).material.length - sum).append("\n");
		
		return s.toString();
	}
}
