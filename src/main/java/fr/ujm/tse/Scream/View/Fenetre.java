package fr.ujm.tse.Scream.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.json.JSONException;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import fr.ujm.tse.Scream.Controller.AccessWebWiki;
import fr.ujm.tse.Scream.Controller.BoutonConnexion;
import fr.ujm.tse.Scream.Controller.BoutonDeco;
import fr.ujm.tse.Scream.Controller.BoutonMenuComics;
import fr.ujm.tse.Scream.Controller.BoutonMenuPersonnage;
import fr.ujm.tse.Scream.Controller.BoutonRechercherComics;
import fr.ujm.tse.Scream.Controller.BoutonRechercherPerso;
import fr.ujm.tse.Scream.Controller.BoutonRechercherPersoWiki;
import fr.ujm.tse.Scream.Controller.BoutonRepNon;
import fr.ujm.tse.Scream.Controller.BoutonRepOui;
import fr.ujm.tse.Scream.Controller.BoutonRetour;
import fr.ujm.tse.Scream.Controller.BoutonValideId;
import fr.ujm.tse.Scream.Controller.BoutonValideNewBiblio;
import fr.ujm.tse.Scream.Controller.BoutonWikiData;
import fr.ujm.tse.Scream.Controller.Database;
import fr.ujm.tse.Scream.Model.Comics;
import fr.ujm.tse.Scream.Model.Parse;
import fr.ujm.tse.Scream.Model.ParseWiki;
import fr.ujm.tse.Scream.Model.Personnage;
import fr.ujm.tse.Scream.Model.PersonnageWiki;
import fr.ujm.tse.Scream.Model.SearchWiki;

public class Fenetre extends JFrame {

	private JTextField champPerso = new JTextField();
	private JTextField champComics = new JTextField();
	private JTextField champWikiP = new JTextField();
	private JTextField champBiblio= new JTextField();
	private JTextField champUser= new JTextField();
	private JPasswordField  champMdp= new JPasswordField();
	private String nameBiblio=null;
	private String conUser=null;
	private String conMdp=null;
	private JEditorPane reponsePerso = new JTextPane();
	private StyledDocument sDoc = (StyledDocument) reponsePerso.getDocument();
	private JLabel img = new JLabel();
	private JLabel imgComics = new JLabel();
	private String champStartComics;
	private JList<Object> listeComics = new JList<Object>();
	private JList<Object> listePerso = new JList<Object>();
	private JList<Object> listeWikiP = new JList<Object>();
	private JLabel intro = new JLabel();
	private ParseWiki wiki = new ParseWiki();
	private JButton biblio = new JButton("Ma Bibliotèque");
	private JButton deconnexion = new JButton("Déconnexion");
	private JButton connexion = new JButton("Connexion");
	
	
	public String getNameBiblio() {
		return nameBiblio;
	}


	public void setNameBiblio(String nameBiblio) {
		this.nameBiblio = nameBiblio;
	}

	public String getConUser() {
		return conUser;
	}

	public void setConUser(String conUser) {
		this.conUser = conUser;
	}

	public String getConMdp() {
		return conMdp;
	}

	public void setConMdp(String conMdp) {
		this.conMdp = conMdp;
	}

	public JTextField getChampBiblio() {
		return champBiblio;
	}

	public JTextField getChampUser() {
		return champUser;
	}

	public JTextField getChampMdp() {
		return champMdp;
	}

	public JTextField getChampPerso() {
		return champPerso;
	}

	public Fenetre() {
		super();
	}

	public void run() throws BadLocationException, IOException {
		this.build();// On initialise notre fenêtre
	}

	private void build() throws BadLocationException, IOException {
		setTitle("Marvel"); // On donne un titre à l'application
		setSize(800, 600); // On donne une taille à notre fenêtre
		setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
		// setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à l'application de se fermer lors du clic sur la croix
		ImageIcon icon = new ImageIcon(ImageIO.read(new File("src\\main\\resources\\logo.jpg")));
		
	
		setIconImage(icon.getImage());
		setContentPane(buildContentPane());
	}

	/**
	 * retourne le champ de la recherche wikidata
	 * 
	 * @return champWikiP
	 */
	public JTextField getChampWikiP() {
		return champWikiP;
	}

	/**
	 * construit le menu principal de l'application.
	 * 
	 * @throws BadLocationException
	 * 
	 */

