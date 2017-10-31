package xmlite;

import java.util.ArrayList;
import java.util.Iterator;

import library.Library;

public class TestSuite {
	
	private ArrayList<Item> lista;
	
	public TestSuite() {
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
			else
				System.out.println(input);
		}
		
		return (double)corrette/lista.size();
	}
	
	public boolean evaluate(Library l,int test_case) {
		
		return (l.match(lista.get(test_case).getItem()) == lista.get(test_case).getValue());
	}
	
	public int testCaseSize(int test_case) {
		return lista.get(test_case).getItem().length();
	}
	
	@Override
	public String toString() {
		
		return "lista";
	}
	
	public int size() {
		return lista.size();
	}

	public boolean testCaseValue(int test_case) {
		return lista.get(test_case).getValue();
	}

}
