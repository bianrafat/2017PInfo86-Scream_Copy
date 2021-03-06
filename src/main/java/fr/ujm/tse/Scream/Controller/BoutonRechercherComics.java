package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.text.BadLocationException;

import org.json.JSONException;

import fr.ujm.tse.Scream.View.Fenetre;
/**
 * Bouton qui permet de recherche la liste de comics en fonction d'un titre 
 * @author Scream
 *
 */
public class BoutonRechercherComics implements ActionListener , KeyListener{
	private Fenetre fenetre;
	
	public BoutonRechercherComics(Fenetre f){
		
		fenetre=f;
	}
 
	public void actionPerformed(ActionEvent e) { 
		String str = fenetre.getChampComics().getText();
		fenetre.setChampStartComics(str);
		try {
			fenetre.afficheListeComics(str,0);
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}