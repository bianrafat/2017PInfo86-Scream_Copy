package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.BadLocationException;

import fr.ujm.tse.Scream.View.Fenetre;
/**
 * Bouton qui deconnecte l'utilisateur de la bibliothèque 
 * @author Scream
 *
 */
public class BoutonDeco implements ActionListener {
	private Fenetre fenetre;
	public BoutonDeco(Fenetre f){
		fenetre=f;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Database.deconnection();
		// TODO Auto-generated method stub
		fenetre.setNameBiblio(null);
		fenetre.setConUser(null);
		fenetre.setConMdp(null);
		try {
			fenetre.setContentPane(fenetre.buildContentPane());
			fenetre.revalidate();
			
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

}
