package fr.ujm.tse.Scream.Model;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.ujm.tse.Scream.Controller.HttpConnect;


//Cette classe sert à  parser les données 

public class Parse {
	// Creation de champs statiques
	private static String info;
	private static String privateKey ="3f8505dd47a9407315dbda3d3193b8dc7400ab18";
	private static String publicKey = "194167cb1ebace9fa95d54a33cf61753"; 
	private static String reqNom = "https://gateway.marvel.com:443/v1/public/characters?name=";
	private static String reqlistComics = "https://gateway.marvel.com:443/v1/public/comics?characters=";
	private static String reqTitle = "https://gateway.marvel.com:443/v1/public/comics?titleStartsWith=";
	private static String reqTitleId = "https://gateway.marvel.com:443/v1/public/comics/";
	private static String limit = "&limit=10";
	private static String offset = "&offset=";
	private static String apikey = "&apikey=";
	private static String timestp = "&ts=";
	private static String hash = "&hash=";
	private static String donnees ="data";
	private static String tableau = "results";
	private static String identifiant = "id";
	private static String name = "name";
	private static String description = "description";
	private static String titre = "title";
	private static String thumbnail = "thumbnail";
	private static String path = "path";
	private static String extension = "extension";
	private static String creators = "creators";
	private static String characters = "characters";
	private static String items = "items";
	private static String role = "role"; 
	private static String total = "total";
	private static String count = "count";
	private static String ts=Long.toString(System.currentTimeMillis()); //generation du timstamp:
	private static MessageDigest md5hash;
	private static Scanner sc1;
	
	
	/**
	 * 
	 * @param nom
	 * @return pers
	 * @throws IOException
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 */
	public static Personnage infoPersonnage(String nom) throws IOException, JSONException, NoSuchAlgorithmException {
		nom=nom.replace(" ", "%20");
		//generation du md5:
		md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(StandardCharsets.UTF_8.encode(ts+privateKey+publicKey));
		String md5=String.format("%032x", new BigInteger(1, md5hash.digest()));
		
		//on envoie la requete http
		info = HttpConnect.readUrl(reqNom+nom+timestp+ts+apikey+publicKey+hash+md5);
		
		// la reponse de la requete est un JSON
		JSONObject obj = new JSONObject(info);
		JSONObject data = obj.getJSONObject(donnees);
		JSONArray results = data.getJSONArray(tableau);
		Personnage pers=new Personnage();
		pers.setId(results.getJSONObject(0).getInt(identifiant));
		pers.setName(results.getJSONObject(0).getString(name));
		pers.setDescription(results.getJSONObject(0).getString(description));
		
		// pour avoir le lien de l'image du personnage il faut combiner path et extension
		pers.setLien_image(results.getJSONObject(0).getJSONObject(thumbnail).getString(path)+"."+results.getJSONObject(0).getJSONObject(thumbnail).getString(extension));
		
		return pers;
	}
	
	/**
	 * 
	 * @param pers
	 * @return pers
	 * @throws IOException
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 */
	public static Personnage titleComics(Personnage pers) throws IOException, JSONException, NoSuchAlgorithmException
	{
		//generation du md5:
		md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(StandardCharsets.UTF_8.encode(ts+privateKey+publicKey));
		String md5=String.format("%032x", new BigInteger(1, md5hash.digest()));
		
		info = HttpConnect.readUrl(reqlistComics+pers.getId()+limit+timestp+ts+apikey+publicKey+hash+md5);
		
		JSONObject obj = new JSONObject(info);
		JSONObject data = obj.getJSONObject(donnees);
		
		int size=data.getInt("count");
		
		JSONArray results = data.getJSONArray(tableau);
		
		for(int i=0; i<size;i++)
		{
			pers.setComics(Integer.toString(results.getJSONObject(i).getInt(identifiant)) +":"+results.getJSONObject(i).getString(titre));
		}
		
		return pers;
	}
	
	
	// Classe pour retrouver des comics grace a la methode "StartWith"
	// Même méthode que titleComics mais cette fois pour les Comics et non les personnages
	
