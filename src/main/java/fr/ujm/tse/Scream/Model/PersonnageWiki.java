package fr.ujm.tse.Scream.Model;
import java.util.ArrayList;

public class PersonnageWiki {



	private String name;
	private String description;
	private ArrayList<String> nicknames= new ArrayList<String>();
	private ArrayList<String> instance_of= new ArrayList<String>();
	private String gender;
	private String country;
	private String father;
	private String mother;
	private String stepparent;
	private ArrayList<String> siblings= new ArrayList<String>();
	private String birth_name;
	private ArrayList<String> member_of= new ArrayList<String>();
	private ArrayList<String> creators= new ArrayList<String>();
	private ArrayList<String> performers= new ArrayList<String>();
	private ArrayList<String> superhumain_ability= new ArrayList<String>();
	private String wiki_page;
	public PersonnageWiki(String name, String description,String gender, String country,
			String father, String mother, String stepparent, ArrayList<String> siblings, String birth_name,
			String wiki_page) {
		super();
		this.name = name;
		this.description = description;
		this.gender = gender;
		this.country = country;
		this.father = father;
		this.mother = mother;
		this.stepparent = stepparent;
		this.siblings = siblings;
		this.birth_name = birth_name;
		this.wiki_page = wiki_page;
	}
	public PersonnageWiki() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getStepparent() {
		return stepparent;
	}
	public void setStepparent(String stepparent) {
		this.stepparent = stepparent;
	}
	public String getBirth_name() {
		return birth_name;
	}
	public void setBirth_name(String birth_name) {
		this.birth_name = birth_name;
	}
	public String getWiki_page() {
		return wiki_page;
	}
	public void setWiki_page(String wiki_page) {
		this.wiki_page = wiki_page;
	}
	public void getNicknames() {
		System.out.println("Surnoms:");
		for(int i=0; i<nicknames.size(); i++)
		{
			String surnom = (String)nicknames.get(i);
			System.out.println(surnom);
		}
	}
	public void setNicknames(String nickname) {
		this.nicknames.add(nickname);
	}
	public void getInstance_of() {
		System.out.println("Instance de:");
		for(int i=0; i<instance_of.size(); i++)
		{
			String instance = (String)instance_of.get(i);
			System.out.println(instance);
		}
	}
	public void setInstance_of(String instance) {
		this.instance_of.add(instance);
	}
	public void getSiblings() {
		System.out.println("fr�res et soeurs:");
		for(int i=0; i<siblings.size(); i++)
		{
			String sibling = (String)siblings.get(i);
			System.out.println(sibling);
		}
	}
	public void setSiblings(String sibling) {
		this.siblings.add(sibling);
	}

	public void getMember_of() {
		System.out.println("Membre de:");
		for(int i=0; i<member_of.size(); i++)
		{
			String membre = (String)member_of.get(i);
			System.out.println(membre);
		}
	}
	public void setMember_of(String membre) {
		this.member_of.add(membre);
	}
	public void getCreators() {
		System.out.println("Auteurs:");
		for(int i=0; i<creators.size(); i++)
		{
			String creator = (String)creators.get(i);
			System.out.println(creator);
		}
	}
	public void setCreators(String creator) {
		this.creators.add(creator);
	}
	public void getPerformers() {
		System.out.println("Acteurs ayant interpr�t� ce role:");
		for(int i=0; i<performers.size(); i++)
		{
			String performer = (String)performers.get(i);
			System.out.println(performer);
		}
	}
	public void setPerformers(String performer) {
		this.performers.add(performer);
	}


	public void getSuperhumain_ability() {
		System.out.println("pouvoirs:");
		for(int i=0; i<superhumain_ability.size(); i++)
		{
			String pouv = (String)superhumain_ability.get(i);
			System.out.println(pouv);
		}
	}
	public void setsuperhumain_ability(String pouv) {
		this.superhumain_ability.add(pouv);
	}
	public void afficher() {
		System.out.println("Nom du personnage : " + this.getName());
		System.out.println(" Description: " + this.getDescription());
		this.getNicknames();
		this.getInstance_of();
		System.out.println("sexe:"+this.getGender());
		System.out.println("pays:"+this.getCountry());
		System.out.println("p�re:"+this.getFather());
		System.out.println("m�re:"+this.getMother());
		System.out.println("beau-parent:"+this.getStepparent());
		this.getSiblings();
		System.out.println("nom de naissance:"+this.getBirth_name());
		this.getCreators();
		this.getMember_of();
		this.getPerformers();
		this.getSuperhumain_ability();
		System.out.println("lien de la page wikipedia:"+this.getWiki_page());
	} 








}