package fr.ujm.tse.Scream.Controller;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.jar.JarException;

import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import org.json.JSONException;
import fr.ujm.tse.Scream.Model.ParseWiki;

import fr.ujm.tse.Scream.Model.Comics;
import fr.ujm.tse.Scream.Model.Parse;
import fr.ujm.tse.Scream.Model.Personnage;
import fr.ujm.tse.Scream.View.Fenetre;

public class Menu {
	public static void main(String[] args) throws IOException, JSONException, NoSuchAlgorithmException {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une nouvelle instance de notre JDialog
				Fenetre fenetre = new Fenetre();
				try {
					fenetre.run();
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fenetre.setVisible(true);//On la rend visible
			}
		});
		Thread t = new Thread(new ParseWiki());
		t.start(); 
	/*	Database d = new Database();
		d.databse();
		System.out.println("Entrez le nom d'un personnage ");
		Scanner sc1 = new Scanner(System.in);
		String str = sc1.nextLine();
		Personnage perso=Parse.infoPersonnage(str);
		perso.afficher();
		Parse.titleComics(perso).afficheComics();
		System.out.println("Entrez le titre de Comics");
		Scanner sc1 = new Scanner(System.in);
		String str = sc1.nextLine();
		Comics comics = Parse.listeComics(str);
		comics.afficherComics();
		System.out.println("Veuillez entre un numero de pour avoir des informations sur le comics.");
		Scanner sc2 = new Scanner(System.in);
		int nbr = sc2.nextInt();
		comics = Parse.infoComics(str, nbr);
		comics.afficher(); */
		
		}
}
