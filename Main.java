import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.opencsv.CSVReader;


public class Main extends JFrame implements ActionListener{
	private YahooHistoryDataDownloader d1;
	private CSVReader read;
	private JPanel p = new JPanel();
	private JPanel top = new JPanel();
	private JPanel bottom = new JPanel();
	private JButton click = new JButton("Download");
	private JTextArea text = new JTextArea(23,56);
	private String[] symbols = new String[30];
	private JButton rate = new JButton("Rate");
	private ArrayList<Integer> ratingArray = new ArrayList<Integer>(30);
	private Font font = new Font("Courier", Font.BOLD,23);
	private JScrollPane scroll = new JScrollPane(text);
	private JButton exit = new JButton("Exit");
	
	public Main(){
		//makes the GUI Window
		super("Stock Downloader");
		setSize(800,800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//creates JPanel that everything is on top of
		p.setLayout(new BorderLayout(0,0));
		setContentPane(p);
		
		//adds all the buttons to the top of the window
		p.add(top, BorderLayout.NORTH);
		click.setEnabled(true);
		click.addActionListener(this);
		rate.setEnabled(true);
		rate.addActionListener(this);
		exit.setEnabled(true);
		exit.addActionListener(this);
		top.add(click);
		top.add(rate);
		top.add(exit);
		
		//adds the text area to the bottom of the window
		p.add(bottom);
		text.setEnabled(false);
		text.setFont(font);
		bottom.add(scroll);
		setVisible(true);
		
		//reads the csv file with all the stock symbols on it
		try {
			read = new CSVReader(new FileReader("data/dow30.csv"));
			try {
				for (int i = 0; i < 30; i++) 
				{
					//takes off all the brackets around the symbols and stores them in an array
					String[] temp;
					temp = read.readNext();
					String temp1 = "";
					String temp2 = Arrays.toString(temp);
					temp1 = temp2.substring(1, temp2.length()-1);
					symbols[i] = (temp1);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(click == e.getSource())
		{
			//downloads the stock info if clicked
			for(int i = 0 ; i < 30 ; i++)
			{
				text.append("Downloading " + symbols[i] + "...done!\n");
				d1 = new YahooHistoryDataDownloader(symbols[i]);
				d1.download();
			}
		}
		
		if(exit == e.getSource())
		{
			//exits the window if clicked
			System.exit(0);
		}
		
		if(rate == e.getSource())
		{
			//rates the stocks when clicked
			for(int j = 0 ; j < 30 ; j++) //reads info for all 30 stocks
			{
				try {
					ArrayList<HistoryData> list = new ArrayList<>();
					read = new CSVReader(new FileReader("data/" + symbols[j] + ".csv"));
					String[] temp;
					//skips the first row which doesn't have data
					temp = read.readNext();
					boolean out = false;
					while(!out) //continues to loop until all info has been read for each file
					{
						HistoryData h1 = new HistoryData("",0,0,0,0,0,0);
						temp = read.readNext();
						String temp1 = Arrays.toString(temp);
						if(temp1 == "null") //if there is nothing left to read break out of while loop
							out = true;
						else
						{
							temp1 = temp1.substring(1, temp1.length()-1);
							temp1 += ",";
							loop:
							for(int i = 0 ; i < 7 ; i++) //reads all 6 items from each line
							{
								String temp2 = temp1.substring(0, temp1.indexOf(','));
								if(i == 6) //reads Adj_close
								{
									float x = Float.parseFloat(temp2);
									h1.setAdj_close(x);
									break loop;
								}
								temp1 = temp1.substring(temp2.length()+2,temp1.length());
								if(i == 0) //reads Date
								{
									h1.setDate(temp2);
								}
								if(i == 1)//reads Open
								{
									float x = Float.parseFloat(temp2);
									h1.setOpen(x);
								}
								else if(i == 2) //reads High
								{
									float x = Float.parseFloat(temp2);
									h1.setHigh(x);
								}
								else if(i == 3) //reads Low
								{
									float x = Float.parseFloat(temp2);
									h1.setLow(x);
								}
								else if(i == 4) //reads Close
								{
									float x = Float.parseFloat(temp2);
									h1.setClose(x);
								}
								else if(i == 5) //reads Volume
								{
									long x = Long.parseLong(temp2);
									h1.setVolume(x);
								}
							}
							list.add(h1); //adds the 6 items to a list each time
						}
					}
					//once the whole file has been read it makes a rating for that stock
					Prev5Rating r1 = new Prev5Rating(list);
					r1.compute(); //computes the rating
					int x = r1.getRating();
					String string = "" + x;
					text.append(symbols[j] + ":\t" + string + "\n");
					ratingArray.add(x);	
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//after all the ratings have been made they are sorted
			SortRating s1 = new SortRating(ratingArray, symbols);
			s1.sort();
			text.append(s1.toString());
		}
	}
	
	
	public static void main(String[] args)
	{
		Main m1 = new Main();	
	}
}
