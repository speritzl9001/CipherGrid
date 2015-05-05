import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CipherFileWriter {
	
	private String csvFile;
	private FileWriter fw;
	
	// No arg Constructor
	public CipherFileWriter() {	}

	public void writeEncodedMsgToFile (ArrayList<Integer> encodedMsg, String fileName) throws IOException {
		
		this.csvFile = fileName;
		
		// Create a FileWriter object, pass it the new file name.
		this.fw = new FileWriter(this.csvFile);
		//this.fw = new FileWriter(this.csvFile, true); // true makes it append to the end of the file
		
		// Loop through each index in the encodedMsg ArrayList.
		// Get each index as a String then write it to the file
		// with a comma separating each item.
		for (Integer i: encodedMsg) {
			this.fw.append(i.toString());
			//this.fw.write(i.toString());
			this.fw.append(',');
		}
		this.fw.flush();
		this.fw.close();
	}

	public void writeDecodedMsgToFile (String decodedMsg, String fileName) throws IOException {
		
		String output = decodedMsg;	 // Copy the incoming decoded message String.
		String fileToWriteIn = fileName; // Copy the incoming file name String.
		
		// Add "_decoded.txt" to the end of the file name.
		fileToWriteIn = fileToWriteIn.substring(0, fileToWriteIn.lastIndexOf("_encoded.txt"));
		//System.out.println("fileToWriteIn = " + fileToWriteIn);
		fileToWriteIn = fileToWriteIn.concat("_decoded.txt");
		//System.out.println("fileToWriteIn = " + fileToWriteIn);
		
		// Remove any padding characters that might have been added during the encoding process.
		// ( ASCII integer 210	Ò  Latin capital letter O with grave )  http://www.ascii-code.com/
		output = output.replaceAll("Ø", "");
		//System.out.println("output = " + output);
				
		// Create a FileWriter object and pass it the file to write to.
		FileWriter fw = new FileWriter(fileToWriteIn); 
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(output);
		bw.close();
	}
}// END class
