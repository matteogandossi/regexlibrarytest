package library;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

public class Brics extends Library {
	
	RunAutomaton runAutomaton;

	@Override
	public boolean match(String input) {		
		return runAutomaton.run(input);
	}

	@Override
	public void setRegex(String regex) {
		
		RegExp r = new RegExp(regex);
		Automaton automaton = r.toAutomaton();
		runAutomaton = new RunAutomaton(automaton);
	}

	@Override
	public String libraryName() {
		return "Brics";
	}

}
