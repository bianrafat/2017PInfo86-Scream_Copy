package fr.ujm.tse.Scream.Controller;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.ujm.tse.Scream.View.Fenetre;
/**
 * Bouton qui permet a l'utilisateur de se connecter à sa bibliotheque 
 * @author Scream
 *
 */
public class BoutonRepOui extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	private Fenetre fenetre;
	public BoutonRepOui(String texte, Fenetre f){
		super(texte);
		fenetre=f;
	}
 
	public void actionPerformed(ActionEvent e) { 
		fenetre.boutonRepOui();
	} 
}