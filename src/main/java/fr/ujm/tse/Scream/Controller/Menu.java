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
		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() { // On cr�e
		 * une nouvelle instance de notre JDialog Fenetre fenetre = new Fenetre(); try {
		 * fenetre.run(); } catch (BadLocationException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } fenetre.setVisible(true);// On la rend
		 * visible } }); Thread t = new Thread(new ParseWiki()); t.start();
		 */
		/*
		 * System.out.println("Entrez le nom d'un personnage "); Scanner sc1 = new
		 * Scanner(System.in); String str = sc1.nextLine(); Personnage
		 * perso=Parse.infoPersonnage(str); perso.afficher();
		 * Parse.titleComics(perso).afficheComics();
		 */

		System.out.println("Entrez le titre de Comics");
		int offset = 0;
		int num_page = 0;
		Scanner sc1 = new Scanner(System.in);
		String titre = sc1.nextLine();

		char fin = 'n';
		while (fin != 'y') {
			Comics comics = Parse.listeComics(titre, offset);
			comics.afficherComics();
			System.out.println("Vous pouvez changer de page (0) ou avoir des informations sur le comics (1) ou arr�ter l'application (2).");
			Scanner sc2 = new Scanner(System.in);
			int choix = sc2.nextInt();
			switch (choix) {
			case 0:
				System.out.println(
						"Pour changer de page : (-1 : page pr�c�dente / 0 : retour au d�but / 1 : page suivante)");
				Scanner sc3 = new Scanner(System.in);
				int page = sc2.nextInt();
				switch (page) {
				case -1:
					if (offset != 0) {
						offset = offset - 10;
						comics = Parse.listeComics(titre, offset);
						comics.afficherComics();
						num_page = num_page - 1;
					} else {
						comics = Parse.listeComics(titre, offset);
						comics.afficherComics();
					}
					break;
				case 0:
					offset = 0;
					comics = Parse.listeComics(titre, offset);
					comics.afficherComics();
					num_page = 0;
					break;
				case 1:
					if (offset < 50) {
						offset = offset + 10;
						comics = Parse.listeComics(titre, offset);
						comics.afficherComics();
						num_page = num_page + 1;
					} else {
						comics = Parse.listeComics(titre, offset);
						comics.afficherComics();
					}
					break;
				}
				break;
			case 1:
				System.out.println("Veuillez entrer un numero de pour avoir des informations sur le comics.");
				Scanner sc4 = new Scanner(System.in);
				int nbr = sc4.nextInt();
				comics = Parse.infoComics(titre, nbr, num_page);
				comics.afficher();
				break;
				// Pour arr�ter le programme
			case 2:
				System.out.println("Avez-vous fini de faire vos recherches ? (y/n)");
				Scanner sc5 = new Scanner(System.in);
				fin = sc5.nextLine().charAt(0);
				break;
			}
		}

	}
}
