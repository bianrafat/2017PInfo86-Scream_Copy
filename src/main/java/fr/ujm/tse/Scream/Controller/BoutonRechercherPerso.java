package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.AbstractAction;

import org.json.JSONException;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonRechercherPerso extends AbstractAction {
	private Fenetre fenetre;
	public BoutonRechercherPerso(String texte, Fenetre f){
		super(texte);
		fenetre=f;
	}
 
	public void actionPerformed(ActionEvent e) { 
		String str = fenetre.getChampPerso().getText();
		try {
			fenetre.affichePerso(str);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	} 
}