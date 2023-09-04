package org.example.Comparators.Markings;

import org.example.DataTypes.Marking;

import java.util.Comparator;

public class SortMarksByAmount implements Comparator<Marking>
{
	@Override
	public int compare(Marking o1, Marking o2)
	{
		return o2.amount - o1.amount;
	}
}
