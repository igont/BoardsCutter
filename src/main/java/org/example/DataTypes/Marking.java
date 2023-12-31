package org.example.DataTypes;

public class Marking implements Comparable<Marking>
{
	public String name;
	public Material material;
	public int length;
	public int amount;
	
	@Override
	public int compareTo(Marking compare)
	{
		return compare.length - length;
	}
	
	@Override
	public String toString()
	{
		return "Marking{" + "name= '" + name + '\'' + ", material= " + material + ", length= " + length + '}';
	}
}