	/**
	 * 
	 * @param title
	 * @return comics
	 * @throws IOException
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 */
	public static Comics listeComics(String title, int nb_offset) throws  IOException, JSONException, NoSuchAlgorithmException
	{
		
		//generation du md5:
		md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(StandardCharsets.UTF_8.encode(ts+privateKey+publicKey));
		String md5=String.format("%032x", new BigInteger(1, md5hash.digest()));
		
		//on envoie la requete http
		info = HttpConnect.readUrl(reqTitle+title+limit+offset+nb_offset+timestp+ts+apikey+publicKey+hash+md5);
		
		JSONObject obj = new JSONObject(info);
		JSONObject data = obj.getJSONObject(donnees);
		JSONArray results = data.getJSONArray(tableau);
		
		
		Comics comics=new Comics();
		int size = data.getInt(count);
		comics.setTotal(data.getInt(total));
		for(int i=0; i<size;i++)
		{
			comics.setComics(results.getJSONObject(i).getString(titre));
			comics.setLien_image(results.getJSONObject(i).getJSONObject(thumbnail).getString(path)+"."+results.getJSONObject(i).getJSONObject(thumbnail).getString(extension));
		}
		return comics;
	}
	
	// Méthode qui permet d'avoir des informations sur un comics choisi par l'utilisateur.
	// Une liste de titre de Comics lui est proposé, ensuite il fait un choix parmi cette liste.
	
	/**
	 * 
	 * @param title
	 * @param num
	 * @return comics
	 * @throws IOException
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 */
	public static Comics infoComics(String title, int num, int page) throws  IOException, JSONException, NoSuchAlgorithmException
	{
		
		//generation du md5:
		md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(StandardCharsets.UTF_8.encode(ts+privateKey+publicKey));
		String md5=String.format("%032x", new BigInteger(1, md5hash.digest()));
		
		//on envoie la requete http
		info = HttpConnect.readUrl(reqTitle+title+timestp+ts+apikey+publicKey+hash+md5);
		
		JSONObject obj = new JSONObject(info);
		JSONObject data = obj.getJSONObject(donnees);
		JSONArray results = data.getJSONArray(tableau);
		
		// création d'un objet Comics
		Comics comics=new Comics();		
		
		//Création d'une boucle pour que l'utilisateur choisisse bien entre 1 et 10  pour chaque page(liste de comics)
		if ( num + 10*page < 1 + 10*page || num + 10*page > 10 + 10*page) {
			System.out.println("Veuillez entrer un nombre entre 1 et 10 / Please enter a number between 1 and 10.");
			sc1 = new Scanner(System.in); 
			int i = sc1.nextInt();
			comics = Parse.infoComics(title, i, page);
		}
		else {
			//Récupération de l'identifiant, du titre et de la description.
			comics.setId(results.getJSONObject(num + 10*page -1).getInt(identifiant));
			comics.setTitle(results.getJSONObject(num + 10*page -1).getString(titre));
			
			
			// Récupération de la description si elle existe
			if (results.getJSONObject(num + 10*page -1).isNull(description)){
				comics.setDescription("Aucune description / No description available.");
			}else {
				comics.setDescription(results.getJSONObject(num + 10*page -1).getString(description));
			}
			
			// Récupération des créateurs 
			int nbCreators=results.getJSONObject(num + 10*page -1).getJSONObject(creators).getInt("available");
			if (nbCreators ==0) {
				comics.setCreators("Aucune information / No information.");
				comics.setPremierCreateur("Aucune information");
			}
			else{
				comics.setPremierCreateur(results.getJSONObject(num + 10*page -1).getJSONObject(creators).getJSONArray(items).getJSONObject(0).getString(name));
				for (int j=0; j<nbCreators; j++)
				{
					comics.setCreators(results.getJSONObject(num + 10*page -1).getJSONObject(creators).getJSONArray(items).getJSONObject(j).getString(role).toUpperCase()+" : "+results.getJSONObject(num + 10*page -1).getJSONObject(creators).getJSONArray(items).getJSONObject(j).getString(name));
				}
			}
			
			// Récupération des personnages
			int nbCharacters=results.getJSONObject(num + 10*page -1).getJSONObject(characters).getInt("available");
			if (nbCharacters ==0) {
				comics.setCharacters("Aucune information / No information.");
			}
			else{
				for (int j=0; j<nbCharacters; j++)
				{			
					comics.setCharacters(results.getJSONObject(num + 10*page -1).getJSONObject(characters).getJSONArray(items).getJSONObject(j).getString(name));
				}
			}
			
			// pour avoir le lien de l'image du personnage il faut combiner path et extension
			comics.setLien_image(results.getJSONObject(num + 10*page -1).getJSONObject(thumbnail).getString(path)+"."+results.getJSONObject(num + 10*page -1).getJSONObject(thumbnail).getString(extension));
		}
		return comics;
	}
	
