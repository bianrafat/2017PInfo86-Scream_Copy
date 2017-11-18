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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.json.JSONException;

import fr.ujm.tse.Scream.Controller.BoutonMenuComics;
import fr.ujm.tse.Scream.Controller.BoutonMenuPersonnage;
import fr.ujm.tse.Scream.Controller.BoutonRechercherComics;
import fr.ujm.tse.Scream.Controller.BoutonRechercherPerso;
import fr.ujm.tse.Scream.Controller.BoutonRetour;
import fr.ujm.tse.Scream.Controller.BoutonValideComics;
import fr.ujm.tse.Scream.Model.Comics;
import fr.ujm.tse.Scream.Model.Parse;
import fr.ujm.tse.Scream.Model.Personnage;

public class Fenetre  extends JFrame {
	
	private JTextField champPerso = new JTextField();
	private JTextField champComics = new JTextField();
	private JTextField champComicsValide = new JTextField();
	private JEditorPane reponsePerso = new JTextPane();
	private JEditorPane reponseComics = new JTextPane();
	private StyledDocument sDoc = (StyledDocument)reponsePerso.getDocument();
	private StyledDocument sDoc1 = (StyledDocument)reponseComics.getDocument();
	private JLabel img=new JLabel() ;
	private String champStartComics;
	



	public JTextField getChampPerso() {
		return champPerso;
	}

	public Fenetre(){
		super();
	}
	
	public void  run() throws BadLocationException {
		this.build();//On initialise notre fenêtre
	}
	private void build() throws BadLocationException{
		setTitle("Marvel"); //On donne un titre à l'application
		setSize(800,600); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		//setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
	
		setContentPane(buildContentPane());
	}
	
	/**
	 * construit le menu principal de l'application.
	 * @throws BadLocationException 
	 * 
	 */
	
	public JPanel buildContentPane() throws BadLocationException{
		
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
		JButton boutonComics = new JButton(new BoutonMenuComics("rechercher un Comics ",this));
		boutonComics.setMaximumSize(new Dimension(300, 50));
		boutonComics.setMinimumSize(new Dimension(200, 50));
		boutonComics.setPreferredSize(new Dimension(200, 50));
		boutonComics.setFont(fontMenu);
		boutonComics.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(boutonComics);
		
		
		// pour le bouton retour 
		sDoc.remove(0, sDoc.getLength());
		sDoc1.remove(0, sDoc1.getLength());
		img.setIcon(new ImageIcon());;
		champPerso.setText("");
		champComics.setText("");
		champComicsValide.setText("");
		
		
		return panel2;
	}
	
	/**
	 * 
	 * interface affichait quand on clique sur le bouton "Recherche un personnage"
	 */
	
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
		reponsePerso.setBackground(new Color(236, 248, 254));
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panelGeneral.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		panelEast.setLayout(new FlowLayout());
		
		reponsePerso.setEditable(false);
		JScrollPane scrollingArea = new JScrollPane(reponsePerso);
		scrollingArea.getHorizontalScrollBar().setUnitIncrement(10);
		
		JLabel label = new JLabel("Entrer le nom du personnage:");
		champPerso.setMaximumSize(new Dimension(200,30));
		champPerso.setMinimumSize(new Dimension(100,30));
		champPerso.setPreferredSize(new Dimension(200,30));   
		
		panelWest.setMaximumSize(new Dimension(400,400));
		panelWest.setMinimumSize(new Dimension(250,250));
		panelWest.setPreferredSize(new Dimension(350,350));
		JButton recherche = new JButton(new BoutonRechercherPerso("Rechercher",this));
		
		JButton retour = new JButton(new BoutonRetour("Retour",this));
		panelEast.add(Box.createRigidArea(new Dimension(10, 0)));
	
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
	
	/**
	 * interface lorsqu'on clique sur le bouton " recherche un comic"
	 */
	
