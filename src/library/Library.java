package library;

public abstract class Library {
	
	public abstract void setRegex(String regex);
	
	public abstract boolean match(String input);
	
	public abstract String libraryName();
		

}
