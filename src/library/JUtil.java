package library;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JUtil extends Library {
	
	Pattern pattern;
	Matcher matcher;

	@Override
	public boolean match(String input) {
		
		matcher = pattern.matcher(input);
		
		return matcher.matches();
	}

	@Override
	public void setRegex(String regex) {
		
		pattern = Pattern.compile(regex);		
	}

	@Override
	public String libraryName() {
		return "JUtil";
	}
	
	

}
