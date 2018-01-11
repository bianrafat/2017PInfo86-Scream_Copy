package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fr.ujm.tse.Scream.View.Fenetre;
import fr.ujm.tse.Scream.View.RowPopup;

public class BoutonAjouterNoteBiblio implements ActionListener {
	private Fenetre fenetre;
	private RowPopup popup;
	private String dbName;
	private String userName;
	private String pass;
	private int id;
	private int note;

	public BoutonAjouterNoteBiblio(RowPopup fenetre2,Fenetre f, String userName, String pass, String pass2, int id) {
		// TODO Auto-generated constructor stub
		popup=fenetre2;
		fenetre = f;
		this.dbName=userName;
		this.userName= pass;
		this.pass=pass2;
		this.id=id;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.note=Integer.parseInt(popup.getTextNote().getText());
		if(Database.updateNote(dbName, userName, pass, id, note)){
			JOptionPane.showMessageDialog(null," Modifié avec succès !");
		}else {
			JOptionPane.showMessageDialog(null," Erreur!");
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
