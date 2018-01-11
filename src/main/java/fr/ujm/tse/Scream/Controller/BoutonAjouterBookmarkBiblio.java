package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fr.ujm.tse.Scream.View.Fenetre;
import fr.ujm.tse.Scream.View.RowPopup;

public class BoutonAjouterBookmarkBiblio implements ActionListener{
	private Fenetre fenetre;
	private RowPopup popup;
	private String dbName;
	private String userName;
	private String pass;
	private int id;
	private int bookmark;
	
	public BoutonAjouterBookmarkBiblio(RowPopup popup,Fenetre fenetre2, String userName, String pass, String pass2, int id)
	 {
		this.popup=popup;
		fenetre = fenetre2;
		this.dbName=userName;
		this.userName= pass;
		this.pass=pass2;
		this.id=id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.bookmark=Integer.parseInt(popup.getTextBookmark().getText());
		if(Database.updateBookmark(dbName, userName, pass, id, bookmark)) {
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
