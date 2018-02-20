package q7.comparator;

import java.util.ArrayList;
import java.util.Comparator;

public class CompareEmployees implements Comparator<Employees> {

	public void sort()
	{
		ArrayList<Employees> s = new ArrayList<>();
		Employees Jon = new Employees("Jon", "Computers", 33);
		Employees Jack = new Employees("Jack", "Game Master", 22);
		int result = this.compare(Jon, Jack);
		if(result <= 0)
		{
			s.add(Jon);
			s.add(Jack);
		}
		else
		{
			s.add(Jack);
			s.add(Jon);
		}
		for(Employees aEmployee: s)
		{
			System.out.println(aEmployee.getName() + " " + aEmployee.getAge() + " " + aEmployee.getDepartment());
		}
	}
	@Override
	public int compare(Employees arg1, Employees arg2) 
	{
		String name1 = arg1.getName();
		String name2 = arg2.getName();
		if(name1.compareTo(name2) <= 0)
		{
		return 0;
		}
		else return 1;
	}

}
