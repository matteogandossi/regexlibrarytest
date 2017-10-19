package library;

import jregex.Matcher;
import jregex.Pattern;

public class JRegex extends Library {
	
	Pattern pattern;
	Matcher matcher;

	@Override
	public boolean match(String input) {
		
		matcher = pattern.matcher(input);
		
		return matcher.matches();
		
	}

	@Override
	public void setRegex(String regex) {
		
		pattern = new Pattern(regex);
	}

	@Override
	public String libraryName() {
		return "JRegex";
	}

}
