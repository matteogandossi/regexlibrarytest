package xmlite;

public class Item {
	
	private String item;
	private boolean value;
	
	
	public Item(String item, boolean value){
		this.item = item;
		this.value = value;
	}
	
	public String getItem() {
		return item;
	}
	
	public boolean getValue() {
		return value;
	}
	
	@Override
	public String toString(){
		
		return item + " - " + value;
	}


}
