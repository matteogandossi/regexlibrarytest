package oracolo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

public class Writer {
	
	private Oracolo list;
	private String regex;
	
	public Writer(Oracolo list,String regex){
		this.list = list;
		this.regex = regex;
	}
	
	public boolean export(String fileOutput){
		
		PrintStream file;
		String outputFileName = fileOutput + "Lite.xml";
		
		try {
			file = new PrintStream(new FileOutputStream(outputFileName));
		} catch (FileNotFoundException e) {
			return false;
		}
		
		file.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		file.println("<file for=\"" + fileOutput + "\">");
		file.println("<regex>" + regex + "</regex>");
		file.println("<nofelements>" + list.size() + "</nofelements>");
		file.println("<elements>");
		
		Iterator<Item> it = list.getIterator();
		
		int i=0;
		
		while(it.hasNext()){
			
			Item current = it.next();
			
			file.println("\t<element id=\"" + (i+1) + "\">" );
			file.println("\t\t<item>" + current.getItem() + "</item>" );
			file.println("\t\t<value>" + current.getValue() + "</value>" );
			file.println("\t</element>" );
			i++;
		}
				
		file.println("</elements>");
		file.print("</file>");
		
		file.close();
		return true;
	}

}
