package fr.ujm.tse.Scream.Controller;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonRepNon extends AbstractAction {
	private Fenetre fenetre;
	public BoutonRepNon(String texte, Fenetre f){
		super(texte);
		fenetre=f;
	}
 
	public void actionPerformed(ActionEvent e) { 
		fenetre.boutonRepNon();
	} 
}