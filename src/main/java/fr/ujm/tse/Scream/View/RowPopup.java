package fr.ujm.tse.Scream.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;

import org.json.JSONException;

import fr.ujm.tse.Scream.Controller.BoutonAjouterBookmarkBiblio;
import fr.ujm.tse.Scream.Controller.BoutonAjouterCommentaireBiblio;
import fr.ujm.tse.Scream.Controller.BoutonAjouterEtatBiblio;
import fr.ujm.tse.Scream.Controller.BoutonAjouterNoteBiblio;

public class RowPopup extends JPopupMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Fenetre fenetre;
	private JMenuItem com;
	private JMenuItem note;
	private JMenuItem bookmark;
	private JMenuItem etat;
	private JMenuItem recomandationSerie=new JMenuItem("Suite de la série");
	private JMenuItem recomandationAuteur=new JMenuItem("Recomandation par auteur");
	private JDialog dialog;
	private JDialog dialog1;
	private JDialog dialog2;
	private JDialog dialog3;
	private String dbName;
	private String userName;
	private String pass;
	private JButton ajouterCom=new JButton("Ajouter le commentaire");
	private JButton ajouterNote=new JButton("Ajouter un note");
	private JButton ajouterBookmark=new JButton("Ajouter un bookmark");
	private JButton ajouterEtat=new JButton("Ajouter un etat");
	private JTextArea textCom= new JTextArea();
	private JTextArea textNote= new JTextArea();
	private JTextArea textBookmark= new JTextArea();
	private JTextArea textEtat= new JTextArea();
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
		RowPopup popup=this;
		com.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int rowIndex=fenetre.getTable().getSelectedRow();
				String commentaire =(String)fenetre.getTable().getValueAt(rowIndex, 6);
				textCom.setText(commentaire);
		    	bouton.add(ajouterCom);
		    	panel.add(textCom,BorderLayout.NORTH);
				panel.add(bouton,BorderLayout.CENTER);
				dialog.add(panel);
		        dialog.setVisible(true);
		        ajouterCom.addActionListener(new BoutonAjouterCommentaireBiblio(popup,fenetre, dbName,userName,pass, Integer.parseInt((String) fenetre.getTable().getValueAt(fenetre.getTable().getSelectedRow(), 0))));
		    }
    	
		});
		  
		dialog1 = new JDialog();
		dialog1.setSize(new Dimension(210,200));
		dialog1.setLocationRelativeTo(null);
		JPanel panel1 = new JPanel(new BorderLayout());
		JPanel bouton1 = new JPanel(new FlowLayout());
		textNote.setPreferredSize(new Dimension(200,100));
		ajouterNote.setPreferredSize(new Dimension(200,30));
		note.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex=fenetre.getTable().getSelectedRow();
				int noteGiven =Integer.parseInt(fenetre.getTable().getValueAt(rowIndex, 5).toString());
				textNote.setText(Integer.toString(noteGiven));
		    	bouton1.add(ajouterNote);
		    	panel1.add(textNote,BorderLayout.NORTH);
				panel1.add(bouton1,BorderLayout.CENTER);
				dialog1.add(panel1);
		        dialog1.setVisible(true);
		        ajouterNote.addActionListener(new BoutonAjouterNoteBiblio(popup,fenetre, dbName,userName,pass, Integer.parseInt((String) fenetre.getTable().getValueAt(fenetre.getTable().getSelectedRow(), 0))));
			}
			
		});
		dialog2 = new JDialog();
		dialog2.setSize(new Dimension(210,200));
		dialog2.setLocationRelativeTo(null);
		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel bouton2 = new JPanel(new FlowLayout());
		textBookmark.setPreferredSize(new Dimension(200,100));
		ajouterBookmark.setPreferredSize(new Dimension(200,30));
		bookmark.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int rowIndex=fenetre.getTable().getSelectedRow();
				String bookmarkGiven =(String)fenetre.getTable().getValueAt(rowIndex,4);
				textBookmark.setText(bookmarkGiven);
		    	bouton2.add(ajouterBookmark);
		    	panel2.add(textBookmark,BorderLayout.NORTH);
				panel2.add(bouton2,BorderLayout.CENTER);
				dialog2.add(panel2);
		        dialog2.setVisible(true);
		        ajouterBookmark.addActionListener(new BoutonAjouterBookmarkBiblio(popup,fenetre, dbName,userName,pass, Integer.parseInt((String) fenetre.getTable().getValueAt(fenetre.getTable().getSelectedRow(), 0))));
			
			}		
		});
		
		dialog3 = new JDialog();
		dialog3.setSize(new Dimension(210,200));
		dialog3.setLocationRelativeTo(null);
		JPanel panel3 = new JPanel(new BorderLayout());
		JPanel bouton3 = new JPanel(new FlowLayout());
		textEtat.setPreferredSize(new Dimension(200,100));
		ajouterEtat.setPreferredSize(new Dimension(200,30));
		etat.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int rowIndex=fenetre.getTable().getSelectedRow();
				String etatGiven =(String)fenetre.getTable().getValueAt(rowIndex, 3);
				textEtat.setText(etatGiven);
		    	bouton3.add(ajouterEtat);
		    	panel3.add(textEtat,BorderLayout.NORTH);
				panel3.add(bouton3,BorderLayout.CENTER);
				dialog3.add(panel3);
		        dialog3.setVisible(true);
		        ajouterEtat.addActionListener(new BoutonAjouterEtatBiblio(popup,fenetre, dbName,userName,pass, Integer.parseInt((String) fenetre.getTable().getValueAt(fenetre.getTable().getSelectedRow(), 0))));
			
			}		
		});
		recomandationSerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int rowIndex=fenetre.getTable().getSelectedRow();
				int id=Integer.parseInt((String)fenetre.getTable().getValueAt(rowIndex, 0));
				try {
					fenetre.ContentPanelRecomandationSerie(id);
				} catch (JSONException | NoSuchAlgorithmException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		
		});
		
		recomandationAuteur.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int rowIndex=fenetre.getTable().getSelectedRow();
				String name=(String)fenetre.getTable().getValueAt(rowIndex, 2);
				try {
					fenetre.ContentPanelRecomandationAuteur(name);
				} catch (JSONException | NoSuchAlgorithmException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		
		});
		
		add(com);
		add(note);
		add(bookmark);
		add(etat);
		add(recomandationSerie);
		add(recomandationAuteur);
	}
	public JTextArea getTextNote() {
		return textNote;
	}
	public JTextArea getTextBookmark() {
		return textBookmark;
	}
	public JTextArea getTextEtat() {
		return textEtat;
	}
	public JTextArea getTextCom() {
		return textCom;
	}	

}
