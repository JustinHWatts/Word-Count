/**
 * WordCountDrive.java - Driver to test the Word Count program.
 * The program reads in a text file and counts the number of words
 * within the text file, outputting both the original file's contents
 * and the number of words.
 *
 * @author Justin Watts
 * @version 11/5/15
 */
public class WordCountDrive
{
	/**
	 * Main method of WordCount program. Begins the program.
	 */
	public static void main(String[] args)
	{
		WordCountWindow window = new WordCountWindow();
		window.start();
	}
}