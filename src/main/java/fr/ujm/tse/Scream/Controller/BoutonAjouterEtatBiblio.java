package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fr.ujm.tse.Scream.View.Fenetre;
import fr.ujm.tse.Scream.View.RowPopup;
/**
 * Bouton qui change l'etat d'un comics dans la bibliothèque
 * @author Scream
 *
 */
public class BoutonAjouterEtatBiblio implements ActionListener {
	private Fenetre fenetre;
	private String dbName;
	private String userName;
	private String pass;
	private int id;
	private String etat;
	private RowPopup popup;
	
	
	
	public BoutonAjouterEtatBiblio(RowPopup popup, Fenetre f, String dbName, String userName, String pass, int id) {
		super();
		this.popup=popup;
		this.fenetre = f;
		this.dbName = dbName;
		this.userName = userName;
		this.pass = pass;
		this.id = id;
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		etat=popup.getTextEtat().getText();
		if(Database.updateEtat(dbName, userName, pass, id, etat)){
			JOptionPane.showMessageDialog(null," Modifié avec succès !");
		}else {
			JOptionPane.showMessageDialog(null," Erreur!");
		}
		
		try {
			fenetre.boutonBiblio();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		fenetre.revalidate();
		fenetre.repaint();
		
		
	}

}
