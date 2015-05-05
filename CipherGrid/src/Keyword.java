import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class Keyword {

	// Constructor
	public Keyword() {
		
	}
	
	// Map for removing all duplicate letters in the keyword.
	private Map<Integer, Integer> keywordMap = new LinkedHashMap<Integer, Integer>();
	
	private String keyword;
	
	public ArrayList<Integer> keywordToIntArr(String keyword) {
		
		this.keyword = keyword;
		
		// Loop through the keyword string and put each letter (typecast as an integer) into the map.
		// Duplicate 'letters' will be overwritten. So the map will contain no duplicates.
		for (int i=0; i<this.keyword.length(); i++) 
		{
			this.keywordMap.put((int)this.keyword.charAt(i), i);
		}

		// Put the maps' key set (which holds the 'letters') into an ArrayList.
		// This will make it easier to put the 'letters' into the Table later.
		ArrayList<Integer> keywordAsIntArray = new ArrayList<Integer>(this.keywordMap.keySet());
		
		boolean keywordToIntArrDebug = false;
		if (keywordToIntArrDebug) {System.out.println("\n" + "map.keySet() from keyslist ArrayList = " + keywordAsIntArray.toString());}
		
		return keywordAsIntArray;
	}// END keywordToIntArr()
	
}// END Keyword Class