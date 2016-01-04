import java.util.Calendar;


public abstract class HistoryDataDownloader {
	
	protected String symbol;
	protected int startMonth = 01;
	protected int startDay = 01;
	protected int startYear = 2013;
	protected int stopMonth = 03;
	protected int stopDay = 16;
	protected int stopYear = 2015;
	protected char frequencyCode;
	protected final static String DATA_FOLDER = "data";
	
	public HistoryDataDownloader(String symbol1)
	{
		symbol = symbol1;
	}
	
	public void setStartDate(Calendar date)
	{
		this.startMonth = date.get(startMonth);
		this.startDay = date.get(startDay);
		this.startYear = date.get(startYear);
	}
	
	public void setStopDate(Calendar date)
	{
		this.stopMonth = date.get(startMonth);
		this.stopDay = date.get(startDay);
		this.stopYear = date.get(startYear);
	}
	
	public String getDataFolder()
	{
		return DATA_FOLDER;
	}
	
	public void download()
	{}
}
