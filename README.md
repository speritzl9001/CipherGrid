# CipherGrid
A GUI based Java implementation of the Playfair cipher (with expanded table)<a href="http://en.wikipedia.org/wiki/Playfair_cipher" target="_blank"> Wikipedia</a>  

<img src="CipherGrid/images/CipherGrid.png" alt="CipherGrid"  width="837" height="572" CipherGrid>  

Please see <a href="https://github.com/speritzl9001/CipherGrid/blob/master/CipherGrid/resources/Info.pdf" target="_blank">Info.pdf</a> in the "resources" folder for a more detailed look at the program.  

Dependencies: <a href="https://code.google.com/p/guava-libraries/" target="_blank">Google Guava Libraries</a>  

Several Google Guava classes are used including: HashBasedTable,LinkedHashMap and Splitter.  

In HashBasedTable, each cell of the table contains two keys and one value so it's perfect for the Playfair cipher. The two keys are row and column, the value is the ASCII integer value of a letter.  

```java
private Table<Integer,Integer,Integer> table = HashBasedTable.create(1, 1, 12);  
```

In a LinkedHashMap duplicate entries are overwritten so it's used to remove duplicate letters in the keyword as required in the Playfair cipher. See Keyword.java in the src folder.   
```java
private Map keywordMap = new LinkedHashMap();
```  

Splitter is an replacement for the problematic built-in Java string splitting classes. See Message.java in the src folder. 
> The built in Java utilities for splitting strings can have some quirky behaviors. For example, String.split silently discards trailing separators, and StringTokenizer respects exactly five whitespace characters and nothing else.
>  
<a href="https://code.google.com/p/guava-libraries/wiki/StringsExplained" target="_blank">Google Guava Splitter</a>  

********

<h3>Eclipse</h3> is an free, open source integrated development environment (IDE). Written mostly in Java, Eclipse can be used to develop applications. <a href="http://eclipse.org/downloads/packages/eclipse-ide-java-developers/lunasr2" target="_blank">Eclipse for Java developers</a>  

Through plug-ins, Eclipse can also be used to develop applications in other programming languages:  
Ada, ABAP, C, C++, COBOL, Fortran, Haskell, JavaScript, Lasso, Lua, Natural, Perl, PHP, Prolog, Python(PyDev), R, Ruby (including Ruby on Rails framework), Scala, Clojure, Groovy, Scheme, and Erlang.  

To import the Google Guava libraries into the Eclipse IDE:   
  
1. In the Eclipse "Package Explorer" view: Right click the project file name.  
2. Build Path -> Configure Build Path  
3. Click the tab "Libraries"   
4. Click the button "Add External JARs..."  
5. Select the Google Guava Libraries you downloaded.  
6. Click "OK"
