import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class YahooHistoryDataDownloader extends HistoryDataDownloader{

	private char frequencyCode = 'd';
	private final String YAHOO_URL = "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&a="
			+ startMonth + "&b=" + startDay + "&c=" + startYear + "&d="
			+ stopMonth + "&e=" + stopDay + "&f=" + stopYear + "&g="
			+ frequencyCode + "&ignore=.csv";
	
	public YahooHistoryDataDownloader(String symbol1)
	{
		super(symbol1);
		//frequencyCode = 'd';
	}
	@Override
	public void download() {
		//System.out.println(YAHOO_URL);
		DataInputStream in=null;
		FileOutputStream fOut = null;
		DataOutputStream out = null;
		String fileName = getDataFolder()+File.separator+symbol+".csv";

		try {
			URL remoteFile = new URL(YAHOO_URL);
			URLConnection fileStream = remoteFile.openConnection();
			in = new DataInputStream(new BufferedInputStream(
					fileStream.getInputStream()));

			fOut = new FileOutputStream(fileName);
			out = new DataOutputStream(new BufferedOutputStream(fOut));

			int data;
			while ((data = in.read()) != -1) {
				out.write(data);
			}
		} catch (MalformedURLException e) {
			System.out.println("Please check the URL:" + e.toString());
		} catch (ConnectException e) {
			System.out.println(fileName.substring(0, fileName.length() - 3)
					+ ":failed! Connection Error!");
		} catch (FileNotFoundException e) {
			System.out.println("ERROR, make sure no other processes are using "
					+ fileName + " and run program again");
			System.out.println(fileName.substring(0, fileName.length() - 4)
					+ " will be skipped or innacurate");
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (fOut != null)
					fOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}		
	}
}
