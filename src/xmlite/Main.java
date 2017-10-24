package xmlite;

public class Main {
	
	private static void writeOnFile(TestSuite list, String outputFileName, String regex){
		
		Writer w = new Writer(list,regex);
		
		if(w.export(outputFileName))
			Grafica.showInfoMessage("Scrittura del file completata.", "Scrittura completata con successo!");
		else
			Grafica.showErrorMessage("Scrittura del file non riuscita!", "Errore scrittura!");
		
	}
		
	private static void newFile(){
		
		String title = Grafica.getFileName("Inserisci l'argomento del file.");
		String regex = Grafica.getRegex("Inserisci la regex");
		TestSuite list = new TestSuite();
		Item newItem;
		int count = 0;
		
		do{
			newItem = Grafica.getItem("Inserisci il nuovo elemento.\n"
									+ "Per terminare l'inserimento, chiudere la finestra. ", 
									"Inserimento nuovo elemento", count);
			if(newItem != null)
				list.add(newItem);
			
			count++;
			
		}while(newItem != null);
		
		writeOnFile(list, title, regex);
	
	}
	
	public static void main(String[] args){
		
		newFile();
	}
		

}