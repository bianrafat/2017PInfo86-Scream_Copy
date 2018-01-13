package fr.ujm.tse.Scream.Controller;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;
/**
 * Classe qui ouvre une page web 
 * @author Scream
 *
 */
public class AccessWebWiki {
	/**
	 * Ouvre une page web quand l'utilisateur clique sur le lien 
	 * @param web
	 * @param url
	 */
	public static void goWebsite(JLabel web,String url) {
	    web.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            

	        	try {
	        		try {
	        			if(Desktop.isDesktopSupported()) {
	        				Desktop.getDesktop().browse(new URI(url));}
	        		} catch (URISyntaxException e1) {
	        			// TODO Auto-generated catch block
	        			e1.printStackTrace();
	        		}
	        	} catch (IOException e1) {
	        		// TODO Auto-generated catch block
	        		e1.printStackTrace();
	        	}


	        }
	    });
	}
}
