package fr.ujm.tse.Scream.Controller;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;
//import java.util.Scanner;
//import java.util.jar.JarException;

import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException; 

import org.json.JSONException;
//import fr.ujm.tse.Scream.Model.ParseWiki;

/*import fr.ujm.tse.Scream.Model.Comics;
import fr.ujm.tse.Scream.Model.Parse;
import fr.ujm.tse.Scream.Model.Personnage;*/
import fr.ujm.tse.Scream.View.Fenetre; 
/**
 * Classe qui lance l'application
 * @author Scream
 *
 */
public class Menu {
	public static void main(String[] args) throws IOException, JSONException, NoSuchAlgorithmException {
		
		 SwingUtilities.invokeLater(new Runnable() { 
			public void run() {
				 // On crée une nouvelle instance de notre JDialog 
				 Fenetre fenetre = new Fenetre(); 
				 try {
					 fenetre.run(); 
				} catch (BadLocationException | IOException e) { 
					 e.printStackTrace(); 
				} 
				 fenetre.setVisible(true);// On la rend visible 
				 } 
		}); 
	}
}
