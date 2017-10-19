package oracolo;

import java.util.ArrayList;
import java.util.Iterator;

import library.Library;

public class Oracolo {
	
	private ArrayList<Item> lista;
	
	public Oracolo() {
		lista = new ArrayList<Item>();
		
	}
	
	public void add(Item e) {
		lista.add(e);
	}
	
	public Iterator<Item> getIterator(){
		return lista.iterator();
	}
	
	public double evaluate(Library l) {
		
		int corrette = 0;
		
		Iterator<Item> i = getIterator();
		
		while(i.hasNext()) {
			
			Item current = i.next();
			
			String input = current.getItem();
			boolean expected = current.getValue();
			
			if(l.match(input) == expected)
				corrette++;
		}
		
		return (double)corrette/lista.size();
	}
	
	@Override
	public String toString() {
		
		return "lista";
	}
	
	public int size() {
		return lista.size();
	}

}
