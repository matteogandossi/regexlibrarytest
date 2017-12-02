package xmlite;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Grafica {
	
	private Grafica(){}
	
	/*
	 * Metodo che mostra un messaggio di tipo INFO
	 */
	public static void showInfoMessage(String msg, String title){
		
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/*
	 * Metodo che mostra un messaggio di tipo WARNING
	 */
	public static void showWarningMessage(String msg, String title){
		
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
	}
	
	/*
	 * Metodo che mostra un messaggio di tipo ERROR
	 */
	public static void showErrorMessage(String msg, String title){
		
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
	}
	/*
	 * 
	 */
	public static String getFileName(String message){
		
		return JOptionPane.showInputDialog(message);
	}
	
	public static String getRegex(String message){
		
		return JOptionPane.showInputDialog(message);
	}
	
	/*
	 * Get item
	 */
	public static Item getItem(String msg, String title, int count){
		
		msg = msg + "\nElementi inseriti: " + count +"\n";
		
		JTextField item = new JTextField();
		JCheckBox selezione = new JCheckBox("Corretta");
		selezione.setSelected(true);
		
		Object[] grid = {
			msg,
		    "Valore", item,
		    selezione
		};
		
		int res = JOptionPane.showConfirmDialog(null, grid, title, JOptionPane.DEFAULT_OPTION);
		
		if (res == JOptionPane.YES_OPTION){
			String result = item.getText();
			boolean value = selezione.isSelected();
			
			return new Item(result, value);
		}
		else
			return null;
	
	}
	/*
	 * 
	 */
	public static int chooseOperation(){
		
		Object[] scelte = {"Nuovo file", "Autogenerato TRUE", "Autogenerato FALSE"}; 
		
		int utente = JOptionPane.showOptionDialog(null,
				"Seleziona l'operazione da effettuare",
				"XMLite",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				scelte,
				scelte[0]);	
		
		switch (utente) {
		
		case 0:
			return 0;
			
		case 1:
			return 1;
		
		case 2:
			return 2;
	
		default:
			return -1;
		}
		
	}

	public static int getSize(String message) {
		
		String n = JOptionPane.showInputDialog(message);
		return Integer.parseInt(n);
	}

}
