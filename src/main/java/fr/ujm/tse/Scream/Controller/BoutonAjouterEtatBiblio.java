package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonAjouterEtatBiblio implements ActionListener {
	private Fenetre fenetre;
	private String dbName;
	private String userName;
	private String pass;
	private int id;
	private String etat;

	
	
	
	public BoutonAjouterEtatBiblio(Fenetre fenetre, String dbName, String userName, String pass, int id, String etat) {
		super();
		this.fenetre = fenetre;
		this.dbName = dbName;
		this.userName = userName;
		this.pass = pass;
		this.id = id;
		this.etat = etat;
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		if(Database.updateEtat(dbName, userName, pass, id, etat)){
			JOptionPane.showMessageDialog(null," Modifier avec succès !");
		}else {
			JOptionPane.showMessageDialog(null," erreur!");
		}
		
		try {
			fenetre.boutonBiblio();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fenetre.revalidate();
		fenetre.repaint();
		
		
	}

}
