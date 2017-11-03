package fr.ujm.tse.Scream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Cette classe sert à parser les données 

public class Parse {
	public static Personnage infoPersonnage(String nom) throws IOException, JSONException, NoSuchAlgorithmException {
		String info;
		String privateKey="3f8505dd47a9407315dbda3d3193b8dc7400ab18";
		String publicKey= "194167cb1ebace9fa95d54a33cf61753";
		//génération du timstamp:
		String ts=Long.toString(System.currentTimeMillis());
		//génération du md5:
		MessageDigest md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(StandardCharsets.UTF_8.encode(ts+privateKey+publicKey));
		String md5=String.format("%032x", new BigInteger(1, md5hash.digest()));
		//on envoie la requête http
		info = HttpConnect.readUrl("https://gateway.marvel.com:443/v1/public/characters?name="+nom+"&ts="+ts+"&apikey="+publicKey+"&hash="+md5);
		// la réponse de la reque est un JSON
		JSONObject obj = new JSONObject(info);
		JSONObject data = obj.getJSONObject("data");
		JSONArray results = data.getJSONArray("results");
		Personnage pers=new Personnage();
		pers.setId(results.getJSONObject(0).getInt("id"));
		pers.setName(results.getJSONObject(0).getString("name"));
		pers.setDescription(results.getJSONObject(0).getString("description"));
		// pour avoir le lien de l'image du personnage il faut combiner path et extension
		pers.setLien_image(results.getJSONObject(0).getJSONObject("thumbnail").getString("path")+"."+results.getJSONObject(0).getJSONObject("thumbnail").getString("extension"));
		return pers;
	}
	public static Personnage titleComics(Personnage pers) throws IOException, JSONException, NoSuchAlgorithmException
	{
		String info;
		String privateKey="3f8505dd47a9407315dbda3d3193b8dc7400ab18";
		String publicKey= "194167cb1ebace9fa95d54a33cf61753";
		//génération du timstamp:
		String ts=Long.toString(System.currentTimeMillis());
		//génération du md5:
		MessageDigest md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(StandardCharsets.UTF_8.encode(ts+privateKey+publicKey));
		String md5=String.format("%032x", new BigInteger(1, md5hash.digest()));
		info = HttpConnect.readUrl("https://gateway.marvel.com:443/v1/public/comics?characters="+pers.getId()+"&ts="+ts+"&apikey="+publicKey+"&hash="+md5);
		JSONObject obj = new JSONObject(info);
		JSONObject data = obj.getJSONObject("data");
		int size=data.getInt("count");
		JSONArray results = data.getJSONArray("results");
		for(int i=0; i<size;i++)
		{
			pers.setComics(results.getJSONObject(i).getString("title"));
		}
		return pers;
	}
}