	public static Comics infoComicsId(String title) throws  IOException, JSONException, NoSuchAlgorithmException
	{
		
		//generation du md5:
		md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(StandardCharsets.UTF_8.encode(ts+privateKey+publicKey));
		String md5=String.format("%032x", new BigInteger(1, md5hash.digest()));
		
		//on envoie la requete http
		info = HttpConnect.readUrl(reqTitleId+Integer.parseInt(title)+"?"+timestp+ts+apikey+publicKey+hash+md5);
		
		JSONObject obj = new JSONObject(info);
		JSONObject data = obj.getJSONObject(donnees);
		JSONArray results = data.getJSONArray(tableau);
		
		// création d'un objet Comics
		Comics comics=new Comics();		
		
		//Récupération de l'identifiant, du titre et de la description.
		comics.setId(results.getJSONObject(0).getInt(identifiant));
		comics.setTitle(results.getJSONObject(0).getString(titre));
		
		
		// Récupération de la description si elle existe
		if (results.getJSONObject(0).isNull(description)){
			comics.setDescription("Aucune description / No description available.");
		}else {
			comics.setDescription(results.getJSONObject(0).getString(description));
		}
		
		// Récupération des créateurs 
		int nbCreators=results.getJSONObject(0).getJSONObject(creators).getInt("available");
		if (nbCreators ==0) {
			comics.setCreators("Aucune information / No information.");
			comics.setPremierCreateur("Aucune information");
		}
		else{
			comics.setPremierCreateur(results.getJSONObject(0).getJSONObject(creators).getJSONArray(items).getJSONObject(0).getString(name));
			for (int j=0; j<nbCreators; j++)
			{
				comics.setCreators(results.getJSONObject(0).getJSONObject(creators).getJSONArray(items).getJSONObject(j).getString(role).toUpperCase()+" : "+results.getJSONObject(0).getJSONObject(creators).getJSONArray(items).getJSONObject(j).getString(name));
			}
		}
		
		// Récupération des personnages
		int nbCharacters=results.getJSONObject(0).getJSONObject(characters).getInt("available");
		if (nbCharacters ==0) {
			comics.setCharacters("Aucune information / No information.");
		}
		else{
			for (int j=0; j<nbCharacters; j++)
			{			
				comics.setCharacters(results.getJSONObject(0).getJSONObject(characters).getJSONArray(items).getJSONObject(j).getString(name));
			}
		}
		
		// pour avoir le lien de l'image du personnage il faut combiner path et extension
		comics.setLien_image(results.getJSONObject(0).getJSONObject(thumbnail).getString(path)+"."+results.getJSONObject(0).getJSONObject(thumbnail).getString(extension));
		
		return comics;
	}
}
