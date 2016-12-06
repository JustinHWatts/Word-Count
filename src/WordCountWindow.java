import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * WordCountWindow.java - Sets up the GUI of the WordCount program,
 * creating the main window and setting listeners for the buttons
 * to allow a user to read in up to ten files at a time, clear the
 * existing file names from the text input fields, or exit the program.
 * Each file being read is assigned to a thread, thus allowing for
 * multithreaded processing.
 *
 * @author Justin Watts
 * @version 11/5/15
 */
public class WordCountWindow
{
	private JFrame main;
	private JPanel instructionPanel, inputPanel, buttonPanel;
	private JLabel label;
	private JTextField[] textFields;
	private JButton inputFiles, clear, exit;
	
	/**
	 * Constructor for the WordCountWindow class. Sets up the elements of
	 * the WordCount GUI.
	 */
	public WordCountWindow()
	{
		this.main = new JFrame("Word Count");
		
		this.instructionPanel = new JPanel();
		this.inputPanel = new JPanel();
		this.buttonPanel = new JPanel();
		
		this.label = new JLabel("Input Filenames Here");

		this.textFields = new JTextField[10];
		for (int i = 0; i <= 9; i++)
			textFields[i] = new JTextField(20);
		
		this.inputFiles = new JButton("Run");
		this.clear = new JButton("Clear");
		this.exit = new JButton("Exit");
	}
	
	/**
	 * Begins the WordCount program by displaying the main window
	 * of the program and adding action listeners to various buttons
	 * in order to read in files, clear the file names, and exit
	 * the program.
	 */
	public void start()
	{
		main.setLayout(new BorderLayout());
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLocation(925, 300);
		
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
		buttonPanel.setLayout(new FlowLayout());
		
		inputFiles.addActionListener(new ActionListener()
		{
			/**
			 * Sets action listener for run button to take
			 * all input file names and count the number of
			 * words in each file.
			 */
			public void actionPerformed(ActionEvent event)
			{
				for (int i = 0; i <= 9; i++)
				{
					if (textFields[i].getText().length() > 0)
					{
						Runnable run = new WordCount(textFields[i].getText());
						Thread thread = new Thread(run);
						thread.start();
					}
				}
			}
		});
		
		clear.addActionListener(new ActionListener()
		{
			/**
			 * Sets action listener for clear button to clear all
			 * input text fields.
			 */
			public void actionPerformed(ActionEvent event)
			{
				for (int i = 0; i <= 9; i++)
					textFields[i].setText("");
			}
		});
		
		exit.addActionListener(new ActionListener()
		{
			/**
			 * Sets action listener for exit button to shut down the
			 * entire program.
			 */
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});
		
		main.add(instructionPanel, BorderLayout.NORTH);
		main.add(inputPanel, BorderLayout.CENTER);
		main.add(buttonPanel, BorderLayout.SOUTH);
		
		instructionPanel.add(label);
		
		for (int i = 0; i <= 9; i++)
		{
			inputPanel.add(textFields[i]);
			if (i < 9)
				inputPanel.add(Box.createVerticalStrut(5));
		}
		
		buttonPanel.add(inputFiles);
		buttonPanel.add(clear);
		buttonPanel.add(exit);
		
		main.setResizable(false);
		main.pack();
		main.setVisible(true);
	}
}