package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonBiblio implements ActionListener {
	private Fenetre fenetre;

	public BoutonBiblio(Fenetre f) {
		
		fenetre = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		fenetre.boutonBiblio();
		
	}
	
	
	
	

}
