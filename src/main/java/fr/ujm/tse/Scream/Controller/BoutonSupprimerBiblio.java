package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonSupprimerBiblio implements ActionListener {
	private Fenetre fenetre;
	private String dbName;
	private String userName;
	private String pass;

	public BoutonSupprimerBiblio(Fenetre f,String dbName, String userName, String pass) {
		// TODO Auto-generated constructor stub
		this.dbName=dbName;
		this.userName=userName;
		this.pass=pass;
	
		
		fenetre = f;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int rowIndex=fenetre.getTable().getSelectedRow();
		int id = Integer.parseInt((String)fenetre.getTable().getValueAt(rowIndex, 0));
		
		if(Database.deleteLigne(id, dbName, userName, pass)){
			JOptionPane.showMessageDialog(null," supprimer avec succès !");
		}else {
			JOptionPane.showMessageDialog(null," erreur!");
		}
		((AbstractTableModel) fenetre.getTable().getModel()).fireTableDataChanged();
		//fenetre.getTable().repaint();
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
