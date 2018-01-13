package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.text.BadLocationException;

import org.json.JSONException;

import fr.ujm.tse.Scream.View.Fenetre;
/**
 * Bouton dans l'affichage de la liste des comics affiche les precedents 
 * @author Scream
 *
 */
public class BoutonPrecedent implements ActionListener {
	private Fenetre fenetre;
	private int offset;
	
	public BoutonPrecedent(Fenetre f){
		
		fenetre=f;
		offset=0;
	}
 
	public void actionPerformed(ActionEvent e) { 
		String str = fenetre.getChampComics().getText();
		fenetre.setChampStartComics(str);
		if(fenetre.getOffset()>=0) {
			offset=fenetre.getOffset();
			fenetre.setOffset(offset-10);
		}else {
			fenetre.setOffset(0);
		}
		try {
			fenetre.afficheListeComics(str,fenetre.getOffset());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
