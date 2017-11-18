package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.text.BadLocationException;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonRetour extends AbstractAction {
	private Fenetre fenetre;
	public BoutonRetour(String texte, Fenetre f){
		super(texte);
		fenetre=f;
	}
 
	public void actionPerformed(ActionEvent e) { 
		fenetre.repaint();
		try {
			fenetre.setContentPane(fenetre.buildContentPane());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fenetre.revalidate();
		
	} 
}