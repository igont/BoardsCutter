package org.example.Comparators.Print;

import org.example.DataTypes.Marking;

import java.util.Comparator;
import java.util.List;

public class SortByCount implements Comparator<List<Marking>>
{
	@Override
	public int compare(List<Marking> o1, List<Marking> o2)
	{
		return o1.size() - o2.size();
	}
}
