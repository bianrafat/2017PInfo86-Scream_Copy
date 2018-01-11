package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonValideNewBiblio extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fenetre fenetre;
	private String userName;
	private String password;
	
	public BoutonValideNewBiblio(String texte, Fenetre f){
		super(texte);
		fenetre=f;
	}
 
	public void actionPerformed(ActionEvent e) { 
		userName=fenetre.getChampUser().getText();
		password=fenetre.getChampMdp().getText();
		if(Database.createDatabase(userName, userName, password)) {
			JOptionPane.showMessageDialog(null,"Biblioth�que cr��e avec succ�s !");
			fenetre.setNameBiblio(userName);
			fenetre.setConUser(userName);
			fenetre.setConMdp(password);
			try {
				fenetre.setContentPane(fenetre.buildContentPane());
				fenetre.revalidate();
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Erreur! Un champ doit �tre vide");
		}
	} 
}