package fr.ujm.tse.Screram.Model;

import java.util.ArrayList;

public class Comics {
	private int id;
	private String title;
	private String description;
	private ArrayList<String> creators = new ArrayList<String>();
	//private ArrayList<Comics> tabComics = new ArrayList<Comics>();
	
	
	public Comics(int id, String title, String description) {
		super();
		this.setId(id);
		this.setTitle(title);
		this.setDescription(description);
	}
	
	public Comics() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public void setCreators(String creators) {
		this.creators.add(creators);
	}
	
	public void getCreators() {
		for (int i=0; i<creators.size(); i++)
		{
			String creator = (String)creators.get(i);
			System.out.println(creator);
		}
	}

	//essayer d'utiliser cette methode pour afficher les titre avec descriptions
	public void afficher() {
		System.out.println("Titre : " + this.getTitle());
		System.out.println("Id : " + this.getId());
		System.out.println("Description : " + this.getDescription());
		this.getCreators();
	}

	


	

	
	

}
