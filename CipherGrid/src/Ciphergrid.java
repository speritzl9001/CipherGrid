import java.util.ArrayList;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Ciphergrid {

	// Constructor
	public Ciphergrid() {	}

	// Begin class fields ***********************************************************************
	private String keywordAsString;
	private String decodedMessage;

	// ArrayList to store the keyword as an Integer Array, as 
	// returned by the Keyword Class method keywordToIntArr().
	private ArrayList<Integer> keywordAsIntArr = new ArrayList<Integer>(0);

	// ArrayList to hold the incoming message to be encoded.
	private ArrayList<Integer> messageToEncode = new ArrayList<Integer>(0);

	// ArrayList to hold the incoming message to be put into the table by the fill method.
	private ArrayList<Integer> message = new ArrayList<Integer>(0);

	// Encoded message to be returned as an ArrayList from the encode method.
	private ArrayList<Integer> encodedMessage = new ArrayList<Integer>(0);

	// ArrayList for holding the new 'letter' pair.
	private ArrayList<Integer> newLetter = new ArrayList<Integer>(0);

	// ArrayList to hold the incoming message to be decoded.
	private ArrayList<Integer> messageToDecode = new ArrayList<Integer>(0);

	// ArrayList to hold the incoming keyword to be put into the table "Ciphergid"
	private ArrayList<Integer> inputArr = new ArrayList<Integer>(0);

	// Create an ArrayList to hold all 256 ASCII characters (as integers).
	private ArrayList<Integer> asciiArray = new ArrayList<Integer>(0);

	// ArrayList to hold the row and column of the last element that's used the table.
	// This will be used the next time the fill method is called to find the index to start filling.
	private ArrayList<Integer> lastPosition = new ArrayList<Integer>(2);

	// A table from the Google Guava library, stores <Row, Column, Value>  the "Ciphergid"
	private Table<Integer,Integer,Integer> table = HashBasedTable.create(1, 1);
	private int tblWidth = 0; 

	private Integer r = 0; // Row of the last element that gets used in the table.
	private Integer c = 0; // Column of the last element that gets used in the table.
	// END class fields ***********************************************************************

	// Fill the grid with what ever is pass in.
	public void fill(ArrayList<Integer> keyword, ArrayList<Integer> message, int tableWidth) {
		//public void fill(ArrayList<Integer> inputArr, int tableWidth) {

		this.inputArr = keyword;
		this.message = message;
		this.tblWidth = tableWidth;

		// Loop through the inputArr ArrayList and fill the table with it's contents.
		for (int i = 0; i < this.inputArr.size(); i++ )
		{
			table.put(this.r, this.c, inputArr.get(i)); // Add the new element to the table.

			this.c++;
			if (this.c > tableWidth - 1 ) { 
				this.c = 0; // reset the column number to zero because we've reached the last column in the row.
				this.r++;
			}
		}

		// Loop through the table's cell set and get the row and column of the last element.
		for (Table.Cell<Integer, Integer, Integer> cell : table.cellSet())
		{
			this.r = cell.getRowKey(); 	  // get the row of the last element that gets used in the table.
			this.c = cell.getColumnKey() + 1; // get the column of the last element that gets used in the table.
			boolean fillDebug1 = false;
			if (fillDebug1) {
				System.out.println("\n" + "fillDebug1: table filled with keyword:");
				System.out.println(cell.getRowKey() + "|" + cell.getColumnKey()+ "|" + cell.getValue());}
		}
		this.lastPosition.add(r); // Add the row number of the last element in the table to the ArrayList.
		this.lastPosition.add(c); // Add the column number of the last element in the table to the ArrayList.

		boolean fillDebug2 = false;
		if (fillDebug2) {
			System.out.println("\n" + "row to begin with when filling next time: " + r.toString());
			System.out.println("column to begin with when filling next time: " + c.toString());
		}

		// Loop through the message ArrayList and fill the table with it's contents.
		for (int i = 0; i < this.message.size(); i++ )
		{
			table.put(this.r, this.c, message.get(i)); // Add the new element to the table.

			this.c++; // Increment column number.
			if (this.c > tableWidth) { 
				this.c = 0; // reset the column number to zero because we've reached the last column in the row.
				this.r++;   // Increment row number.
			}
		}
		boolean fillDebug3 = false;
		if (fillDebug3) { System.out.println("Debug fill method:" + "Done with fill"); }
	}// END fill method


	// Encode the message
	public ArrayList<Integer> encode(String keyword, ArrayList<Integer> message) {

		this.keywordAsString = keyword; 	
		Keyword keywordObj = new Keyword(); 
		// Convert the keyword String to a Integer ArrayList with no duplicates.
		this.keywordAsIntArr = keywordObj.keywordToIntArr(this.keywordAsString);

		// Fill the ArrayList with all 256 ASCII characters as integers.
		for (int i=0; i<256; i++) {
			this.asciiArray.add(i);
		}
		// Remove the keyword letters from the asciiArray. 
		for (int i=0; i<keywordAsIntArr.size(); i++) {
			Integer letterToSearchFor = keywordAsIntArr.get(i);
			if (asciiArray.contains(letterToSearchFor))
			{
				asciiArray.remove(letterToSearchFor);
			}
		}
		// Fill the table "ciphergrid" with the keyword and asciiArray.
		final int tableSize = 15;
		fill(this.keywordAsIntArr, this.asciiArray, tableSize);

		this.messageToEncode = message;	// Copy incoming message ArrayList.

		// Loop through messageToEecode, get a pair of 'letters' 
		// and add them to the letterPair integer array.
		int[] letterPair = new int[2];	// Array to hold two 'letters' of the incoming message.
		for (int i=0; i<this.messageToEncode.size() - 1; i+=2) {
			letterPair[0] = this.messageToEncode.get(i);
			letterPair[1] = this.messageToEncode.get(i+1);
			boolean encodeDebug1 = false;
			if (encodeDebug1) {
				System.out.println("");
				System.out.println("letterPair[" + i + "] = " + letterPair[0]);
				System.out.println("letterPair[" + (i+1) + "] = " + letterPair[1]);
			}

			// Pass the two 'letters' to the getShape method and store the result in letterInfo integer array.
			// Index 0-2: first letters'  row,column,value
			// Index 3-5: second letters' row,column,value
			// Index 6: shape
			// Shape refers to one of three letter configurations described 
			// in the WIKI page about the cipher: rectangle = 1, same row = 2, same column = 3.
			int[] letterInfo = new int[6]; // Array for storing detailed information about two letters.
			letterInfo = getShape(letterPair);
			boolean encodeDebug2 = false;
			if (encodeDebug2) { System.out.println("shape = " + letterInfo[6]); }

			// Pass the two 'letters' held in the letterInfo integer array, to the getEncodedLetter method. 
			// This will return each 'letters' new encoded value.
			this.encodedMessage.clear();
			this.encodedMessage.addAll(getEncodedLetter(letterInfo));
			boolean encodeDebug3 = false;
			if (encodeDebug3) {
				System.out.println("" + "encoded pair:");
				System.out.println(this.encodedMessage);
			}
		}
		return this.encodedMessage;   
	}

	// This method returns the "Shape" that two 'letter' are in. 
	// Shape refers to one of three letter configurations described 
	// in the WIKI page about the cipher: rectangle = 1, same row = 2, same column = 3.
	private int[] getShape(int[] letters) {

		// Array for storing each 'letters': row,column,value and one index for the shape.
		int[] rowsColumns = new int[7];
		int shape = 0; // How 'letters' in the pair are oriented to each other.

		int count = 0;
		// Loop through the 'letters' array.
		for (int i=0; i<letters.length; i++) {			
			// Loop through the cells in the table.
			for (Table.Cell<Integer, Integer, Integer> cell : table.cellSet())
			{
				if (cell.getValue() == letters[i]) // If we found a matching element in the table...
				{ 
					rowsColumns[count] = cell.getRowKey();		// store the matching cells' row number in the array.
					rowsColumns[count + 1] = cell.getColumnKey();	// store the matching cells' column number in the array.
					rowsColumns[count + 2] = cell.getValue();	// store the matching cells' value in the array.
				}
			}
			count+=3; // Increment three indexes at a time to get to the next 'letters' value.
		}

		// Find which "Shape" the pair of 'letters' is in and
		// store the result in the rowsColumns integer array.
		if (rowsColumns[0] == rowsColumns[3]) { // If their in the same row.
			shape = 2; 
			rowsColumns[6] = shape; // Add the shape to the array.
		}
		else if (rowsColumns[1] == rowsColumns[4]) { // If their in the same column.
			shape = 3; 
			rowsColumns[6] = shape; // Add the shape to the array.
		}
		// If there not in the same row or column they must be in a rectangle shape.
		else if ( (rowsColumns[0] != rowsColumns[3]) && (rowsColumns[1] != rowsColumns[4]) )
		{
			shape = 1;
			rowsColumns[6] = shape;
		}

		return rowsColumns; 
	}// END getShape

	// Get the new encoded version of the 'letter' pair.
	private ArrayList<Integer> getEncodedLetter(int[] letterArr) { 
		// Copy letterArr indexes into variables
		int firstLetterRow  = letterArr[0];
		int firstLetterCol  = letterArr[1];
		int secondLetterRow = letterArr[3]; 
		int secondLetterCol = letterArr[4];
		int newLetterCol = 0; // the newly encoded letters column

		// Shape refers to one of three letter configurations described 
		// in the WIKI page about the cipher: rectangle = 1, same row = 2, same column = 3.
		int shape = letterArr[6];

		if (shape == 2) // If both 'letters' are in the same row.
		{
			if ( firstLetterCol > 14) // If the first 'letter' is in the last column of the table.
			{
				firstLetterCol = 0; 	       // Set the new first 'letters' column number to zero. 
				letterArr[1] = firstLetterCol; // Set the array to reflect the change. 
			}
			else { // The first 'letter' isn't in the last column of the table.
				newLetterCol++;	   // Increase the column number by one.
				letterArr[1] += 1; // Set the array to reflect the change.   
			}
			if ( secondLetterCol > 14) // If the second 'letter' is in the last column of the table.
			{
				secondLetterCol = 0;		// Set the new the second 'letters' column number to zero.
				letterArr[4] = secondLetterCol; // Set the array to reflect the change. 
			}
			else { // The second 'letter' isn't in the last column of the table.
				newLetterCol++;	    // Increase the column number by one.
				letterArr[4] += 1 ; // Set the array to reflect the change. 
			}

		}
		else if (shape == 3)  // Both 'letters' are in the Same column. 
		{
			if ( firstLetterRow > 14) // If the first 'letter' is in the last row in the table.
			{
				firstLetterRow = 0; 		// Set the new column number to row number zero.
				letterArr[0] = firstLetterRow;  // Set the array to reflect the change.
			}
			else // The first 'letter' isn't in the last row in the table.
			{
				firstLetterRow ++; 		// New encoded 'letter' will be the one below the current one.
				letterArr[0] = firstLetterRow;  // Set the array to reflect the change.
			}
			if (secondLetterRow > 14) // If the second 'letter' is in the last row in the table.
			{
				secondLetterRow = 0; 		// Set the new column number to row number zero.
				letterArr[3] = secondLetterRow; // Set the array to reflect the change.
			}
			else
			{
				secondLetterRow ++; 		 // new encoded 'letter' will be the one below the current one.
				letterArr[3] = secondLetterRow;  // Set the array to reflect the change.
			}
		}
		else if (shape == 1) // Rectangle shape. 
		{
			// Temporary variables to store the column numbers of the two 'letters'
			int tempLetter1Col = letterArr[1];
			int tempLetter2Col = letterArr[4];

			// Swap the column values.
			letterArr[1] = tempLetter2Col;
			letterArr[4] = tempLetter1Col;
		}

		// Lookup the values in the table using their new row and column. 
		// table.get: Returns the value corresponding to the given row and column keys, or null if no such mapping exists.
		this.newLetter.add( table.get(letterArr[0], letterArr[1] ));
		this.newLetter.add( table.get(letterArr[3], letterArr[4] ));

		return this.newLetter;
	}//END getEncodedLetter method

	//  public ArrayList<Integer> encode(String keyword, ArrayList<Integer> message) {
	public String decode(String keyword, ArrayList<Integer> messageToDecode) {

		this.keywordAsString = keyword; 	
		Keyword keywordObj = new Keyword(); 
		// Convert the keyword String to a Integer ArrayList with no duplicates.
		this.keywordAsIntArr = keywordObj.keywordToIntArr(this.keywordAsString);

		this.asciiArray.clear();
		// Fill the asciiArray ArrayList with all 256 ASCII characters as integers.
		for (int i=0; i<256; i++) {
			this.asciiArray.add(i);
		}
		// Remove the keyword letters from the asciiArray. 
		for (int i=0; i<keywordAsIntArr.size(); i++) {
			Integer letterToSearchFor = keywordAsIntArr.get(i);
			if (asciiArray.contains(letterToSearchFor))
			{
				asciiArray.remove(letterToSearchFor);
			}
		}
		// Fill the table "ciphergrid" with the keyword and asciiArray.
		final int tableSize = 15;
		fill(this.keywordAsIntArr, this.asciiArray, tableSize);

		this.messageToDecode = messageToDecode; // Copy the incoming message ArrayList.
		int[] letterPair = new int[2];		// Array to hold two 'letters' of the incoming message.
		this.decodedMessage = ""; 		// String for holding the decoded message.

		// Loop through messageToDecode, get a pair of 'letters' 
		// and add them to the letterPair integer array.
		for(int i=0; i<this.messageToDecode.size() - 1; i+=2) {
			letterPair[0] = this.messageToDecode.get(i);
			letterPair[1] = this.messageToDecode.get(i+1);
			boolean decodeDebug1 = false;
			if (decodeDebug1) {
				System.out.println("");
				System.out.println("letterPair[" + i + "] = " + letterPair[0]);
				System.out.println("letterPair[" + (i+1) + "] = " + letterPair[1]);
			}

			// Pass the two 'letters' to the getShape method and store the result in letterInfo integer array.
			// Index 0-2: first letters'  row,column,value
			// Index 3-5: second letters' row,column,value
			// Index 6: shape
			// Shape refers to one of three letter configurations described 
			// in the WIKI page about the cipher: rectangle = 1, same row = 2, same column = 3.
			int[] letterInfo = new int[6]; // Array for storing information about two letters.
			letterInfo = getShape(letterPair); 
			boolean decodeDebug2 = false;
			if (decodeDebug1) { System.out.println("shape = " + letterInfo[6]); }

			// Pass the two 'letters' held in the letterInfo integer array, to the getDecodedLetter method. 
			// This will return each 'letters' decoded value.
			this.decodedMessage = this.decodedMessage.concat(getDecodedLetter(letterInfo));
			boolean decodeDebug3 = false;
			if (decodeDebug3) { 
				System.out.println("" + "decoded pair:");
				System.out.println(this.decodedMessage);
			}
		}
		return this.decodedMessage;
	}


	public String getDecodedLetter(int[] letterArr) {
		// Copy letterArr indexes into variables
		int firstLetterRow  = letterArr[0];
		int firstLetterCol  = letterArr[1];
		int secondLetterRow = letterArr[3]; 
		int secondLetterCol = letterArr[4];
		int newLetterCol = 0; // the newly encoded letters column

		// Shape refers to one of three letter configurations described 
		// in the WIKI page about the cipher: rectangle = 1, row = 2, column = 3.
		int shape = letterArr[6];

		if (shape == 2) // If both 'letters' are in the same row.
		{
			// Test the first 'letter'
			if ( firstLetterCol < 1)   // If the first 'letter' is in the first column of the table.
			{
				firstLetterCol = 15; 	       // Set the first 'letters' new column number to be the last column in the table.
				letterArr[1] = firstLetterCol; // Set the array to reflect the change. 
			}
			else { // The first 'letter' isn't in the first column of the table.
				newLetterCol--;	   // Decrease the column number by one.
				letterArr[1] -= 1; // Set the array to reflect the change.   
			}

			// Test the second 'letter'
			if ( secondLetterCol < 1) // If the second 'letter' is in the first column of the table (column 0)
			{
				secondLetterCol = 15; // Set the second 'letters' new column number to be the last column in the table.  
				letterArr[4] = secondLetterCol; // Set the array to reflect the change. 
			}
			else { // The second 'letter' isn't in the first column of the table.
				newLetterCol--;	   // Increase the column number by one.
				letterArr[4] -= 1; // Set the array to reflect the change.   
			}
		}
		else if (shape == 3)  // If both 'letters' are in the same column. 
		{
			if ( firstLetterRow < 1)	// If the first 'letter' is in the first row in the table (row 0)
			{
				firstLetterRow = 15; 		// Set the new row number to the last row in the table (row 15)
				letterArr[0] = firstLetterRow;  // Set the array to reflect the change.

			}
			else // The first 'letter' isn't in the first row of the table.
			{
				firstLetterRow --; 		// New encoded 'letter' will be the one above the current one.
				letterArr[0] = firstLetterRow;  // Set the array to reflect the change.

			}
			if (secondLetterRow < 1) // If the second 'letter' is in the first row in the table.
			{
				secondLetterRow = 15; 		// Set the new row number to the last row in the table (row 15)
				letterArr[3] = secondLetterRow; // Set the array to reflect the change.
			}

			else
			{
				secondLetterRow --; 		 // New encoded 'letter' will be the one above the current one.
				letterArr[3] = secondLetterRow;  // Set the array to reflect the change.
			}
		}
		else if (shape == 1) // Rectangle shape. 
		{
			// Temporary variables to store the column numbers of the two 'letters'
			int tempLetter1Col = letterArr[1];
			int tempLetter2Col = letterArr[4];

			// Swap the column values.
			letterArr[1] = tempLetter2Col;
			letterArr[4] = tempLetter1Col;
		}

		// Lookup the values in the table using their new row and column numbers. 
		// table.get: Returns the value corresponding to the given row and column keys, or null if no such mapping exists.
		// ArrayList for holding new 'letters'
		ArrayList<Integer> newLetter = new ArrayList<Integer>(0);

		// Get integer from table to a string.
		int x1 = table.get(letterArr[0], letterArr[1] );
		int x2 = table.get(letterArr[3], letterArr[4] );
		char c1 = '\0';
		char c2 = '\0';
		
		// filter out Ý = ASCII integer 221, Latin capital letter Y with acute. http://www.ascii-code.com/
		if (x1 != 211) {
			c1 = (char)x1;		
		}
		if (x2 != 211) {
			c2 = (char)x2;
		}

		String c1AsString = Character.toString(c1);
		String c2AsString = Character.toString(c2);
		String letters = "";
		letters = letters.concat(c1AsString).concat(c2AsString);

		return letters;
	}// END getDecodedLetter()

}// END Ciphergrid Class