	public void boutonComics(){
		JPanel panelGeneral = new JPanel();
		JPanel panelNorth= new JPanel();
		JPanel panel = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelSouth = new JPanel();
		
		panelSouth.setBackground(new Color(236, 248, 254));
		panelWest.setBackground(new Color(236, 248, 254));
		panel.setBackground(new Color(236, 248, 254));
		panelNorth.setBackground(new Color(236, 248, 254));
		panelGeneral.setBackground(new Color(236, 248, 254));
		reponseComics.setBackground(new Color(236, 248, 254));
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panelGeneral.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		panelSouth.setLayout(new FlowLayout());
		
		reponseComics.setEditable(false);
		JScrollPane scrollingArea = new JScrollPane(reponseComics);
		scrollingArea.getHorizontalScrollBar().setUnitIncrement(10);
		
		JLabel label = new JLabel("Entrez un titre de Comics (le debut ou le titre complet) :");
		champComics.setMaximumSize(new Dimension(200,30));
		champComics.setMinimumSize(new Dimension(100,30));
		champComics.setPreferredSize(new Dimension(200,30));   
		
		JButton recherche = new JButton(new BoutonRechercherComics("Rechercher",this));
		JButton retour = new JButton(new BoutonRetour("Retour",this));

		panelWest.add(Box.createRigidArea(new Dimension(200, 0)));
		
		JLabel labelValidate = new JLabel("Entrez le numero de votre Comics :");
		champComicsValide.setMaximumSize(new Dimension(200,30));
		champComicsValide.setMinimumSize(new Dimension(100,30));
		champComicsValide.setPreferredSize(new Dimension(200,30));   
		
		JButton valider = new JButton( new BoutonValideComics("Valider",this));
		
	
		panelNorth.add(label);
		panelNorth.add(champComics);
		panelNorth.add(recherche);
		panelNorth.add(retour);
		panel.add(reponseComics);
		
		panelSouth.add(labelValidate);
		panelSouth.add(champComicsValide);
		panelSouth.add(valider);
		
		panelGeneral.add(panelNorth,BorderLayout.NORTH);
		panelGeneral.add(panel,BorderLayout.CENTER);
		panelGeneral.add(panelWest,BorderLayout.WEST);
		panelGeneral.add(panelSouth,BorderLayout.SOUTH);
		this.add(scrollingArea);
		setContentPane(panelGeneral);
		revalidate();
	}
	
	/**
	 * retourne la zone de texte du numéro de comics choisi 
	 * @return champComicsValide
	 */
	public JTextField getChampComicsValide() {
		return champComicsValide;
	}

	
	/**
	 * retourne la zone de texte du debut du titre d'un comics 
	 * @return champComics
	 */
	public JTextField getChampComics() {
		return champComics;
	}
	
	/**
	 * interface  pour afficher les informations d'un comics
	 * @param nb
	 * @param title
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws BadLocationException
	 */
	
	public void ContentPanelComics(int nb, String title) throws JSONException, NoSuchAlgorithmException, IOException, BadLocationException {
		Comics comics = Parse.infoComics(title, nb);
		JEditorPane comicsText = new JTextPane();
		comicsText.setEditable(false);
		StyledDocument contenu = (StyledDocument)comicsText.getDocument();
		Style gras=((JTextPane) comicsText).addStyle("gras", null);
		StyleConstants.setBold(gras, true);
		int pos=0;
		String str="Titre : ";
		
		
		contenu.insertString(pos, str,gras);pos+=str.length();
		contenu.insertString(pos, comics.getTitle()+"\n", null);pos+=comics.getTitle().length()+1;
		str="Description : ";
		contenu.insertString(pos, str,gras);pos+=str.length();
		contenu.insertString(pos, comics.getDescription()+"\n", null);pos+=comics.getDescription().length()+1;
		str="Createurs : \n ";
		contenu.insertString(pos, str,gras);pos+=str.length();
		for (int i=0; i<comics.getCreators2().size(); i++){
			contenu.insertString(pos, comics.getCreators2().get(i)+"\n", null);pos+=comics.getCreators2().get(i).length()+1;
		}
		
		JButton retour = new JButton(new BoutonRetour("Retour",this));
		
		
		JPanel panelGeneral = new JPanel();
		JPanel panel = new JPanel();
		JPanel panelSouth = new JPanel();
		
		panelSouth.setBackground(new Color(236, 248, 254));
		panel.setBackground(new Color(236, 248, 254));
		panelGeneral.setBackground(new Color(236, 248, 254));
		comicsText.setBackground(new Color(236, 248, 254));
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panelGeneral.setLayout(new BorderLayout());
		panelSouth.setLayout(new FlowLayout());
		
		panelSouth.add(retour);
		panel.add(comicsText);
		panelGeneral.add(panel,BorderLayout.CENTER);
		panelGeneral.add(panelSouth,BorderLayout.SOUTH);
		
		setContentPane(panelGeneral);
		revalidate();
		
		
	}
	
