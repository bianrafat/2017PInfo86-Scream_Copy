package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonTri  implements ActionListener{

	
	private Fenetre fenetre;
	private String triStr;
	private TableModel tabtri; 
	
	public BoutonTri(Fenetre f,TableModel t)
	 {
		fenetre = f;
		tabtri=t;
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		triStr=fenetre.getChampBiblioSearch().getText();
		if(fenetre.getCombo().getSelectedItem()=="Titre") {
			try {
				tabtri.triParTitre(triStr);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			fenetre.getTable().removeAll();
			fenetre.getTable().setModel(tabtri);
			fenetre.repaint();
		}else if(fenetre.getCombo().getSelectedItem()=="Auteur"){
			try {
				tabtri.triParAuteur(triStr);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			fenetre.getTable().removeAll();
			fenetre.getTable().setModel(tabtri);
			fenetre.repaint();
		}else {
			try {
				tabtri.resetData();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			fenetre.getTable().removeAll();
			fenetre.getTable().setModel(tabtri);
			fenetre.repaint();
		}
	}

}