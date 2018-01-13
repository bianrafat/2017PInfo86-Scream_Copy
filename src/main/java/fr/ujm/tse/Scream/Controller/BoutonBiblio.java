package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import fr.ujm.tse.Scream.View.Fenetre;
/**
 * bouton qui permet de passer dans l'affichage de la bibliothèque 
 * @author Scream
 *
 */
public class BoutonBiblio implements ActionListener {
	private Fenetre fenetre;

	public BoutonBiblio(Fenetre f) {
		
		fenetre = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			fenetre.boutonBiblio();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	
	

}
