package fr.ujm.tse.Scream.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import fr.ujm.tse.Scream.View.Fenetre;

public class BoutonAddComicsBiblio implements ActionListener {
	private Fenetre fenetre;
	private String dbName;
	private String userName;
	private String pass;
	private int id;
	private String title;
	private String author;
	private int year;
	private String etat;
	private int bookmark;
	private int note;
	private String com;
	
	public BoutonAddComicsBiblio(Fenetre f,String dbName, String userName, String pass, int id, String title, String author, int year, String etat, int bookmark, int note, String com)
	 {
		
		fenetre = f;
		this.dbName=dbName;
		this.userName= userName;
		this.pass=pass;
		this.id=id;
		this.title=title;
		this.author=author;
		this.year=year;
		this.etat=etat;
		this.bookmark=bookmark;
		this.note=note;
		this.com=com;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(Database.insert(dbName, userName,pass, id, title, author, year, etat, bookmark, note, com)) {
			JOptionPane.showMessageDialog(null," ajouter avec succès !");
		}else {
			JOptionPane.showMessageDialog(null," erreur!");
		}
		
	}
	
	
	
	

}
