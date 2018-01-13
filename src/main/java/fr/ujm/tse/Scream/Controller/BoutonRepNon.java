package fr.ujm.tse.Scream.Controller;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.ujm.tse.Scream.View.Fenetre;
/**
 * Bouton qui affiche le panel pour créer une nouvelle bibliotheque 
 * @author Scream
 *
 */
public class BoutonRepNon extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	private Fenetre fenetre;
	public BoutonRepNon(String texte, Fenetre f){
		super(texte);
		fenetre=f;
	}
 
	public void actionPerformed(ActionEvent e) { 
		fenetre.boutonRepNon();
	} 
}