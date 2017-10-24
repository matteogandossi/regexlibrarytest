package pack;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import library.Brics;
import library.JRegex;
import library.JUtil;
import library.Library;
import xmlite.TestSuite;
import xmlite.Reader;

public class Main {
	
	/*
	 * Dichiarazione numero prove
	 */
	private final static int LIBRARY = 3;
	private final static int ITERATIONS = 100000;
	private final static int NUM_TEST = 10;
	private static String filename = "floatingLite";
	
	/*
	 * Inizializzazione oggetti globali
	 */
	private static Times times = new Times(LIBRARY, NUM_TEST);
	private static double allscore[][] = new double[LIBRARY][NUM_TEST];
	private static Library[] lib = new Library[LIBRARY];
	
	/*
	 * Metodo di scrittura su file
	 */
	private static void saveonFile(int testSuiteLength) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
		String outputFile = sdf.format(Calendar.getInstance().getTime()) + "_" + filename + ".txt";
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String today = format.format(Calendar.getInstance().getTime());
		
		FileOutputStream out;
		try {
			out = new FileOutputStream(outputFile);
			PrintStream ps = new PrintStream(out);
			
			ps.println("Testing on file: \t\t" + filename);
			ps.println("Date of test: \t\t\t" + today);
			ps.println("Number of Libraries: \t" + LIBRARY);
			ps.println("Number of Tests: \t\t" + NUM_TEST);
			ps.println("Number of Test Cases: \t" + testSuiteLength);
			ps.println("Number of Iterations: \t" + ITERATIONS);
			ps.println("Input Evalueted: \t\t" + (ITERATIONS*testSuiteLength));
			
			ps.println();
			
			//stampa dei tempi
			ps.println("Time result");
			for(int i=0; i<LIBRARY; i++) {
				
				ps.print(lib[i].libraryName()+" \t");
				
				for(int j=0; j<NUM_TEST; j++)
					ps.format("%5d ", times.get(i, j));
				ps.print("\n");
			}
			
			ps.println();
			
			//stampa dei tempi
			ps.println("Matching rate");
			for(int i=0; i<LIBRARY; i++) {
				
				ps.print(lib[i].libraryName()+" \t");
				
				for(int j=0; j<NUM_TEST; j++)
					ps.format("%5.1f ", allscore[i][j]);
				ps.print("\n");
			}
			
			ps.println();
			
			//stampa delle medie
			ps.println("Time Means");
			for(int i=0; i<LIBRARY; i++)				
				ps.println(lib[i].libraryName()+":\t\t"+times.mean(i));			
			
			out.close();			
			
		} catch (IOException e) {}		
		
	}

	public static void main(String[] args) {
		
		long begin,end;
		double score = 0.0;		
		
		/*
		 * Lettura da file
		 */		
		TestSuite testSuite = Reader.read(filename + ".xml");
		String regex = Reader.getRegex(filename + ".xml");
		
		/*
		 * Inizializzazione librerie
		 */		
		lib[0] = new JUtil();
		lib[1] = new Brics();
		lib[2] = new JRegex();
		
		/*
		 * Inserimento regex
		 */
		for(int library=0; library<LIBRARY; library++) {
			lib[library].setRegex(regex);
			testSuite.evaluate(lib[library]);
		}
		
		for(int test=0; test<NUM_TEST; test++) {
			
			System.out.println("---TEST " + (test+1));			
			
			for(int library=0; library<LIBRARY; library++) {
				
				int i;
				
				begin = System.currentTimeMillis();
				
				for(i=0; i<ITERATIONS; i++)
					score = testSuite.evaluate(lib[library]);
				
				end = System.currentTimeMillis();
				
				allscore[library][test] = score;
				
				times.set(library, test, (end-begin));
				
				System.out.println("Analisi lib \"" + lib[library].libraryName() + "\" with score = " + (score*100) + "%");
				System.out.println("Tempo: " + times.get(library, test));
			}
			
			System.out.println("---END TEST " + (test+1) +"\n");			
			
		}
		
		saveonFile(testSuite.size());
		
		System.out.println("Medie valutazioni");		
		//stampa delle medie
		for(int library=0; library<LIBRARY; library++) 
			System.out.println("Libreria \"" + lib[library].libraryName() +"\": \t" + times.mean(library));		
			
	}

}
