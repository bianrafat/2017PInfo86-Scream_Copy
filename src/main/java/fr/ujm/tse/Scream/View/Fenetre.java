package fr.ujm.tse.Scream.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.json.JSONException;

import fr.ujm.tse.Screram.Model.Parse;
import fr.ujm.tse.Screram.Model.Personnage;

public class Fenetre  extends JFrame  implements ActionListener {
	
	private JButton boutonPersonnage;
	private JTextField textField = new JTextField();

	private JTextArea reponsePerso ;
	private JButton recherche;
	private JButton retour;
	
	

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
	private JPanel buildContentPane(){
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.LIGHT_GRAY);
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Bienvenue");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(label);

        panel2.add(Box.createRigidArea(new Dimension(0, 20)));

		
		boutonPersonnage = new JButton("rechercher un personnage ");
		boutonPersonnage.setAlignmentX(Component.CENTER_ALIGNMENT);
		boutonPersonnage.addActionListener(this);
		panel2.add(boutonPersonnage);
		

		
		return panel2;
	}
	private void boutonPerso(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		JLabel label = new JLabel("Entrer le nom du personnage:");
		JScrollPane scrollingArea = new JScrollPane(reponsePerso);
		scrollingArea.getHorizontalScrollBar().setUnitIncrement(10);
	
		panel.add(label);

		
		textField.setMaximumSize(new Dimension(300,30));

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
		panel.add(textField);
		reponsePerso = new JTextArea("");
		reponsePerso.setEditable(false);
		reponsePerso.setLineWrap(true);
		reponsePerso.setWrapStyleWord(true); 
		reponsePerso.setBackground(Color.LIGHT_GRAY);
		
		recherche = new JButton("Rechercher");
		recherche.addActionListener(this);
		panel.add(recherche);
		retour = new JButton("Retour");
		retour.addActionListener(this);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(retour);

		setContentPane(panel);
		panel.add(reponsePerso);
		this.add(scrollingArea);
	  
		
		revalidate();
	}
	
	public void actionPerformed(ActionEvent e) { 
		Object source = e.getSource();
		 
		if(source == boutonPersonnage){
			boutonPerso();
		}else if(source == recherche) {
			String texte = textField.getText();
			try {
				affichePerso(texte);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if(source== retour) {
			setContentPane(buildContentPane());
			revalidate();
		}
	}
	public void affichePerso(String str) throws IOException, JSONException, NoSuchAlgorithmException  { 
		Personnage perso=Parse.infoPersonnage(str);
		
		reponsePerso.setText("Nom du personnage :   "+ perso.getName()+ "\n");
		reponsePerso.setText(reponsePerso.getText().concat("id :   "+ perso.getId()+"\n"));
		reponsePerso.setText(reponsePerso.getText().concat("Description :   "+ perso.getDescription()+"\n"));
		reponsePerso.setText(reponsePerso.getText().concat(" Lien de l'image de profil: " + perso.getLien_image()+ "\n"));
		reponsePerso.setText(reponsePerso.getText().concat("Comics :  "+"\n"));
		Parse.titleComics(perso);
		for(int i=0; i<perso.getComics2().size();i++) {
			reponsePerso.setText(reponsePerso.getText().concat((i+1)+") "+(String)perso.getComics2().get(i)+"\n"));
		}
		
	
	}
	
}


