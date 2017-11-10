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
	private static String privateKey ="3f8505dd47a9407315dbda3d3193b8dc7400ab18";
	private static String publicKey = "194167cb1ebace9fa95d54a33cf61753"; 
	private static String reqNom = "https://gateway.marvel.com:443/v1/public/characters?name=";
	private static String reqlistComics = "https://gateway.marvel.com:443/v1/public/comics?characters=";
	private static String apikey = "&apikey=";
	private static String timestp = "&ts=";
	private static String hash = "&hash=";
	private static String donnees ="data";
	private static String tableau = "results";
	private static String identifiant = "id";
	private static String name = "name";
	private static String description = "description";
	private static String titre = "title";
	private static String jsonobject = "thumbnail";
	private static String path = "path";
	private static String extension = "extension";
	
	public static Personnage infoPersonnage(String nom) throws IOException, JSONException, NoSuchAlgorithmException {
		String info;
		
		//génération du timstamp:
		String ts=Long.toString(System.currentTimeMillis());
		
		//génération du md5:
		MessageDigest md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(StandardCharsets.UTF_8.encode(ts+privateKey+publicKey));
		String md5=String.format("%032x", new BigInteger(1, md5hash.digest()));
		
		//on envoie la requête http
		info = HttpConnect.readUrl(reqNom+nom+timestp+ts+apikey+publicKey+hash+md5);
		
		// la réponse de la reque est un JSON
		JSONObject obj = new JSONObject(info);
		JSONObject data = obj.getJSONObject(donnees);
		JSONArray results = data.getJSONArray(tableau);
		Personnage pers=new Personnage();
		pers.setId(results.getJSONObject(0).getInt(identifiant));
		pers.setName(results.getJSONObject(0).getString(name));
		pers.setDescription(results.getJSONObject(0).getString(description));
		
		// pour avoir le lien de l'image du personnage il faut combiner path et extension
		pers.setLien_image(results.getJSONObject(0).getJSONObject(jsonobject).getString(path)+"."+results.getJSONObject(0).getJSONObject(jsonobject).getString(extension));
		
		return pers;
	}
	public static Personnage titleComics(Personnage pers) throws IOException, JSONException, NoSuchAlgorithmException
	{
		String info;
		//génération du timstamp:
		String ts=Long.toString(System.currentTimeMillis());
		
		//génération du md5:
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
}
