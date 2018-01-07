package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonAjouterNoteBiblio implements ActionListener {
	private Fenetre fenetre;
	private String dbName;
	private String userName;
	private String pass;
	private int id;
	private int note;

	public BoutonAjouterNoteBiblio(Fenetre f,String dbName, String userName, String pass, int id, int note) {
		// TODO Auto-generated constructor stub
		fenetre = f;
		this.dbName=dbName;
		this.userName= userName;
		this.pass=pass;
		this.id=id;
		this.note=note;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(Database.updateNote(dbName, userName, pass, id, note)){
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
