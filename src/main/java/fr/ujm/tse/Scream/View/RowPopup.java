package fr.ujm.tse.Scream.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;

import fr.ujm.tse.Scream.Controller.BoutonAjouterCommentaireBiblio;

public class RowPopup extends JPopupMenu{
	private JTable table;
	private Fenetre fenetre;
	private JMenuItem com;
	private JMenuItem note;
	private JMenuItem bookmark;
	private JMenuItem etat;
	private JDialog dialog;
	private String dbName;
	private String userName;
	private String pass;
	private JButton ajouterCom=new JButton("Ajouter le commentaire");
	private JTextArea textCom= new JTextArea();
	
	public RowPopup(JTable table,Fenetre f,String dbName, String userName, String pass){
		this.table=table;
		fenetre = f;
		this.dbName=dbName;
		this.userName=userName;
		this.pass=pass;
		JMenuItem com=new JMenuItem("Ajouter un commentaire");
		JMenuItem note=new JMenuItem("Ajouter une note");
		JMenuItem bookmark=new JMenuItem("Ajouter un bookmark");
		JMenuItem etat=new JMenuItem("Etat");
		dialog = new JDialog();
		dialog.setSize(new Dimension(210,200));
		dialog.setLocationRelativeTo(null);
		JPanel panel = new JPanel(new BorderLayout());
		JPanel bouton = new JPanel(new FlowLayout());
		textCom.setPreferredSize(new Dimension(200,100));
		ajouterCom.setPreferredSize(new Dimension(200,30));
		com.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int rowIndex=fenetre.getTable().getSelectedRow();
				String commentaire =(String)fenetre.getTable().getValueAt(rowIndex, 6);
				textCom.setText(commentaire);
		    	bouton.add(ajouterCom);
		    	panel.add(textCom,BorderLayout.NORTH);
				panel.add(bouton,BorderLayout.CENTER);
				dialog.setModal(true);
				dialog.add(panel);
		        dialog.setVisible(true);
		        ajouterCom.addActionListener(new BoutonAjouterCommentaireBiblio(fenetre, dbName,userName,pass, Integer.parseInt((String) fenetre.getTable().getValueAt(fenetre.getTable().getSelectedRow(), 0)),textCom.getText()));
		    
		    }
		});
			
		add(com);
		add(note);
		add(bookmark);
		add(etat);
	}	

}
