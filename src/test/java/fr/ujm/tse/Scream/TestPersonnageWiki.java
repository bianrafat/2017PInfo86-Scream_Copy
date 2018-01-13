package fr.ujm.tse.Scream;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import fr.ujm.tse.Scream.Model.ParseWiki;
import fr.ujm.tse.Scream.Model.PersonnageWiki;

public class TestPersonnageWiki {
	@Test
	public void testInfoPersonnageWiki() throws IOException, MediaWikiApiErrorException{
		ArrayList<String> nicknamesExpected = new ArrayList<String>();
		nicknamesExpected.add("Donald Blake");
		nicknamesExpected.add("The Mighty Thor");
		nicknamesExpected.add("Thor Odinson");
		nicknamesExpected.add("Odinson");
		nicknamesExpected.add("Donald Blake");
		nicknamesExpected.add("Don Blake");
		nicknamesExpected.add("Sigurd Jarlson");
		nicknamesExpected.add("Daniele Neri");
		nicknamesExpected.add("Thor (Marel Comics)");
		nicknamesExpected.add("The Mighty Thor");
		String genderExpected="masculin";
		String countryExpected="Asgard";
		String fatherExpected="Odin";
		String motherExpected="Gaea";
		String stepparentExpected="Frigga";
		ArrayList<String> siblingsExpected= new ArrayList<String>();
		siblingsExpected.add("Angela");
		siblingsExpected.add("Loki");
		siblingsExpected.add("Tyr");
		siblingsExpected.add("Hermod");
		siblingsExpected.add("Balder");
		String birth_nameExpected="Non disponible";
		ArrayList<String> creatorsExpected= new ArrayList<String>();
		creatorsExpected.add("Stan Lee");
		creatorsExpected.add("Jack Kirby");
		creatorsExpected.add("Larry Lieber");
		ArrayList<String> performersExpected= new ArrayList<String>();
		performersExpected.add("Chris Hemsworth");
		performersExpected.add("Chris Wiggins");
		performersExpected.add("Jack Angel");
		performersExpected.add("Eric Allan Kramer");
		performersExpected.add("John Rhys-Davies");
		performersExpected.add("David Boat");
		performersExpected.add("Rick D. Wasserman");
		performersExpected.add("Travis Willingham");
		performersExpected.add("Michael Adamthwaite");
		ArrayList<String> superhumain_abilityExpected= new ArrayList<String>();
		superhumain_abilityExpected.add("Weather manipulation");
		String wikidata_pageExpected="https://www.wikidata.org/wiki/Q717588";
		String wiki_pageExpected="https://fr.wikipedia.org/wiki/Thor_%28comics%29";
		
		new PersonnageWiki();
		ParseWiki wiki = new ParseWiki();
		wiki.infoWikipersonnage("thor");
		wiki.infoWikipersonnagetwo(4);
		PersonnageWiki perso = wiki.getPersoWiki();

		
		String genderObtenu=perso.getGender();
		String countryObtenu=perso.getCountry();
		String fatherObtenu=perso.getFather();
		String motherObtenu=perso.getMother();
		String stepparentObtenu=perso.getStepparent();
		ArrayList<String> siblingsObtenu=perso.getSiblings2();
		String birth_nameObtenu=perso.getBirth_name();
		ArrayList<String> creatorsObtenu=perso.getCreators2();
		ArrayList<String> performersObtenu= perso.getPerformers2();
		ArrayList<String> superhumain_abilityObtenu=perso.getSuperhumain_ability2();
		String wikidata_pageObtenu=perso.getWikidata_page();
		String wiki_pageObtenu=perso.getWiki_page();
		
		
		assertEquals(genderObtenu,genderExpected);
		assertEquals(countryObtenu,countryExpected);
		assertEquals(fatherObtenu,fatherExpected);
		assertEquals(motherObtenu,motherExpected);
		assertEquals(stepparentObtenu,stepparentExpected);
		assertEquals(siblingsObtenu,siblingsExpected);
		assertEquals(birth_nameObtenu,birth_nameExpected);
		assertEquals(creatorsObtenu,creatorsExpected);
		assertEquals(performersObtenu,performersExpected);
		assertEquals(superhumain_abilityObtenu,superhumain_abilityExpected);
		assertEquals(wikidata_pageObtenu,wikidata_pageExpected);
		assertEquals(wiki_pageObtenu,wiki_pageExpected);
	}

}
