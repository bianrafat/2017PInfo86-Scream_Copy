package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonDeleteComicsBiblio implements ActionListener {
	private Fenetre fenetre;
	private int id;
	private String dbName;
	private String userName;
	private String pass;

	public BoutonDeleteComicsBiblio(Fenetre f,int id, String dbName, String userName, String pass) {
		this.id=id;
		this.dbName=dbName;
		this.userName=userName;
		this.pass=pass;
		
		fenetre = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(Database.deleteLigne(id,dbName,userName,pass)) {
			JOptionPane.showMessageDialog(null," Supprimé avec succès !");
		}else {
			JOptionPane.showMessageDialog(null," Erreur!");
		}
		fenetre.revalidate();
		fenetre.repaint();
		
	}
	
	
	
	

}
