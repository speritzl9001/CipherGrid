import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import com.google.common.base.Splitter;

public class Message {
	
	public Message() {	}
	
	private ArrayList<Integer> fileMsgIntArray = new ArrayList<Integer>(0);
	private ArrayList<Integer> fileMessageAsInts = new ArrayList<Integer>(0);
	private String line;
	private String messageFilePath;
	private String messageQuick;
	
	// Reads in the message file and converts the letters to Integers
	// with the helper method messageToIntArray.
	public ArrayList<Integer> readMessageFile(String fileName)
	{
		try {
			this.line = "";
			this.messageFilePath = fileName;
			Scanner scanner = new Scanner(new File(this.messageFilePath));			
			scanner.useDelimiter("\\z");
			
			while (scanner.hasNext())
			{
				this.line += scanner.next();
			}
			scanner.close();
			
			// Pass the string to the messageToIntArray method.
			// messageToIntArray will convert the characters to integers.
			// We then store the return value in the fileMessageAsInts ArrayList.
			this.fileMessageAsInts = messageToIntArray(this.line);
			
			// Loop through the fileMessageAsInts ArrayList.
			for (int i=0; i<fileMessageAsInts.size()-1; i++)
			{
				// If there are any two elements next to each other that are the same...
				if (fileMessageAsInts.get(i) == fileMessageAsInts.get(i+1))
				{
					// Add ASCII integer 216, 'Ø' Latin capital letter O with slash. http://www.ascii-code.com/
					fileMessageAsInts.add(i+1, 216);
				}
			}
			
			// If the length of the array is not even...
			if ( (fileMessageAsInts.size() % 2) != 0 )
			{
				// Add the character Ý at the end.
				// Ý = ASCII integer 221, Latin capital letter Y with acute. http://www.ascii-code.com/
				fileMessageAsInts.add(211);
			}
			boolean MessageDebug = false;
			if (MessageDebug) System.out.println("\n" + "fileMessageAsInts = " + fileMessageAsInts.toString());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
			
		// Return the file as a ArrayList of integers.
		return this.fileMessageAsInts;
	}
	
	private ArrayList<Integer> messageToIntArray(String string)
	{
		for (int i=0; i<string.length();i++)
		{
			fileMsgIntArray.add((int)string.charAt(i));
		}
		return fileMsgIntArray;
	}
	
	public ArrayList<Integer> quickmessage(String message)
	{
		this.messageQuick = message;
		if ( (this.messageQuick.length() % 2) != 0 )
		{
			this.messageQuick = this.messageQuick.concat("Ý");
		}
		
		// Fill the ArrayList with the message from the JTextArea
		// converting the letters to integers as we fill.
		ArrayList<Integer> quickMessageToIntArr = new ArrayList<Integer>(0);
		for (int i=0; i<this.messageQuick.length(); i++)
		{
			quickMessageToIntArr.add((int)this.messageQuick.charAt(i));
		}
		return quickMessageToIntArr;
	}
	
	public ArrayList<Integer> quickMessageDecode(String message)
	{
		String quickmessage = message;
		// http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/base/Splitter.html#on%28char%29
		// Returns a splitter that uses the given single-character separator.
		// For example, Splitter.on(',').split("foo,,bar") returns an iterable containing ["foo", "", "bar"].
		Iterable<String> iter = Splitter.on(',').split(quickmessage);  
		
		ArrayList<Integer> quickMessageToIntArr = new ArrayList<Integer>(0);
		Splitter splitter = Splitter.on(',');
		Iterable<String> numbers = splitter.split(quickmessage);
		for(String number : numbers)
		{
			int num = Integer.parseInt(number);
			quickMessageToIntArr.add(num);
			//System.out.println(num);
		}
		return quickMessageToIntArr;
	}
}
