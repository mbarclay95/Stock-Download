import java.util.ArrayList;

public class Rating{
	
	private int rating;
	private ArrayList<HistoryData> data;
	
	public Rating(ArrayList<HistoryData> data1)
	{
		data = data1;
	}
	
	public ArrayList<HistoryData> getDataList()
	{
		return data;
	}
	
	public void setDataList(ArrayList<HistoryData> data1)
	{
		data = data1;
	}
	
	public int getRating()
	{
		return rating;
	}
	
	public void setRating(int rating1)
	{
		rating = rating + rating1;
	}
	
	public void compute()
	{}
}
