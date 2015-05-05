import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class Process {
	
	private String keywordAsString = "";
	private String keyword = "";
		
	// ArrayList to hold the letters of the keyword with duplicates removed.
	private ArrayList<Integer> keywordAsIntsNoDup = new ArrayList<Integer>(0);
	
	// Map for removing all duplicate letters in the keyword.
	private Map<Integer, Integer> keywordLetters = new LinkedHashMap<Integer, Integer>(0);
	
	// ArrayList to hold all 256 ASCII characters (as integers).
	private ArrayList<Integer> asciiArray = new ArrayList<Integer>(0);
	
	// ArrayList for storing the message from the file.
	ArrayList<Integer> fileMessageAsInteger = new ArrayList<Integer>(0);
	
	// Constructor
	public void process() {
		
	}
	
	public void processKeyword(String keyword) {
		
		// Copy incoming keyword String
		this.keywordAsString = keyword;
				
		// Pass incoming keyword String to the removeDuplicate method.
		// removeDuplicate will first convert the letters to Integers,
		// then remove any duplicate letters.
		// Store the result in the keywordAsIntsNoDup ArrayList
		this.keywordAsIntsNoDup = removeDuplicates(this.keywordAsString);
		
		// Create ArrayList and fill it with all 256 ASCII characters (as integers).
		createAsciiArr();
		
		// Remove the keyword letters from the asciiArray. 
		for (int i=0; i<this.keywordAsIntsNoDup.size(); i++) {
			Integer letterToSearchFor = this.keywordAsIntsNoDup.get(i);
			if (this.asciiArray.contains(letterToSearchFor))
			{
				this.asciiArray.remove(letterToSearchFor);
			}
		}
	}// END processKeyword()
	
	
	public ArrayList<Integer> removeDuplicates(String keyword) {
		
		// Copy incoming keyword String
		this.keyword = keyword;

		// Loop through the keywordAsIntArray ArrayList, putting each 'letter' of the keyword into the map.
		// Duplicate letters will be overridden, so the map will contain the keyword without any duplicates. 
		for (int i=0; i<this.keyword.length(); i++) //for (int i=0; i<this.keywordAsIntArray.size(); i++)
		{
			this.keywordLetters.put((int)this.keyword.charAt(i), i); //this.keywordLetters.put(this.keywordAsIntArray.get(i), i);
		}
		
		// Put the maps' key set (which holds the 'letters') into an ArrayList.
		// This will make it easier to put the 'letters' into the Table later.
		ArrayList<Integer> keyslist = new ArrayList<Integer>(this.keywordLetters.keySet());
		System.out.println("\n" + "map.keySet() from keyslist ArrayList = " + keyslist.toString());
		
		return keyslist;
	}
	
	public void createAsciiArr() {

		// Use an enhanced for loop to fill the asciiArray ArrayList
		// with all 256 ASCII characters as integers.
		for (int i=0; i<256; i++) {
			this.asciiArray.add(i);
		}
	}// END createAsciiArr()
}// END class
