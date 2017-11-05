package generex;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;
import generex.Generex;

public class RegexOracle {
	
	private RegExp faultyRE, oracleRE;
	private Set<String> positiveStrings, negativeStrings;
	
	public RegexOracle(String faulty, String oracle, int np, int nn) {
		
		faultyRE = new RegExp(faulty);
		oracleRE = new RegExp(oracle);
		
		positiveStrings = generatePositiveStrings(oracleRE.toAutomaton(), np);
		negativeStrings = generateNegativeStrings(oracleRE.toAutomaton(), nn);
	}

	private Set<String> generatePositiveStrings(Automaton oracleA, int np) {
		
		Set<String> result = new TreeSet<String>();
		
		Generex g = new Generex(oracleA);
		
		RunAutomaton ra = new RunAutomaton(oracleA);
		if (ra.run(""))
			result.add("");
		
		int i = 0;
		while (i < np) {
			if (result.add(g.random()))
				i++;
		}
		
		return result;
		
	}
	
	private Set<String> generateNegativeStrings(Automaton oracleA, int nn) {
		
		RegExp univ = new RegExp("[\\u0032-\\u0126]*");
		Automaton univA = univ.toAutomaton();
		Automaton complA = oracleA.complement();
		Automaton intersA = complA.intersection(univA);
		return generatePositiveStrings(intersA, nn);
		
	}
	
	public RegExp getFaultyRE() {
		return faultyRE;
	}
	
	public RegExp getOracleRE() {
		return oracleRE;
	}
	
	public Iterator<String> getPositiveIterator() {
		return positiveStrings.iterator();
	}
	
	public Iterator<String> getNegativeIterator() {
		return negativeStrings.iterator();
	}

}
