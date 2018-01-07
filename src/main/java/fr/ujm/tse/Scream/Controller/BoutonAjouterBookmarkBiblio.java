package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonAjouterBookmarkBiblio implements ActionListener{
	private Fenetre fenetre;
	private String dbName;
	private String userName;
	private String pass;
	private int id;
	private int bookmark;
	
	public BoutonAjouterBookmarkBiblio(Fenetre f,String dbName, String userName, String pass, int id, int bookmark)
	 {
		
		fenetre = f;
		this.dbName=dbName;
		this.userName= userName;
		this.pass=pass;
		this.id=id;
		this.bookmark=bookmark;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(Database.updateBookmark(dbName, userName, pass, id, bookmark)) {
			JOptionPane.showMessageDialog(null," Modifier avec succ�s !");
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
