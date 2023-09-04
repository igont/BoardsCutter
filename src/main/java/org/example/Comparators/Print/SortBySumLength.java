package org.example.Comparators.Print;

import org.example.DataTypes.Marking;
import org.example.DataTypes.Sample;

import java.util.Comparator;
import java.util.List;

public class SortBySumLength implements Comparator<Sample>
{
	@Override
	public int compare(Sample o1, Sample o2)
	{
		int diff = o2.getSumLength() - o1.getSumLength();
		if(diff == 0) return o1.getCountDesks() - o2.getCountDesks();
		
		return diff;
	}
}
