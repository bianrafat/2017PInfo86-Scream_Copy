package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import fr.ujm.tse.Scream.View.Fenetre;
/**
 * Bouton qui valide la connexion 
 * @author Scream
 *
 */
public class BoutonValideId extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	private Fenetre fenetre;
	private String userName;
	private String password;
	
	public BoutonValideId(String texte, Fenetre f){
		super(texte);
		fenetre=f;
	}
 
	public void actionPerformed(ActionEvent e) { 
		userName=fenetre.getChampUser().getText();
		password=fenetre.getChampMdp().getText();
		if(Database.connectDatabase(userName,userName,password)) {
			JOptionPane.showMessageDialog(null, "Connexion réussie");
			fenetre.setNameBiblio(userName);
			fenetre.setConUser(userName);
			fenetre.setConMdp(password);
			try {
				fenetre.setContentPane(fenetre.buildContentPane());
				fenetre.revalidate();
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Erreur! Un champ doit être mal renseigné");
		}
	} 
}