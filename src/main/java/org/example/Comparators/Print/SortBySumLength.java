package org.example.Comparators.Print;

import org.example.DataTypes.Marking;

import java.util.Comparator;
import java.util.List;

public class SortBySumLength implements Comparator<List<Marking>>
{
	@Override
	public int compare(List<Marking> o1, List<Marking> o2)
	{
		int len = 0;
		for(Marking m: o1) len -= m.length;
		for(Marking m: o2) len += m.length;
		
		if(len == 0) return o1.size() - o2.size();
		
		return len;
	}
}
