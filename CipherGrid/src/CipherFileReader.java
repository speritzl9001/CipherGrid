import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CipherFileReader {
	
	private String line; 
	private String messageFile;
	// ArrayList to store the integers in the file.
	private ArrayList<Integer> readFile = new ArrayList<Integer>(0);
	
	// No arg constructor.
	public CipherFileReader() {	}
	
	public ArrayList<Integer> readEncodedMsgFile (String fileName) throws IOException {
		
		this.line = ""; 
		this.messageFile = fileName; // Copy the file name argument.
		
		// Create a new scanner object and pass it the copied file name.
		Scanner scanner = new Scanner(new File(this.messageFile));
		
		// Use a comma delimiter with the scanner to get each integer (as a string)
		scanner.useDelimiter(",");
		
		// Loop through each scanner token.
		while (scanner.hasNextInt())  //while (scanner.hasNext())
		{
			this.readFile.add(scanner.nextInt()); // Pass the stored integer to the readFile ArrayList.
		}

		scanner.close(); // Close the scanner object.
		return readFile; // Return the readFile ArrayList filled with the integers from the file.
	}
}
