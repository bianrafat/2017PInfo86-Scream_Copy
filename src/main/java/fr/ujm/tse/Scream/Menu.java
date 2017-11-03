package fr.ujm.tse.Scream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.json.JSONException;

public class Menu {
	public static void main(String[] args) throws IOException, JSONException, NoSuchAlgorithmException {
		System.out.println("Entrez le nom d'un personnage ");
		Scanner sc1 = new Scanner(System.in);
		String str = sc1.nextLine();
		Personnage perso=Parse.infoPersonnage(str);
		perso.afficher();
		Parse.titleComics(perso).afficheComics();
		}
}
