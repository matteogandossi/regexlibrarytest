package library;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.BasicOperations;
import dk.brics.automaton.RegExp;

public class Brics extends Library {
	
	Automaton automaton;

	@Override
	public boolean match(String input) {		
		return BasicOperations.run(automaton, input);
	}

	@Override
	public void setRegex(String regex) {
		
		RegExp r = new RegExp(regex);
		automaton = r.toAutomaton();		
	}

	@Override
	public String libraryName() {
		return "Brics";
	}

}
