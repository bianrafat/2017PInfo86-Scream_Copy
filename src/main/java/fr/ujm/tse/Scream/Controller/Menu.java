package fr.ujm.tse.Scream.Controller;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.jar.JarException;

import javax.swing.SwingUtilities;

import org.json.JSONException;

import fr.ujm.tse.Scream.View.Fenetre;
import fr.ujm.tse.Screram.Model.Comics;
import fr.ujm.tse.Screram.Model.Parse;
import fr.ujm.tse.Screram.Model.Personnage;

public class Menu {
	public static void main(String[] args) throws IOException, JSONException, NoSuchAlgorithmException {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une nouvelle instance de notre JDialog
				Fenetre fenetre = new Fenetre();
				fenetre.run();
				fenetre.setVisible(true);//On la rend visible
			}
		});
		/*System.out.println("Entrez le nom d'un personnage ");
		Scanner sc1 = new Scanner(System.in);
		String str = sc1.nextLine();
		Personnage perso=Parse.infoPersonnage(str);
		perso.afficher();
		Parse.titleComics(perso).afficheComics();*/
		
		
		System.out.println("Entrez un titre de comics (le début ou le titre complet) : ");
		
		//Permet de prendre en compte ce qui est ecrit sur la console
		Scanner sc1 = new Scanner(System.in); 
		String str = sc1.nextLine();
		Comics comics = Parse.listeComics(str);
		comics.afficherComics();
		int i = sc1.nextInt();
		comics = Parse.infoComics(str, i);
		comics.afficher();
		}
}
