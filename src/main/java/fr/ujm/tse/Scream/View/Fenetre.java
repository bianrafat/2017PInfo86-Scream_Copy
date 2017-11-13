package fr.ujm.tse.Scream.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.json.JSONException;

import fr.ujm.tse.Scream.Controller.BoutonMenuPersonnage;
import fr.ujm.tse.Scream.Controller.BoutonRechercherPerso;
import fr.ujm.tse.Scream.Controller.BoutonRetour;
import fr.ujm.tse.Scream.Model.Parse;
import fr.ujm.tse.Scream.Model.Personnage;

public class Fenetre  extends JFrame {
	
	private JTextField champPerso = new JTextField();
	private JTextArea reponsePerso ;
	private JLabel img=new JLabel() ;
	



	public JTextField getChampPerso() {
		return champPerso;
	}

	public Fenetre(){
		super();
	}
	
	public void  run() {
		this.build();//On initialise notre fenêtre
	}
	private void build(){
		setTitle("Marvel"); //On donne un titre à l'application
		setSize(800,600); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		//setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
	
		setContentPane(buildContentPane());
	}
	public JPanel buildContentPane(){
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(236, 248, 254));
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Bienvenue");
		Font font = new Font("Century Schoolbook",Font.BOLD,24);

		Font fontMenu = new Font("Century Schoolbook",Font.BOLD,15);
		label.setFont(font);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(Box.createRigidArea(new Dimension(0, 20)));
		panel2.add(label);
        panel2.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton boutonPersonnage = new JButton(new BoutonMenuPersonnage("rechercher un personnage ",this));
		boutonPersonnage.setMaximumSize(new Dimension(300, 50));
		boutonPersonnage.setMinimumSize(new Dimension(200, 50));
		boutonPersonnage.setPreferredSize(new Dimension(200, 50));
		boutonPersonnage.setFont(fontMenu);
		boutonPersonnage.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(boutonPersonnage);
		
		panel2.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton boutonComics = new JButton("rechercher un Comics ");
		boutonComics.setMaximumSize(new Dimension(300, 50));
		boutonComics.setMinimumSize(new Dimension(200, 50));
		boutonComics.setPreferredSize(new Dimension(200, 50));
		boutonComics.setFont(fontMenu);
		boutonComics.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(boutonComics);
		return panel2;
	}
	
	public void boutonPerso(){
		JPanel panelGeneral = new JPanel();
		JPanel panelNorth= new JPanel();
		JPanel panel = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelEast = new JPanel();
		
		panelEast.setBackground(new Color(236, 248, 254));
		panelWest.setBackground(new Color(236, 248, 254));
		panel.setBackground(new Color(236, 248, 254));
		panelNorth.setBackground(new Color(236, 248, 254));
		panelGeneral.setBackground(new Color(236, 248, 254));
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panelGeneral.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		panelEast.setLayout(new FlowLayout());
		
		JScrollPane scrollingArea = new JScrollPane(reponsePerso);
		scrollingArea.getHorizontalScrollBar().setUnitIncrement(10);
		
		JLabel label = new JLabel("Entrer le nom du personnage:");
		champPerso.setMaximumSize(new Dimension(200,30));
		champPerso.setMinimumSize(new Dimension(100,30));
		champPerso.setPreferredSize(new Dimension(200,30));   
		reponsePerso = new JTextArea("");
		reponsePerso.setEditable(false);
		reponsePerso.setLineWrap(true);
		reponsePerso.setWrapStyleWord(true); 
		reponsePerso.setBackground(new Color(236, 248, 254));
		panelWest.setMaximumSize(new Dimension(400,400));
		panelWest.setMinimumSize(new Dimension(250,250));
		panelWest.setPreferredSize(new Dimension(350,350));
		JButton recherche = new JButton(new BoutonRechercherPerso("Rechercher",this));
		JButton retour = new JButton(new BoutonRetour("Retour",this));
		panelEast.add(Box.createRigidArea(new Dimension(10, 0)));
		
		//img.setSize(panelWest.getWidth()/8,panelWest.getHeight()/8);
	
		panelWest.add(img);
		panelNorth.add(label);
		panelNorth.add(champPerso);
		panelNorth.add(recherche);
		panelNorth.add(retour);
		panel.add(reponsePerso);
		panelGeneral.add(panelNorth,BorderLayout.NORTH);
		panelGeneral.add(panel,BorderLayout.CENTER);
		panelGeneral.add(panelWest,BorderLayout.WEST);
		panelGeneral.add(panelEast,BorderLayout.EAST);
		this.add(scrollingArea);
		setContentPane(panelGeneral);
		revalidate();
	}
	
	
	public void affichePerso(String str) throws IOException, JSONException, NoSuchAlgorithmException  { 
		Personnage perso=Parse.infoPersonnage(str);
		
		reponsePerso.setText("Nom du personnage :   "+ perso.getName()+ "\n");
		reponsePerso.setText(reponsePerso.getText().concat("id :   "+ perso.getId()+"\n"));
		reponsePerso.setText(reponsePerso.getText().concat("Description :   "+ perso.getDescription()+"\n"));
		ImageIcon icon = new ImageIcon(new URL(perso.getLien_image()));
		icon = new ImageIcon(icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
		img.setIcon(icon);
		
		//reponsePerso.setText(reponsePerso.getText().concat(" Lien de l'image de profil: " + perso.getLien_image()+ "\n"));
		reponsePerso.setText(reponsePerso.getText().concat("Comics :  "+"\n"));
		Parse.titleComics(perso);
		for(int i=0; i<perso.getComics2().size();i++) {
			reponsePerso.setText(reponsePerso.getText().concat((i+1)+") "+(String)perso.getComics2().get(i)+"\n"));
		}
		
	
	}
	
	
	
}


