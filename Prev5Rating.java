import java.util.ArrayList;

public class Prev5Rating extends Rating {
	
	public Prev5Rating(ArrayList<HistoryData> data1)
	{
		super(data1);
	}
	
	public void compute()
	{
		ArrayList<HistoryData> data = getDataList();
		float close_temp = data.get(data.size()-6).getClose();
		long volume_temp = data.get(data.size()-6).getVolume();
		for(int i = 5 ; i > 0 ; i--)
		{
			if(close_temp < data.get(data.size()-i).getClose() &&
					volume_temp < data.get(data.size()-i).getVolume())
				setRating(2);
			else if(close_temp < data.get(data.size()-i).getClose() &&
					volume_temp > data.get(data.size()-i).getVolume())
				setRating(1);
			else if(close_temp > data.get(data.size()-i).getClose() &&
					volume_temp > data.get(data.size()-i).getVolume())
				setRating(-1);
			else
				setRating(-2);
			close_temp = data.get(data.size()-i).getClose();
			volume_temp = data.get(data.size()-i).getVolume();
		}
	}
}
