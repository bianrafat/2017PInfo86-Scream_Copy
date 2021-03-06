package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.ujm.tse.Scream.View.Fenetre;
/**
 * Bouton qui lance l'affichage de recherche pour wikidata
 * @author Scream
 *
 */
public class BoutonWikiData extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private Fenetre fenetre;
	public BoutonWikiData(String texte, Fenetre f){
		super(texte);
		fenetre=f;
	}
 
	public void actionPerformed(ActionEvent e) { 
		fenetre.boutonWikidata();
	}
	
	
}