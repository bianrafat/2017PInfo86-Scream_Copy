package fr.ujm.tse.Scream.Model;
/**
 * Classe d�crivant le r�sultat d'une recherche par entit�. 
 * le r�sultat est repr�sent� par une description et un identifiant, l'id permet le num�roter dans une liste de choix
 * @author Scream
 *
 */
public class SearchWiki {
	private String identifiant;
	private String description;
	private int id;
	public SearchWiki(String identifiant, String description) {
		super();
		this.identifiant = identifiant;
		this.description = description;
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SearchWiki(){
	
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void Affiher(){
		System.out.println(this.getId()+":"+this.getDescription());
		
	}

}
	