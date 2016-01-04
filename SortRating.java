import java.util.ArrayList;

public class SortRating {
	
	private ArrayList<String> symbols = new ArrayList<String>(30);
	private ArrayList<Integer> ratingArray = new ArrayList<Integer>(30);
	private ArrayList<String> newSymbols = new ArrayList<String>(30);
	private ArrayList<Integer> newRatingArray = new ArrayList<Integer>(30);
	
	public SortRating(ArrayList<Integer> r1, String[] s1)
	{
		for(int i = 0 ; i < 30 ; i++)
			symbols.add(i, s1[i]);
		ratingArray = r1;
	}
	
	public void sort()
	{
		int biggest = 0;
		for(int i = 0 ; i < 30 ; i++)
		{
			biggest = 0;
			for(int j = 0 ; j < 30 - i ; j++)
			{
				if(ratingArray.get(j) >= ratingArray.get(biggest))
					biggest = j;
			}
			newRatingArray.add(i, ratingArray.get(biggest));
			newSymbols.add(i, symbols.get(biggest));
			ratingArray.remove(biggest);
			symbols.remove(biggest);
		}	
		
	}
	
	public String toString()
	{
		String string = "Sorted results: (";
		for(int i = 0 ; i < 30 ; i++)
		{
			string += newSymbols.get(i) + "=" + newRatingArray.get(i);
			if(i < 29)
				string += ", ";
			else
				string += ")";
		}
		return string;
	}
}
