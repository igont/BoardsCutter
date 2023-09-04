package org.example.Comparators.Markings;

import org.example.DataTypes.Marking;

import java.util.Comparator;
import java.util.List;

public class SortMarksByLength implements Comparator<Marking>
{
	@Override
	public int compare(Marking o1, Marking o2)
	{
		return o1.length - o2.length;
	}
}
