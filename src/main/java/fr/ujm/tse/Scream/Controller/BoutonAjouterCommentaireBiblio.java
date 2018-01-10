package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fr.ujm.tse.Scream.View.Fenetre;
import fr.ujm.tse.Scream.View.RowPopup;

public class BoutonAjouterCommentaireBiblio implements ActionListener{
	private RowPopup popup;
	private Fenetre fenetre;
	private String dbName;
	private String userName;
	private String pass;
	private int id;
	private String com;
	
	public BoutonAjouterCommentaireBiblio(RowPopup pop,Fenetre fen,String dbName, String userName, String pass, int id)
	 {
		popup=pop;
		fenetre = fen;
		this.dbName=dbName;
		this.userName= userName;
		this.pass=pass;
		this.id=id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.com=popup.getTextCom().getText();
		if(Database.updateCom(dbName, userName, pass, id, com)) {
			JOptionPane.showMessageDialog(null," Modifier avec succès !");
			System.out.println(com);
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