	/**
	 *  interface pour afficher les informations d'un personnage
	 * @param str
	 * @throws IOException
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 * @throws BadLocationException
	 */
	public void affichePerso(String str) throws IOException, JSONException, NoSuchAlgorithmException, BadLocationException  { 
		Personnage perso=Parse.infoPersonnage(str);
		Parse.titleComics(perso);
		sDoc.remove(0, sDoc.getLength());
		String ph ="Nom du personnage :   ";
		int pos=0;
		
		Style defaut = ((JTextPane) reponsePerso).getStyle("default");
		Style gras=((JTextPane) reponsePerso).addStyle("gras", null);
		StyleConstants.setBold(gras, true);
	
		sDoc.insertString(pos, ph,gras);pos+=ph.length();
		sDoc.insertString(pos, perso.getName()+ "\n",defaut);pos+=perso.getName().length()+1;
		ph="id :   ";
		sDoc.insertString(pos, ph,gras);pos+=ph.length();
		sDoc.insertString(pos, perso.getId()+ "\n",defaut);pos+=(Integer.toString((perso.getId()))).length()+1;
		ph="Description :   ";
		sDoc.insertString(pos, ph,gras);pos+=ph.length();
		sDoc.insertString(pos, perso.getDescription()+ "\n",defaut);pos+=perso.getDescription().length()+1;
		ph="Comics : \n ";
		sDoc.insertString(pos, ph,gras);pos+=ph.length();
		for(int i=0; i<perso.getComics2().size();i++) {
			ph=(i+1) + " ) ";
			sDoc.insertString(pos, ph,defaut);pos+=ph.length();
			sDoc.insertString(pos, perso.getComics2().get(i)+ "\n",defaut);pos+=perso.getComics2().get(i).length()+1;
		}
		
		
		ImageIcon icon = new ImageIcon(new URL(perso.getLien_image()));
		icon = new ImageIcon(icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
		img.setIcon(icon);
	
		
	}
	
	/**
	 * interface affiche la liste des commics disponible avec le String str
	 * @param str
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws BadLocationException
	 */
	public void afficheListeComics(String str) throws JSONException, NoSuchAlgorithmException, IOException, BadLocationException { 
		Comics comics = Parse.listeComics(str);
		sDoc1.remove(0, sDoc1.getLength());
		Style defaut = ((JTextPane) reponseComics).getStyle("default");
		Style gras=((JTextPane) reponseComics).addStyle("gras", defaut);
		StyleConstants.setBold(gras, true);
		int pos=0;
		String ph="Sur quel comics voulez-vous des informations :  \n ";
		sDoc1.insertString(pos, ph,gras);pos+=ph.length();
		for(int i=0; i<comics.getComics2().size();i++) {
			ph=(i+1) + " ) ";
			sDoc1.insertString(pos, ph,defaut);pos+=ph.length();
			sDoc1.insertString(pos, comics.getComics2().get(i)+ "\n",defaut);pos+=comics.getComics2().get(i).length()+1;
		}
	}
	

	public void setChampStartComics(String champStartComics) {
		this.champStartComics = champStartComics;
	}

	public String getChampStartComics() {
		return champStartComics;
	}
	
}


