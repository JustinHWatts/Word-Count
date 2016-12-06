import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * WordCount.java - Handles the display of a file's original contents
 * and also counts the number of words within a file. These tasks are
 * run through a single thread.
 *
 * @author Justin Watts
 * @version 11/5/15
 */
public class WordCount implements Runnable
{
	private File filename;
	private JFrame display;
	private JPanel filePanel, messagePanel, buttonPanel;
	private JButton close;
	private JTextArea fileResult;
	private JScrollPane scrollBar;
	private JLabel message;
	
	/**
	 * Constructor for the WordCount class. Sets up the elements of
	 * the resulting WordCount display window determined by a read-
	 * in file.
	 * 
	 * @param _filename the name of a file as a String
	 */
	public WordCount(String _filename)
	{
		this.filename = new File(_filename);
		
		this.display = new JFrame(_filename);
		this.filePanel = new JPanel();
		this.messagePanel = new JPanel();
		this.buttonPanel = new JPanel();
		
		this.close = new JButton("Ok");
		this.fileResult = new JTextArea(15, 30);
		this.scrollBar = new JScrollPane(fileResult);
		this.message = new JLabel();
	}
	
	/**
	 * Begins the process of displaying a file's contents and
	 * counting the number of words within it. This is taken care
	 * of by a single thread.
	 */
	public void run()
	{
		try
		{
			Scanner inputFile = new Scanner(filename);
			Scanner inputFileCopy = new Scanner(filename);
			
			this.createCountFrame(inputFile, inputFileCopy);
		}
		catch (FileNotFoundException e)
		{
			this.createErrorFrame();
		}
	}
	
	/**
	 * Displays a window with the contents of a file and the
	 * number of words within it.
	 * 
	 * @param inputFile the file being read
	 * @param inputFileCopy copy of the file being read
	 */
	private void createCountFrame(Scanner inputFile, Scanner inputFileCopy)
	{
		display.setLayout(new BorderLayout());
		display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		display.setLocation(1200, 325);
		
		filePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		messagePanel.setLayout(new FlowLayout());
		
		fileResult.setText(this.displayFileContents(inputFile));
		fileResult.setEditable(false);
        
		message.setText("Count: " + this.countWords(inputFileCopy));
		
		filePanel.add(scrollBar);
		messagePanel.add(message);
		
		display.add(filePanel, BorderLayout.CENTER);
		display.add(messagePanel, BorderLayout.SOUTH);
		display.setResizable(false);
		display.pack();
		display.setVisible(true);
	}
	
	/**
	 * Displays a window which notifies the user a file was
	 * not found by the name entered.
	 */
	private void createErrorFrame()
	{
		display.setLayout(new BorderLayout());
		display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		display.setLocation(1200, 325);
		
		messagePanel.setLayout(new FlowLayout());
		messagePanel.setBorder(BorderFactory.createEmptyBorder(15, 18, 10, 18));
		buttonPanel.setLayout(new FlowLayout());
		
		close.addActionListener(new ActionListener()
		{
			/**
			 * Closes the window.
			 */
			public void actionPerformed(ActionEvent event)
			{
				display.dispose();
			}
		});
        
		message.setText(filename + " is not a valid filename");
		messagePanel.add(message);
		buttonPanel.add(close);
		
		display.add(messagePanel, BorderLayout.CENTER);
		display.add(buttonPanel, BorderLayout.SOUTH);
		display.setResizable(false);
		display.pack();
		display.setVisible(true);
	}
	
	/**
	 * Returns the contents of a file.
	 * 
	 * @param inputFile file being read
	 * @return String resulting contents of file
	 */
	private String displayFileContents(Scanner inputFile)
	{
		String result = "";
		
		while (inputFile.hasNextLine())
			result = result + inputFile.nextLine() + "\n";
		
		return result;
	}
	
	/**
	 * Counts the words in a file.
	 * 
	 * @param inputFile file being read
	 * @return int resulting number of words in file
	 */
	private int countWords(Scanner inputFile)
	{
		int result = 0;
		
		while (inputFile.hasNext())
		{
			inputFile.next();
			result++;
		}
		
		return result;
	}
}