package xmlite;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Reader {
	
	private Reader() {}
	
	private static Document parseDoc(String filename) throws ParserConfigurationException, SAXException, IOException {
		
		File f = new File(filename);
		
		//classe factory per istanziare il documentbuilder
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//serve per parsare il file xml in un document per poi essere percorso come albero
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		
		//elemento document per seguire il doc
		Document doc;
		doc = db.parse(f);
		
		return doc;
		
	}
	
	public static TestSuite read(String filename){
		
		TestSuite lista = new TestSuite();
		Document d;
		
		try {
			d = Reader.parseDoc(filename);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			Grafica.showErrorMessage("Errore lettura file", "");
			return null;
		}
		
		NodeList parentEl = d.getElementsByTagName("element");
		
		int length = d.getElementsByTagName("element").getLength();
		
		for(int i=0; i<length; i++) {
			
			Element current = (Element) parentEl.item(i);
			
			String item = current.getElementsByTagName("item").item(0).getTextContent();
			boolean value = Boolean.parseBoolean(current.getElementsByTagName("value").item(0).getTextContent());
			
			lista.add(new Item(item, value));
		}
				
		
		return lista;
		
	}
	
	public static String getRegex(String filename) {
		
		Document d;
		
		try {
			d = Reader.parseDoc(filename);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			
			Grafica.showErrorMessage("Errore lettura file", "");
			return null;
		}
		
		return d.getElementsByTagName("regex").item(0).getTextContent();
		
	}

}
