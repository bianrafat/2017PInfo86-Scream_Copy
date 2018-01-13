package fr.ujm.tse.Scream.Controller;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
/**
 *  Classe permettant de se connecter a une page web et de reécupérer les informations
 * @author Scream
 *
 */
public class HttpConnect {
	public static String readUrl(String str_url) throws IOException{

	      URL url = new URL(str_url);
	      HttpURLConnection con = (HttpURLConnection) url.openConnection();
	      con.setRequestProperty("Accept-Encoding", "gzip");
	      Reader reader = null;
	      String res = "";

	      if ("gzip".equals(con.getContentEncoding())) {
	         reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
	      }
	      else {
	         reader = new InputStreamReader(con.getInputStream());
	      }
	
	      while (true) {
	         int ch = reader.read();
	         if (ch==-1) {
	            break;
	         }
	         res += (char)ch;
	      }
	      con.disconnect();
	      return res;
 }
}
