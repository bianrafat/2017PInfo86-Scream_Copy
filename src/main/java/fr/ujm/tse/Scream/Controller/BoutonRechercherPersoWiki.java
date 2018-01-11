package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import org.json.JSONException;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonRechercherPersoWiki implements ActionListener , KeyListener{
	private Fenetre fenetre;
	public BoutonRechercherPersoWiki(Fenetre f){
		
		fenetre=f;
	}
 
	public void actionPerformed(ActionEvent e) { 

		String str = fenetre.getChampWikiP().getText();
		try {
			fenetre.afficheListeWiki(str);	
		} catch (JSONException e1) {
			JOptionPane.showMessageDialog(null,"Le champ est vide! ");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		} catch (MediaWikiApiErrorException e1) {
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