	public JPanel buildContentPane() throws BadLocationException {

		GridLayout param = new GridLayout(3, 2);
		param.setHgap(70); // Cinq pixels d'espace entre les colonnes (H comme Horizontal)
		param.setVgap(20); // Cinq pixels d'espace entre les lignes (V comme Vertical)

		JPanel panel2 = new JPanel();
		JPanel panelG = new JPanel();
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		center.setBackground(new Color(236, 248, 254));
		north.setBackground(new Color(236, 248, 254));
		panel2.setBackground(new Color(236, 248, 254));
		panelG.setBackground(new Color(236, 248, 254));
		panelG.setLayout(new BorderLayout());
		panel2.setLayout(new BorderLayout());
		north.setLayout(new FlowLayout());
		center.setLayout(param);
		
		JLabel label = new JLabel("Bienvenue");
		Font font = new Font("Century Schoolbook", Font.BOLD, 24);
		Font fontMenu = new Font("Century Schoolbook", Font.BOLD, 15);
		try {
			JLabel background = new JLabel();
			ImageIcon icon = new ImageIcon(ImageIO.read(new File("src\\main\\resources\\Marvel.png")));
			background.setIcon(icon);
			/*
			 * JLabel background2 = new JLabel(); ImageIcon icon2 = new
			 * ImageIcon(ImageIO.read(new File("src\\main\\resources\\title_marvel.png")));
			 * background2.setIcon(icon2); panelG.add(background2,BorderLayout.NORTH);
			 */

			panelG.add(background, BorderLayout.SOUTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		label.setFont(font);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		north.add(label);
		panelG.add(north, BorderLayout.NORTH);
		JButton boutonPersonnage = new JButton(new BoutonMenuPersonnage("Rechercher un personnage ", this));
		/*
		 * boutonPersonnage.setMaximumSize(new Dimension(300, 50));
		 * boutonPersonnage.setMinimumSize(new Dimension(50, 50));
		 * boutonPersonnage.setPreferredSize(new Dimension(150, 40));
		 */
		boutonPersonnage.setFont(fontMenu);
		// boutonPersonnage.setAlignmentX(Component.LEFT_ALIGNMENT);
		center.add(boutonPersonnage);

		// panel2.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton boutonComics = new JButton(new BoutonMenuComics("Rechercher un Comics ", this));
		/*
		 * boutonComics.setMaximumSize(new Dimension(300, 50));
		 * boutonComics.setMinimumSize(new Dimension(200, 50));
		 * boutonComics.setPreferredSize(new Dimension(200, 50));
		 */
		boutonComics.setFont(fontMenu);
		// boutonComics.setAlignmentX(Component.CENTER_ALIGNMENT);
		center.add(boutonComics);

		JButton wikiData = new JButton(new BoutonWikiData("Rechercher Wikidata", this));
		wikiData.setFont(fontMenu);
		center.add(wikiData);
		
		connexion.setFont(fontMenu);
		
		deconnexion.setFont(fontMenu);
		biblio.setFont(fontMenu);
		if(nameBiblio==null) {
			biblio.setVisible(false);
			deconnexion.setVisible(false);
			connexion.setVisible(true);
			center.add(connexion);
			connexion.addActionListener(new BoutonConnexion(this));
		}else {
			biblio.setVisible(true);
			deconnexion.setVisible(true);
			connexion.setVisible(false);
			deconnexion.addActionListener(new BoutonDeco(this));

			center.add(deconnexion);
			center.add(biblio);
			
		}

		
		
	
		panel2.add(Box.createRigidArea(new Dimension(0, 50)), BorderLayout.NORTH);
		panel2.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.WEST);
		panel2.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.EAST);
		panel2.add(center, BorderLayout.CENTER);
		panelG.add(panel2, BorderLayout.CENTER);
		// pour le bouton retour
		sDoc.remove(0, sDoc.getLength());
		img.setIcon(new ImageIcon());
		;
		champPerso.setText("");
		champComics.setText("");
		champWikiP.setText("");
		champBiblio.setText("");
		champUser.setText("");
		champMdp.setText("");
		Object[] listeDefault = new Object[] { "" };
		intro.setText("");
		listePerso.setListData(listeDefault);
		listeComics.setListData(listeDefault);
		listeWikiP.setListData(listeDefault);
		return panelG;
	}

	public JButton getBiblio() {
		return biblio;
	}


	public void setBiblio(JButton biblio) {
		this.biblio = biblio;
	}


	public JButton getConnexion() {
		return connexion;
	}


	/**
	 * 
	 * interface affichait quand on clique sur le bouton "Recherche un personnage"
	 */

