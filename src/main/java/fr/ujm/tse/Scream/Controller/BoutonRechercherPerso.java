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
 *  Bouton qui affiche les informations d'un personnage 
 * @author Scream
 *
 */
public class BoutonRechercherPerso implements ActionListener , KeyListener{
	private Fenetre fenetre;
	private String str;
	public BoutonRechercherPerso(Fenetre f){
		fenetre=f;
	}
	
	
	public void actionPerformed(ActionEvent e) { 
		
		try {
			str = fenetre.getChampPerso().getText();
			fenetre.affichePerso(str);
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