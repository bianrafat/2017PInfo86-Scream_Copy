package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.ujm.tse.Scream.View.Fenetre;
/**
 * Bouton qui affiche le formulaire pour rechercher un comics 
 * @author Scream
 *
 */
public class BoutonMenuComics extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fenetre fenetre;
	
	
	public BoutonMenuComics(String texte, Fenetre f){
		super(texte);
		fenetre=f;
		
	}
 
	public void actionPerformed(ActionEvent e) { 
		fenetre.boutonComics();
		
	} 
}