
public class HistoryData {
	
	private String date;
	private float open, high, low, close, adj_close;
	private long volume;

	public HistoryData(String date1, float open1, float close1, float low1,
			float high1, long volume1, float adj_close1)
	{
		date = date1;
		open = open1;
		high = high1;
		low = low1;
		close = close1;
		volume = volume1;
		adj_close = adj_close1;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setDate(String date1)
	{
		date = date1;
	}
	
	public float getOpen()
	{
		return open;
	}
	
	public void setOpen(float open1)
	{
		open = open1;
	}
	
	public float getHigh()
	{
		return high;
	}
	
	public void setHigh(float high1)
	{
		high = high1;
	}
	
	public float getLow()
	{
		return low;
	}
	
	public void setLow(float low1)
	{
		low = low1;
	}
	
	public float getClose()
	{
		return close;
	}
	
	public void setClose(float close1)
	{
		close = close1;
	}
	
	public long getVolume()
	{
		return volume;
	}
	
	public void setVolume(long volume1)
	{
		volume = volume1;
	}
	
	public float getAdj_close()
	{
		return adj_close;
	}
	
	public void setAdj_close(float adj_close1)
	{
		adj_close = adj_close1;
	}
}
