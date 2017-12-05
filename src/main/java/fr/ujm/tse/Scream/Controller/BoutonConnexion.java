package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonConnexion implements ActionListener {
	private Fenetre fenetre;
	public BoutonConnexion(Fenetre f){
		fenetre=f;
	}
	
 
	public void actionPerformed(ActionEvent e) { 
		fenetre.boutonConnexion();
	} 
}