	public void boutonPerso() {
		JPanel panelGeneral = new JPanel();
		JPanel panelNorth = new JPanel();
		JPanel panel = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelEast = new JPanel();

		panelEast.setOpaque(false);
		panelWest.setOpaque(false);
		panel.setOpaque(false);
		panelNorth.setOpaque(false);
		panelGeneral.setBackground(new Color(236, 248, 254));
		reponsePerso.setOpaque(false);
		listeComics.setBackground(new Color(236, 248, 254));

		panel.setLayout(new BorderLayout());
		panelGeneral.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		panelEast.setLayout(new FlowLayout());

		reponsePerso.setEditable(false);
		JScrollPane scrollingArea = new JScrollPane(reponsePerso);
		scrollingArea.getHorizontalScrollBar().setUnitIncrement(10);

		JLabel label = new JLabel("Entrer le nom du personnage:");
		champPerso.setMaximumSize(new Dimension(200, 30));
		champPerso.setMinimumSize(new Dimension(100, 30));
		champPerso.setPreferredSize(new Dimension(200, 30));

		panelWest.setMaximumSize(new Dimension(400, 400));
		panelWest.setMinimumSize(new Dimension(250, 250));
		panelWest.setPreferredSize(new Dimension(350, 350));
		JButton recherche = new JButton("Rechercher");
		champPerso.addActionListener(new BoutonRechercherPerso(this));
		recherche.addActionListener(new BoutonRechercherPerso(this));
		JButton retour = new JButton(new BoutonRetour("Retour", this));

		listeComics.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					int index = list.locationToIndex(evt.getPoint());
					try {
						ContentPanelComics(listeComics.getSelectedIndex() + 1, champPerso.getText());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		panelEast.add(Box.createRigidArea(new Dimension(10, 0)));
		panelWest.add(img);
		panelNorth.add(label);
		panelNorth.add(champPerso);
		panelNorth.add(recherche);
		panelNorth.add(retour);

		panel.add(reponsePerso, BorderLayout.NORTH);
		panel.add(listeComics, BorderLayout.CENTER);

		panelGeneral.add(panelNorth, BorderLayout.NORTH);
		panelGeneral.add(panel, BorderLayout.CENTER);
		panelGeneral.add(panelWest, BorderLayout.WEST);
		panelGeneral.add(panelEast, BorderLayout.EAST);
		this.add(scrollingArea);
		setContentPane(panelGeneral);
		revalidate();
	}

	/**
	 * fonction utiliser quand nous cliquons dans le menu principale sur "connexion"
	 */

	public void boutonConnexion() {
		JPanel panelGeneral = new JPanel();
		JPanel panelNorth = new JPanel();
		JPanel panel = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelEast = new JPanel();
		JPanel panelBouton = new JPanel();
		JPanel panelSouth = new JPanel();

		Font font = new Font("Century Schoolbook", Font.BOLD, 24);
		Font fontMenu = new Font("Century Schoolbook", Font.BOLD, 15);

		panelEast.setOpaque(false);
		panelSouth.setOpaque(false);
		panelBouton.setOpaque(false);
		panelWest.setOpaque(false);
		panel.setOpaque(false);
		panelNorth.setOpaque(false);
		panelGeneral.setBackground(new Color(236, 248, 254));

		panelEast.setLayout(new FlowLayout());
		panelSouth.setLayout(new FlowLayout());
		panel.setLayout(new BorderLayout());
		panelGeneral.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		panelBouton.setLayout(new FlowLayout());

		JLabel label = new JLabel("Connexion");
		JLabel question = new JLabel("Êtes vous déjà inscrit ?");
		label.setFont(font);
		question.setFont(fontMenu);
		JButton repOui = new JButton(new BoutonRepOui("Oui",this));
		repOui.setPreferredSize(new Dimension(120, 40));
		JButton repNon = new JButton(new BoutonRepNon("Non",this));
		repNon.setPreferredSize(new Dimension(120, 40));
		JButton retour = new JButton(new BoutonRetour("Retour", this));

		panelNorth.add(label);
		panelBouton.add(question);
		panelBouton.add(repOui);
		panelBouton.add(repNon);
		panel.add(Box.createRigidArea(new Dimension(10, 50)), BorderLayout.NORTH);
		panel.add(panelBouton, BorderLayout.CENTER);

		panelSouth.add(retour);
		panelWest.add(Box.createRigidArea(new Dimension(250, 50)));
		panelEast.add(Box.createRigidArea(new Dimension(250, 50)));
		panelGeneral.add(panelNorth, BorderLayout.NORTH);
		panelGeneral.add(panel, BorderLayout.CENTER);
		panelGeneral.add(panelWest, BorderLayout.WEST);
		panelGeneral.add(panelEast, BorderLayout.EAST);
		panelGeneral.add(panelSouth, BorderLayout.SOUTH);
		setContentPane(panelGeneral);
		revalidate();
	}

	/**
	 * fonction quand l'utilisateur veux créer sa nouvelle biblioteque
	 */
	public void boutonRepNon() {
		JPanel panelGeneral = new JPanel();
		JPanel panelNorth = new JPanel();
		JPanel panel = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelEast = new JPanel();
		JPanel panelBouton = new JPanel();
		JPanel panelSouth = new JPanel();

		Font font = new Font("Century Schoolbook", Font.BOLD, 24);
		Font fontMenu = new Font("Century Schoolbook", Font.BOLD, 12);

		panelEast.setOpaque(false);
		panelSouth.setOpaque(false);
		panelBouton.setOpaque(false);
		panelWest.setOpaque(false);
		panel.setOpaque(false);
		panelNorth.setOpaque(false);
		panelGeneral.setBackground(new Color(236, 248, 254));

		panelEast.setLayout(new FlowLayout());
		panelSouth.setLayout(new FlowLayout());
		panel.setLayout(new BorderLayout());
		panelGeneral.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		panelBouton.setLayout(new FlowLayout());

		JLabel label = new JLabel("Connexion");
		JLabel labBiblio = new JLabel("Veuillez renseigner un nom à votre bibliothèque:");
		JLabel labUser = new JLabel("Veuillez renseigner un nom d'utilisateur:");
		JLabel labMdp = new JLabel("Maintenant un mot de passe:");
		label.setFont(font);
		labBiblio.setFont(fontMenu);
		labUser.setFont(fontMenu);
		labMdp.setFont(fontMenu);
		
		champBiblio.setPreferredSize(new Dimension(210, 30));
		champUser.setPreferredSize(new Dimension(210, 30));
		champMdp.setPreferredSize(new Dimension(210, 30));
		JButton conf = new JButton(new BoutonValideNewBiblio("Valider",this));
		conf.setPreferredSize(new Dimension(130, 40));

		// champPerso.addActionListener(new BoutonRechercherPerso(this));
		// recherche.addActionListener(new BoutonRechercherPerso(this));
		JButton retour = new JButton(new BoutonRetour("Retour", this));
		retour.setPreferredSize(new Dimension(130, 40));

		panelNorth.add(label);
		panelBouton.add(labBiblio);
		panelBouton.add(champBiblio);
		panelBouton.add(labUser);
		panelBouton.add(champUser);
		panelBouton.add(labMdp);
		panelBouton.add(champMdp);
		panelBouton.add(conf);
		panelBouton.add(retour);

		panel.add(Box.createRigidArea(new Dimension(10, 50)), BorderLayout.NORTH);
		panel.add(panelBouton, BorderLayout.CENTER);

		panelWest.add(Box.createRigidArea(new Dimension(210, 50)));
		panelEast.add(Box.createRigidArea(new Dimension(210, 50)));
		panelGeneral.add(panelNorth, BorderLayout.NORTH);
		panelGeneral.add(panel, BorderLayout.CENTER);
		panelGeneral.add(panelWest, BorderLayout.WEST);
		panelGeneral.add(panelEast, BorderLayout.EAST);
		setContentPane(panelGeneral);
		revalidate();
	}

	

	/**
	 * fonction quand l'utilisateur veux accéder à sa biblioteque
	 */
	public void boutonRepOui() {
		JPanel panelGeneral = new JPanel();
		JPanel panelNorth = new JPanel();
		JPanel panel = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelEast = new JPanel();
		JPanel panelBouton = new JPanel();
		JPanel panelSouth = new JPanel();

		Font font = new Font("Century Schoolbook", Font.BOLD, 24);
		Font fontMenu = new Font("Century Schoolbook", Font.BOLD, 12);

		panelEast.setOpaque(false);
		panelSouth.setOpaque(false);
		panelBouton.setOpaque(false);
		panelWest.setOpaque(false);
		panel.setOpaque(false);
		panelNorth.setOpaque(false);
		panelGeneral.setBackground(new Color(236, 248, 254));

		panelEast.setLayout(new FlowLayout());
		panelSouth.setLayout(new FlowLayout());
		panel.setLayout(new BorderLayout());
		panelGeneral.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		panelBouton.setLayout(new FlowLayout());

		JLabel label = new JLabel("Connexion");
		JLabel labBiblio = new JLabel("Entrez le nom de votre bibliothèque:");
		JLabel labUser = new JLabel("Entrez le nom d'utilisateur:");
		JLabel labMdp = new JLabel("Entrez votre mot de passe:");
		label.setFont(font);
		labBiblio.setFont(fontMenu);
		labUser.setFont(fontMenu);
		labMdp.setFont(fontMenu);
		
		champBiblio.setPreferredSize(new Dimension(210, 30));
		champUser.setPreferredSize(new Dimension(210, 30));
		champMdp.setPreferredSize(new Dimension(210, 30));
		JButton conf = new JButton(new BoutonValideId("Valider",this));
		conf.setPreferredSize(new Dimension(130, 40));

		// champPerso.addActionListener(new BoutonRechercherPerso(this));
		// recherche.addActionListener(new BoutonRechercherPerso(this));
		JButton retour = new JButton(new BoutonRetour("Retour", this));
		retour.setPreferredSize(new Dimension(130, 40));

		panelNorth.add(label);
		panelBouton.add(labBiblio);
		panelBouton.add(champBiblio);
		panelBouton.add(labUser);
		panelBouton.add(champUser);
		panelBouton.add(labMdp);
		panelBouton.add(champMdp);
		panelBouton.add(conf);
		panelBouton.add(retour);

		panel.add(Box.createRigidArea(new Dimension(10, 50)), BorderLayout.NORTH);
		panel.add(panelBouton, BorderLayout.CENTER);

		panelWest.add(Box.createRigidArea(new Dimension(210, 50)));
		panelEast.add(Box.createRigidArea(new Dimension(210, 50)));
		panelGeneral.add(panelNorth, BorderLayout.NORTH);
		panelGeneral.add(panel, BorderLayout.CENTER);
		panelGeneral.add(panelWest, BorderLayout.WEST);
		panelGeneral.add(panelEast, BorderLayout.EAST);
		setContentPane(panelGeneral);
		revalidate();
	}

	
	/**
	 * interface lorsqu'on clique sur le bouton " recherche Wikidata"
	 */

	public void boutonWikidata() {
		JPanel panelGeneral = new JPanel();
		JPanel panelNorth = new JPanel();
		JPanel panel = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(new Color(236, 248, 254));
		panelWest.setBackground(new Color(236, 248, 254));
		panel.setBackground(new Color(236, 248, 254));
		panelNorth.setBackground(new Color(236, 248, 254));
		panelGeneral.setBackground(new Color(236, 248, 254));
		listeWikiP.setBackground(new Color(236, 248, 254));

		panel.setLayout(new BorderLayout());
		panelGeneral.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		panelSouth.setLayout(new FlowLayout());

		JLabel label = new JLabel("Entrez le nom d'un personnage:");
		champWikiP.setMaximumSize(new Dimension(200, 30));
		champWikiP.setMinimumSize(new Dimension(100, 30));
		champWikiP.setPreferredSize(new Dimension(200, 30));
		champWikiP.addActionListener(new BoutonRechercherPersoWiki(this));
		JButton recherche = new JButton("Rechercher");
		recherche.addActionListener(new BoutonRechercherPersoWiki(this));
		JButton retour = new JButton(new BoutonRetour("Retour", this));

		listeWikiP.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					int index = list.locationToIndex(evt.getPoint());
					try {
						ContentPanelWikiP(list.getSelectedIndex());
					} catch (JSONException | NoSuchAlgorithmException | IOException | BadLocationException
							| MediaWikiApiErrorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		intro.setAlignmentX(Component.LEFT_ALIGNMENT);

		panelNorth.add(label);
		panelNorth.add(champWikiP);
		panelNorth.add(recherche);
		panelNorth.add(retour);

		panel.add(intro, BorderLayout.NORTH);

		panel.add(Box.createRigidArea(new Dimension(200, 20)), BorderLayout.WEST);
		panel.add(listeWikiP, BorderLayout.CENTER);

		panelGeneral.add(panelNorth, BorderLayout.NORTH);
		panelGeneral.add(panel, BorderLayout.CENTER);
		panelGeneral.add(panelWest, BorderLayout.WEST);
		panelGeneral.add(panelSouth, BorderLayout.SOUTH);

		setContentPane(panelGeneral);
		revalidate();
	}

	public void boutonComics() {
		JPanel panelGeneral = new JPanel();
		JPanel panelNorth = new JPanel();
		JPanel panel = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(new Color(236, 248, 254));
		panelWest.setBackground(new Color(236, 248, 254));
		panel.setBackground(new Color(236, 248, 254));
		panelNorth.setBackground(new Color(236, 248, 254));
		panelGeneral.setBackground(new Color(236, 248, 254));
		listeComics.setBackground(new Color(236, 248, 254));

		panel.setLayout(new BorderLayout());
		panelGeneral.setLayout(new BorderLayout());
		panelNorth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		panelSouth.setLayout(new FlowLayout());

		JLabel label = new JLabel("Entrez un titre de Comics (le debut uniquement) :");
		champComics.setMaximumSize(new Dimension(200, 30));
		champComics.setMinimumSize(new Dimension(100, 30));
		champComics.setPreferredSize(new Dimension(200, 30));
		champComics.addActionListener(new BoutonRechercherComics(this));
		JButton recherche = new JButton("Rechercher");
		recherche.addActionListener(new BoutonRechercherComics(this));
		JButton retour = new JButton(new BoutonRetour("Retour", this));

		listeComics.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					int index = list.locationToIndex(evt.getPoint());
					try {
						ContentPanelComics(listeComics.getSelectedIndex() + 1, champStartComics);
					} catch (JSONException | NoSuchAlgorithmException | IOException | BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		intro.setAlignmentX(Component.LEFT_ALIGNMENT);

		panelNorth.add(label);
		panelNorth.add(champComics);
		panelNorth.add(recherche);
		panelNorth.add(retour);

		panel.add(intro, BorderLayout.NORTH);

		panel.add(Box.createRigidArea(new Dimension(200, 20)), BorderLayout.WEST);
		panel.add(listeComics, BorderLayout.CENTER);

		panelGeneral.add(panelNorth, BorderLayout.NORTH);
		panelGeneral.add(panel, BorderLayout.CENTER);
		panelGeneral.add(panelWest, BorderLayout.WEST);
		panelGeneral.add(panelSouth, BorderLayout.SOUTH);

		setContentPane(panelGeneral);
		revalidate();
	}

	/**
	 * retourne le texte a afficher apres la recherche de comics
	 * 
	 * @return intro
	 */

	public JLabel getIntro() {
		return intro;
	}

	/**
	 * retourne la zone de texte du debut du titre d'un comics
	 * 
	 * @return champComics
	 */
	public JTextField getChampComics() {
		return champComics;
	}

	/**
	 * interface pour afficher les informations d'un comics
	 * 
	 * @param nb
	 * @param title
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws BadLocationException
	 */

	public void ContentPanelComics(int nb, String title)
			throws JSONException, NoSuchAlgorithmException, IOException, BadLocationException {
		Comics comics = Parse.infoComics(title, nb,0);
		JEditorPane comicsText = new JTextPane();
		comicsText.setEditable(false);

		ImageIcon icon = new ImageIcon(new URL(comics.getLien_image()));
		icon = new ImageIcon(icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
		imgComics.setIcon(icon);

		StyledDocument contenu = (StyledDocument) comicsText.getDocument();
		Style gras = ((JTextPane) comicsText).addStyle("gras", null);
		StyleConstants.setBold(gras, true);
		int pos = 0;
		String str = "Titre : ";

		contenu.insertString(pos, str, gras);
		pos += str.length();
		contenu.insertString(pos, comics.getTitle() + "\n", null);
		pos += comics.getTitle().length() + 1;
		str = "Description : ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		contenu.insertString(pos, comics.getDescription() + "\n", null);
		pos += comics.getDescription().length() + 1;
		str = "Createurs : \n ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		for (int i = 0; i < comics.getCreators2().size(); i++) {
			contenu.insertString(pos, comics.getCreators2().get(i) + "\n", null);
			pos += comics.getCreators2().get(i).length() + 1;
		}
		str = "Personnages Marvel: \n ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		Object[] lperso = new Object[comics.getCharacters2().size()];
		for (int i = 0; i < comics.getCharacters2().size(); i++) {
			lperso[i] = comics.getCharacters2().get(i);
		}
		listePerso.setListData(lperso);
		listePerso.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					int index = list.locationToIndex(evt.getPoint());
					try {
						affichePerso((String) list.getSelectedValue());
						champPerso.setText((String) list.getSelectedValue());
						boutonPerso();
					} catch (JSONException | NoSuchAlgorithmException | IOException | BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		JButton retour = new JButton(new BoutonRetour("Retour", this));

		JPanel panelGeneral = new JPanel();
		JPanel panel = new JPanel();
		JPanel panelSouth = new JPanel();
		JPanel panelWest = new JPanel();

		listePerso.setBackground(new Color(236, 248, 254));
		panelWest.setBackground(new Color(236, 248, 254));
		panelSouth.setBackground(new Color(236, 248, 254));
		panel.setBackground(new Color(236, 248, 254));
		panelGeneral.setBackground(new Color(236, 248, 254));
		comicsText.setBackground(new Color(236, 248, 254));

		panel.setLayout(new BorderLayout());
		panelGeneral.setLayout(new BorderLayout());
		panelSouth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());

		panelWest.add(imgComics);
		panelSouth.add(retour);
		panel.add(comicsText, BorderLayout.NORTH);
		panel.add(listePerso, BorderLayout.CENTER);
		panelGeneral.add(panel, BorderLayout.CENTER);
		panelGeneral.add(panelSouth, BorderLayout.SOUTH);
		panelGeneral.add(panelWest, BorderLayout.WEST);

		setContentPane(panelGeneral);
		revalidate();

	}

	public void ContentPanelWikiP(int i) throws JSONException, NoSuchAlgorithmException, IOException,
			BadLocationException, MediaWikiApiErrorException {

		wiki.infoWikipersonnagetwo(i + 1);
		JEditorPane wikiText = new JTextPane();
		wikiText.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		wikiText.setEditable(false);
		PersonnageWiki perso = wiki.getPersoWiki();

		StyledDocument contenu = (StyledDocument) wikiText.getDocument();

		Style gras = ((JTextPane) wikiText).addStyle("gras", null);
		StyleConstants.setBold(gras, true);
		int pos = 0;
		String str = "Nom : ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		contenu.insertString(pos, perso.getName() + "\n", null);
		pos += perso.getName().length() + 1;
		str = "Description : ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		contenu.insertString(pos, perso.getDescription() + "\n", null);
		pos += perso.getDescription().length() + 1;
		str = "Surnom(s): \n ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		for (int i1 = 0; i1 < perso.getNicknames2().size(); i1++) {
			contenu.insertString(pos, perso.getNicknames2().get(i1) + "\n", null);
			pos += perso.getNicknames2().get(i1).length() + 1;
		}
		str = "Genre: ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		contenu.insertString(pos, perso.getGender() + "\n", null);
		pos += perso.getGender().length() + 1;
		str = "Pays: ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		contenu.insertString(pos, perso.getCountry() + "\n", null);
		pos += perso.getCountry().length() + 1;
		str = "Père: ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		contenu.insertString(pos, perso.getFather() + "\n", null);
		pos += perso.getFather().length() + 1;
		str = "Mère: ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		contenu.insertString(pos, perso.getMother() + "\n", null);
		pos += perso.getMother().length() + 1;
		str = "Beau-parent: ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		contenu.insertString(pos, perso.getStepparent() + "\n", null);
		pos += perso.getStepparent().length() + 1;
		str = "Frères et soeurs: \n";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		for (int i1 = 0; i1 < perso.getSiblings2().size(); i1++) {
			contenu.insertString(pos, perso.getSiblings2().get(i1) + "\n", null);
			pos += perso.getSiblings2().get(i1).length() + 1;
		}
		str = "Nom de naissance: ";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		contenu.insertString(pos, perso.getBirth_name() + "\n", null);
		pos += perso.getBirth_name().length() + 1;

		str = "Auteurs: \n";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		for (int i1 = 0; i1 < perso.getCreators2().size(); i1++) {
			contenu.insertString(pos, perso.getCreators2().get(i1) + "\n", null);
			pos += perso.getCreators2().get(i1).length() + 1;
		}
		str = "Acteurs ayant interprété ce role: \n";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		for (int i1 = 0; i1 < perso.getPerformers2().size(); i1++) {
			contenu.insertString(pos, perso.getPerformers2().get(i1) + "\n", null);
			pos += perso.getPerformers2().get(i1).length() + 1;
		}
		str = "Pouvoir(s): \n";
		contenu.insertString(pos, str, gras);
		pos += str.length();
		for (int i1 = 0; i1 < perso.getSuperhumain_ability2().size(); i1++) {
			contenu.insertString(pos, perso.getSuperhumain_ability2().get(i1) + "\n", null);
			pos += perso.getSuperhumain_ability2().get(i1).length() + 1;
		}
		JLabel website = new JLabel();
		//website.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		//website.setEditable(false);
		//website.setPreferredSize(new Dimension(20, 20));
		website.setText("<html> Pour plus d'information, consultez la page wikidata du personnage : <a href=\"\">"+perso.getWikidata_page()+"</a></html>");
		website.setCursor(new Cursor(Cursor.HAND_CURSOR));
		AccessWebWiki.goWebsite(website,perso.getWikidata_page());
		//website.setVisible(true);
		
		JLabel website1 = new JLabel();
		//website.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		//website.setEditable(false);
		//website1.setPreferredSize(new Dimension(20, 20));
		website1.setText("<html> lien de la page wikipedia: : <a href=\"\">"+perso.getWiki_page()+"</a></html>");
		website1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		AccessWebWiki.goWebsite(website1,perso.getWiki_page());
		
		JPanel websites=new JPanel();
		websites.add(website);
		websites.add(website1);
		websites.setPreferredSize(new Dimension(50, 50));
		websites.setBackground(new Color(236, 248, 254));
		JButton retour = new JButton(new BoutonRetour("Retour", this));

		JPanel panelGeneral = new JPanel();
		JPanel panel = new JPanel();
		JPanel panelSouth = new JPanel();
		JPanel panelWest = new JPanel();

		panelWest.setBackground(new Color(236, 248, 254));
		panelSouth.setBackground(new Color(236, 248, 254));
		panel.setBackground(new Color(236, 248, 254));
		panelGeneral.setBackground(new Color(236, 248, 254));
		wikiText.setBackground(new Color(236, 248, 254));
		panel.setLayout(new BorderLayout());
		panelGeneral.setLayout(new BorderLayout());
		panelSouth.setLayout(new FlowLayout());
		panelWest.setLayout(new FlowLayout());
		websites.setLayout(new FlowLayout());
		JScrollPane scrollingArea = new JScrollPane(wikiText);
		scrollingArea.setPreferredSize(new Dimension(150, 150));
		setLocationRelativeTo(null);

		panelSouth.add(retour);
		panel.add(websites,BorderLayout.PAGE_END);
		panel.add(scrollingArea, BorderLayout.CENTER);
		panelGeneral.add(panel, BorderLayout.CENTER);
		panelGeneral.add(panelSouth, BorderLayout.SOUTH);
		panelGeneral.add(panelWest, BorderLayout.WEST);

		setContentPane(panelGeneral);
		revalidate();

	}

	/**
	 * interface pour afficher les informations d'un personnage
	 * 
	 * @param str
	 * @throws IOException
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 * @throws BadLocationException
	 */
	public void affichePerso(String str)
			throws IOException, JSONException, NoSuchAlgorithmException, BadLocationException {
		/*
		 * C'est le morceau du code qui s'affiche sur console mais qui donne
		 * l'impression que ça va plus vite (pour rechercher personnage)
		 */
		/*
		 * Personnage perso=InfoPerso.infoPersonnage(str); perso.afficher();
		 * InfoComics.titleComics(perso).afficheComics();
		 */
		Personnage perso = Parse.infoPersonnage(str);
		Parse.titleComics(perso);
		sDoc.remove(0, sDoc.getLength());
		String ph = "Nom du personnage :   ";
		int pos = 0;
		Style defaut = ((JTextPane) reponsePerso).getStyle("default");
		Style gras = ((JTextPane) reponsePerso).addStyle("gras", null);
		StyleConstants.setBold(gras, true);
		Object[] lcomics = new Object[perso.getComics2().size()];
		sDoc.insertString(pos, ph, gras);
		pos += ph.length();
		sDoc.insertString(pos, perso.getName() + "\n", defaut);
		pos += perso.getName().length() + 1;
		ph = "id :   ";
		sDoc.insertString(pos, ph, gras);
		pos += ph.length();
		sDoc.insertString(pos, perso.getId() + "\n", defaut);
		pos += (Integer.toString((perso.getId()))).length() + 1;
		ph = "Description :   ";
		sDoc.insertString(pos, ph, gras);
		pos += ph.length();
		sDoc.insertString(pos, perso.getDescription() + "\n", defaut);
		pos += perso.getDescription().length() + 1;
		ph = "Comics : \n ";
		sDoc.insertString(pos, ph, gras);
		pos += ph.length();

		for (int i = 0; i < perso.getComics2().size(); i++) {
			lcomics[i] = perso.getComics2().get(i);
		}
		listeComics.setListData(lcomics);
		ImageIcon icon = new ImageIcon(new URL(perso.getLien_image()));
		icon = new ImageIcon(icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
		img.setIcon(icon);

	}

	/**
	 * interface affiche la liste des comics disponible avec le String str
	 * 
	 * @param str
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws BadLocationException
	 */
	public void afficheListeComics(String str)
			throws JSONException, NoSuchAlgorithmException, IOException, BadLocationException {
		Comics comics = Parse.listeComics(str,0);

		intro.setText("<html> Effectuez un double clique sur un comics: <br>Pour cette recherche il y a "
				+ comics.getTotal() + " comics disponibles.<br></html>");
		Object[] lcomics = new Object[comics.getComics2().size()];
		for (int i = 0; i < comics.getComics2().size(); i++) {
			lcomics[i] = comics.getComics2().get(i);
		}

		listeComics.setListData(lcomics);
	}

	public void afficheListeWiki(String str) throws JSONException, NoSuchAlgorithmException, IOException,
			BadLocationException, MediaWikiApiErrorException {

		ArrayList<SearchWiki> listeDesc = wiki.infoWikipersonnage(str);

		intro.setText(
				"<html>Effectuez un double clique sur une description correspondante au personnage recherché: <br><br></html>");
		Object[] lwiki = new Object[listeDesc.size()];
		for (int i = 0; i < listeDesc.size(); i++) {
			lwiki[i] = listeDesc.get(i).getDescription();
		}

		listeWikiP.setListData(lwiki);
	}

	public void setChampStartComics(String champStartComics) {
		this.champStartComics = champStartComics;
	}

	public String getChampStartComics() {
		return champStartComics;
	}

}
