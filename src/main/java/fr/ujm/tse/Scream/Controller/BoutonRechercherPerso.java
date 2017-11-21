package fr.ujm.tse.Scream.Controller;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.AbstractAction;
import javax.swing.text.BadLocationException;

import org.json.JSONException;

import fr.ujm.tse.Scream.View.Fenetre;

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


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	} 
}