package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.ujm.tse.Scream.View.Fenetre;
/**
 * Bouton qui lance l'affichage de la connexion 
 * @author Scream
 *
 */
public class BoutonConnexion implements ActionListener {
	private Fenetre fenetre;
	public BoutonConnexion(Fenetre f){
		fenetre=f;
	}
	
 
	public void actionPerformed(ActionEvent e) { 
		fenetre.boutonConnexion();
	} 
}