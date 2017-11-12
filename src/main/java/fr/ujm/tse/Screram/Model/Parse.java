package fr.ujm.tse.Screram.Model;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.ujm.tse.Scream.Controller.HttpConnect;

//Cette classe sert à parser les données 

public class Parse {
	// Creation de champs statiques
	private static String info;
	private static String privateKey ="3f8505dd47a9407315dbda3d3193b8dc7400ab18";
	private static String publicKey = "194167cb1ebace9fa95d54a33cf61753"; 
	private static String reqNom = "https://gateway.marvel.com:443/v1/public/characters?name=";
	private static String reqlistComics = "https://gateway.marvel.com:443/v1/public/comics?characters=";
	private static String reqStartWith = "https://gateway.marvel.com:443/v1/public/comics?titleStartsWith=";
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
	private static String items = "items";
	private static String role = "role"; 
	
	public static Personnage infoPersonnage(String nom) throws IOException, JSONException, NoSuchAlgorithmException {
		
		
		//generation du timstamp:
		String ts=Long.toString(System.currentTimeMillis());
		
		//generation du md5:
		MessageDigest md5hash = MessageDigest.getInstance("MD5");
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
	public static Personnage titleComics(Personnage pers) throws IOException, JSONException, NoSuchAlgorithmException
	{
		//generation du timstamp:
		String ts=Long.toString(System.currentTimeMillis());
		
		//generation du md5:
		MessageDigest md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(StandardCharsets.UTF_8.encode(ts+privateKey+publicKey));
		String md5=String.format("%032x", new BigInteger(1, md5hash.digest()));
		
		info = HttpConnect.readUrl(reqlistComics+pers.getId()+timestp+ts+apikey+publicKey+hash+md5);
		
		JSONObject obj = new JSONObject(info);
		JSONObject data = obj.getJSONObject(donnees);
		
		int size=data.getInt("count");
		
		JSONArray results = data.getJSONArray(tableau);
		
		for(int i=0; i<size;i++)
		{
			pers.setComics(results.getJSONObject(i).getString(titre));
		}
		
		return pers;
	}
	
	// Classe pour retrouver des comics grace a la methode "StartWith"
	public static Comics debutTitle(String debTitle) throws  IOException, JSONException, NoSuchAlgorithmException
	{
		
		//generation du timstamp:
		String ts=Long.toString(System.currentTimeMillis());
		
		//generation du md5:
		MessageDigest md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(StandardCharsets.UTF_8.encode(ts+privateKey+publicKey));
		String md5=String.format("%032x", new BigInteger(1, md5hash.digest()));
		
		//on envoie la requete http
		info = HttpConnect.readUrl(reqStartWith+debTitle+timestp+ts+apikey+publicKey+hash+md5);
		
		JSONObject obj = new JSONObject(info);
		JSONObject data = obj.getJSONObject(donnees);
		JSONArray results = data.getJSONArray(tableau);
		
		int size = data.getInt("count");
		
		Comics comics=new Comics();
		comics.setId(results.getJSONObject(0).getInt(identifiant));
		comics.setTitle(results.getJSONObject(0).getString(titre));
		comics.setDescription(results.getJSONObject(0).getString(description));
		
		int nbCreators=results.getJSONObject(0).getJSONObject(creators).getInt("available");
		for (int j=0; j<nbCreators; j++)
		{
			if (nbCreators ==0) {
				comics.setCreators(" Creators : Aucune information");
			}
			else
			{
				comics.setCreators(results.getJSONObject(0).getJSONObject(creators).getJSONArray(items).getJSONObject(j).getString(role)+" : "+results.getJSONObject(0).getJSONObject(creators).getJSONArray(items).getJSONObject(j).getString(name));
			}
		}			
		return comics;
	}
}
