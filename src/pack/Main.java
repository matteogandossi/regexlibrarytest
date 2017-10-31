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
	private final static int ITERATIONS = 2000000;
	private final static int NUM_TEST = 5;
	private static String filename = "hexcolorsLite";
	private static String regexType = "Misto";	
	
	/*
	 * Inizializzazione oggetti globali
	 */
	private static Times times;
	private static TestSuite testSuite;
	private static boolean allCorrect[][];
	private static Library[] lib = new Library[LIBRARY];	
	
	/*
	 * Salvataggio file CSV
	 */
	private static void saveonFile(int TSLength) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
		String outputFile = sdf.format(Calendar.getInstance().getTime()) + "_" + filename + ".csv";
		
		FileOutputStream out;
		
		try {
			
			out = new FileOutputStream(outputFile);
			PrintStream ps = new PrintStream(out);
			
			ps.print("RegexName,TestCase,TCLength,RegexType,ExpectedValue,Library,Result,Time(ms)");			
			ps.println();
			
			for(int i=0; i<TSLength; i++) 	
				for(int j=0; j<LIBRARY; j++) {
					
					ps.print(filename + "," + (i+1) + ",");
					ps.print(testSuite.testCaseSize(i) + "," + regexType + ",");
					ps.print(testSuite.testCaseValue(i) + "," + lib[j].libraryName() + ",");
					ps.print(allCorrect[j][i] + "," + times.mean(j, i));
					ps.print("\n");
				}
			
			out.close();
			
			System.out.println("Scrittura su file effettuata.");
			
		} catch (IOException e) {}		
		
	}
	
	
	public static void main(String[] args) {
		
		long begin,end;
		boolean correct = true;
		int sizeTS;
		
		/*
		 * Lettura da file
		 */		
		testSuite = Reader.read(filename + ".xml");
		sizeTS = testSuite.size();
		String regex = Reader.getRegex(filename + ".xml");
		
		/*
		 * Inizializzazione librerie
		 */		
		lib[0] = new JUtil();
		lib[1] = new Brics();
		lib[2] = new JRegex();
		
		/*
		 * Inizializzazione var
		 */
		times = new Times(LIBRARY, testSuite.size(), NUM_TEST);
		allCorrect = new boolean[LIBRARY][testSuite.size()];
		
		/*
		 * Inizializzazione automa
		 */
		for(int library=0; library<LIBRARY; library++)
			lib[library].setRegex(regex);
		
		/*
		 * Test
		 */
		for(int test_case=0; test_case<sizeTS; test_case++) {
			
			System.out.println("---TEST FOR STR " + (test_case+1));
			
			for(int library=0; library<LIBRARY; library++) {
				
				int i;
				Library current_lib = lib[library];
				
				for(int num_test=0; num_test<NUM_TEST; num_test++) {
					
					begin = System.currentTimeMillis();
					
					for(i=0; i<ITERATIONS; i++)
						correct = testSuite.evaluate(current_lib, test_case);
					
					end = System.currentTimeMillis();
					
					times.set(library, test_case, num_test, (end-begin));
					System.out.println("Analisi lib \"" + lib[library].libraryName() + "\"");
					System.out.println("Proof: "+(num_test+1));
					System.out.println("Tempo: " + times.get(library,test_case,num_test));
					
				}
				
				allCorrect[library][test_case] = correct;				
				
			}
			
			System.out.println("---END TEST\n");
			
		}
		
		for(int i=0; i<LIBRARY; i++) {
			
			System.out.println(lib[i].libraryName()+"\t");
			for(int j=0; j<sizeTS; j++) {
				
				System.out.format("%3d ", times.mean(i, j));				
			}
			System.out.println();
		}
		
		saveonFile(sizeTS);

	}

}
