package fr.ujm.tse.Scream.Model;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.wikidata.wdtk.datamodel.interfaces.EntityDocument;
import org.wikidata.wdtk.datamodel.interfaces.ItemDocument;
import org.wikidata.wdtk.datamodel.interfaces.MonolingualTextValue;
import org.wikidata.wdtk.datamodel.interfaces.SiteLink;
import org.wikidata.wdtk.datamodel.interfaces.Sites;
import org.wikidata.wdtk.datamodel.interfaces.Statement;
import org.wikidata.wdtk.datamodel.interfaces.StatementGroup;
import org.wikidata.wdtk.datamodel.interfaces.StringValue;
import org.wikidata.wdtk.dumpfiles.DumpProcessingController;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import fr.ujm.tse.Scream.Controller.HttpConnect;
import fr.ujm.tse.Scream.Controller.Login;

public class ParseWiki implements Runnable{



	private String info = "";
	private List<String> id = new ArrayList<String>();
	private List<String> desc = new ArrayList<String>();
	private JSONObject obj = new JSONObject();
	JSONArray results = new JSONArray();
	int choix = 0;
	private static WikibaseDataFetcher wbdf = WikibaseDataFetcher.getWikidataDataFetcher();
	private StatementGroup stringValue=null;
	private Statement stringValue1=null;
	private Statement stringValue2=null;
	private Statement stringValue3=null;
	private StatementGroup stringValue4=null;
	private Statement stringValue5=null;
	private StatementGroup stringValue6=null;
	private Statement stringValue7=null;
	private StatementGroup stringValue8=null;
	private StatementGroup stringValue9=null;
	private StatementGroup stringValue10=null;
	private StatementGroup stringValue11=null;
	private SiteLink stringValue12=null;

	static final String extractPropertyId = "P31";
	static final String extractPropertyId1 = "P21";
	static final String extractPropertyId2 = "P27";
	static final String extractPropertyId3 = "P22";
	static final String extractPropertyId4 = "P25";
	static final String extractPropertyId5="P3448";
	static final String extractPropertyId6="P3373";
	static final String extractPropertyId7="P1477";
	static final String extractPropertyId8="P463";
	static final String extractPropertyId9="P170";
	static final String extractPropertyId10="P175";
	static final String extractPropertyId11="P2563";


	private PersonnageWiki persoWiki = new PersonnageWiki();
	private String[] list ={};
	private String[] list1 ={};
	private String[] list2 ={};
	private String[] list3 ={};
	private String[] list4 ={};
	private String[] list5 ={};
	private String[] list6 ={};
	private String[] list7 ={};
	private String[] list8={};
	private String[] list9={};
	private String[] list10={};
	private String[] list11={};
	private List<String> list_1 = new ArrayList<String>();
	private List<String> list_2 = new ArrayList<String>();
	private List<String> list_3 = new ArrayList<String>();
	private List<String> list_4 = new ArrayList<String>();
	private List<String> list_5 = new ArrayList<String>();
	private List<String> list_6 = new ArrayList<String>();
	private List<String> list_7 = new ArrayList<String>();
	public static final boolean OFFLINE_MODE = false;


	public void infoWikipersonnage(String nom) throws IOException, MediaWikiApiErrorException {
		info = HttpConnect.readUrl("https://www.wikidata.org/w/api.php?action=wbsearchentities&search="+nom+"&language=en&format=json");
		obj = new JSONObject(info);
		JSONArray results = obj.getJSONArray("search");
		for (int i = 0; i < results.length(); i++) {
			id.add(results.getJSONObject(i).getString("id"));
			if(results.getJSONObject(i).has("description")){
				desc.add(results.getJSONObject(i).getString("description"));
			}
			else{
				desc.add("");
			}
			System.out.println("choix " + (i + 1) + ":" + desc.get(i));
		}
		System.out.println("entrez le num�ro de description qui correspond au personnage recherch�");
		Scanner scan = new Scanner(System.in);
		try {
			choix = scan.nextInt();
			scan.close();
			EntityDocument entityDoc= wbdf.getEntityDocument(id.get((choix-1)));
			ItemDocument itemDocument=(ItemDocument) entityDoc;

			// If a value was found, write the data:
			if (itemDocument.getLabels() != null) {
				String name=itemDocument.getLabels().get("fr").getText();
				persoWiki.setName(name);
			}
			else{
				persoWiki.setName("");

			}
			if(itemDocument.getDescriptions()!=null){
				String description=itemDocument.getDescriptions().get("fr").getText();
				persoWiki.setDescription(description);
			}
			else{
				persoWiki.setDescription("");
			}
			if(itemDocument.getAliases()!=null){
				List<MonolingualTextValue> aliasesFr = itemDocument.getAliases().get("fr");
				List<MonolingualTextValue> aliasesEn = itemDocument.getAliases().get("en");
				for(int i=0;i<aliasesFr.size();i++){
					persoWiki.setNicknames(aliasesFr.get(i).getText());
				}
				for(int i=0;i<aliasesEn.size();i++){
					persoWiki.setNicknames(aliasesEn.get(i).getText());
				}
			}
			else{
				persoWiki.setNicknames("");
			}
			stringValue = itemDocument.findStatementGroup(extractPropertyId);
			if (stringValue!= null) {
				stringValue.forEach(i -> {
					list = i.getValue().toString().split("/");
					list_1.add(list[list.length-1].split(" ")[0]);
				});
				for(String j:list_1){
					ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(j);
					if(ITdoc.getLabels().get("fr") != null){
						persoWiki.setInstance_of(ITdoc.getLabels().get("fr").getText());
					}
					else if(ITdoc.getLabels().get("en") != null){
						persoWiki.setInstance_of(ITdoc.getLabels().get("en").getText());
					}
					else{
						persoWiki.setInstance_of("");

					}

				}
			}

			else{
				persoWiki.setInstance_of("");
			}
			stringValue1 = itemDocument.findStatement(extractPropertyId1);
			if (stringValue1!= null) {
				list1=stringValue1.getValue().toString().split("/");
				String a=list1[list1.length-1].split(" ")[0];
				ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(a);
				if(ITdoc.getLabels().get("fr") != null){
					persoWiki.setGender(ITdoc.getLabels().get("fr").getText());
				}
				else if(ITdoc.getLabels().get("en") != null){
					persoWiki.setGender(ITdoc.getLabels().get("en").getText());
				}
				else{
					persoWiki.setGender("");

				}
			}
			else{
				persoWiki.setGender("");
			}
			stringValue2 = itemDocument.findStatement(extractPropertyId2);
			if (stringValue2!= null) {
				list2=stringValue2.getValue().toString().split("/");
				String a=list2[list2.length-1].split(" ")[0];
				ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(a);
				if(ITdoc.getLabels().get("fr") != null){
					persoWiki.setCountry(ITdoc.getLabels().get("fr").getText());
				}
				else if(ITdoc.getLabels().get("en") != null){
					persoWiki.setCountry(ITdoc.getLabels().get("en").getText());
				}
				else{
					persoWiki.setCountry("");

				}
			}
			else{
				persoWiki.setCountry("");
			}
			stringValue3 = itemDocument.findStatement(extractPropertyId3);
			if (stringValue3!= null) {
				list3=stringValue3.getValue().toString().split("/");
				String a=list3[list3.length-1].split(" ")[0];
				ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(a);
				if(ITdoc.getLabels().get("fr") != null){
					persoWiki.setFather(ITdoc.getLabels().get("fr").getText());
				}
				else if(ITdoc.getLabels().get("en") != null){
					persoWiki.setFather(ITdoc.getLabels().get("en").getText());
				}
				else{
					persoWiki.setFather("");

				}

			}
			else{
				persoWiki.setFather("");
			}
			stringValue4 = itemDocument.findStatementGroup(extractPropertyId4);
			if (stringValue4!= null) {
				stringValue4.forEach(i -> {
					list4 = i.getValue().toString().split("/");
					list_2.add(list4[list4.length-1].split(" ")[0]);
				});
				String a=list_2.get(0);
				ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(a);
				if(ITdoc.getLabels().get("fr") != null){
					persoWiki.setMother(ITdoc.getLabels().get("fr").getText());
				}
				else if(ITdoc.getLabels().get("en") != null){
					persoWiki.setMother(ITdoc.getLabels().get("en").getText());
				}
				else{
					persoWiki.setMother("");	
				}

			}
			else{
				persoWiki.setMother("");
			}
			stringValue5 = itemDocument.findStatement(extractPropertyId5);
			if (stringValue5!= null) {
				list5=stringValue5.getValue().toString().split("/");
				String a=list5[list5.length-1].split(" ")[0];
				ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(a);
				if(ITdoc.getLabels().get("fr") != null){
					persoWiki.setStepparent(ITdoc.getLabels().get("fr").getText());
				}
				else if(ITdoc.getLabels().get("en") != null){
					persoWiki.setStepparent(ITdoc.getLabels().get("en").getText());
				}
				else{
					persoWiki.setStepparent("");

				}

			}
			else{
				persoWiki.setStepparent("");
			}
			stringValue6 = itemDocument.findStatementGroup(extractPropertyId6);
			if (stringValue6!= null) {
				stringValue6.forEach(i -> {
					list6= i.getValue().toString().split("/");
					list_3.add(list6[list6.length-1].split(" ")[0]);
				});
				for(String j:list_3){
					ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(j);
					if(ITdoc.getLabels().get("fr") != null){
						persoWiki.setSiblings(ITdoc.getLabels().get("fr").getText());
					}
					else if(ITdoc.getLabels().get("en") != null){
						persoWiki.setSiblings(ITdoc.getLabels().get("en").getText());
					}
					else{
						persoWiki.setSiblings("");	
					}	
				}
			}
			else{
				persoWiki.setSiblings("");
			}
			stringValue7 = itemDocument.findStatement(extractPropertyId7);
			if (stringValue7!= null) {
				list7=stringValue7.getValue().toString().split("/");
				String a=list7[list7.length-1].split(" ")[0];
				persoWiki.setBirth_name(a);


			}
			else{
				persoWiki.setBirth_name("");
			}
			stringValue8 = itemDocument.findStatementGroup(extractPropertyId8);
			if (stringValue8!= null) {
				stringValue8.forEach(i -> {
					list8 = i.getValue().toString().split("/");
					list_4.add(list8[list8.length-1].split(" ")[0]);
				});
				for(String j:list_4){
					ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(j);
					if(ITdoc.getLabels().get("fr") != null){
						persoWiki.setMember_of(ITdoc.getLabels().get("fr").getText());
					}
					else if(ITdoc.getLabels().get("en") != null){
						persoWiki.setMember_of(ITdoc.getLabels().get("en").getText());
					}
					else{
						persoWiki.setMember_of("");

					}

				}
			}

			else{
				persoWiki.setMember_of("");
			}
			stringValue9 = itemDocument.findStatementGroup(extractPropertyId9);
			if (stringValue9!= null) {
				stringValue9.forEach(i -> {
					list9= i.getValue().toString().split("/");
					list_5.add(list9[list9.length-1].split(" ")[0]);
				});
				for(String j:list_5){
					ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(j);
					if(ITdoc.getLabels().get("fr") != null){
						persoWiki.setCreators(ITdoc.getLabels().get("fr").getText());
					}
					else if(ITdoc.getLabels().get("en") != null){
						persoWiki.setCreators(ITdoc.getLabels().get("en").getText());
					}
					else{
						persoWiki.setCreators("");

					}

				}
			}

			else{
				persoWiki.setCreators("");
			}

			stringValue10 = itemDocument.findStatementGroup(extractPropertyId10);
			if (stringValue10!= null) {
				stringValue10.forEach(i -> {
					list10= i.getValue().toString().split("/");
					list_6.add(list10[list10.length-1].split(" ")[0]);
				});
				for(String j:list_6){
					ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(j);
					if(ITdoc.getLabels().get("fr") != null){
						persoWiki.setPerformers(ITdoc.getLabels().get("fr").getText());
					}
					else if(ITdoc.getLabels().get("en") != null){
						persoWiki.setPerformers(ITdoc.getLabels().get("en").getText());
					}
					else{
						persoWiki.setPerformers("");
					}
				}
			}

			else{
				persoWiki.setPerformers("");
			}
			stringValue11 = itemDocument.findStatementGroup(extractPropertyId11);
			if (stringValue11!= null) {
				stringValue11.forEach(i -> {
					list11= i.getValue().toString().split("/");
					list_7.add(list11[list11.length-1].split(" ")[0]);
				});
				for(String j:list_7){
					ItemDocument ITdoc = (ItemDocument) wbdf.getEntityDocument(j);
					if(ITdoc.getLabels().get("fr") != null){
						persoWiki.setsuperhumain_ability(ITdoc.getLabels().get("fr").getText());
					}
					else if(ITdoc.getLabels().get("en") != null){
						persoWiki.setsuperhumain_ability(ITdoc.getLabels().get("en").getText());
					}
					else{
						persoWiki.setsuperhumain_ability("");
					}
				}
			}

			else{
				persoWiki.setsuperhumain_ability("");
			}
			Login.configureLogging();
			DumpProcessingController dumpProcessingController = new DumpProcessingController(
					"wikidatawiki");
			dumpProcessingController.setOfflineMode(OFFLINE_MODE);
			// Download the sites table dump and extract information
			Sites sites = dumpProcessingController.getSitesInformation();
			stringValue12 = itemDocument.getSiteLinks().get("frwiki");
			//Statement stringValue13 = itemDocument.findStatement("enwiki");
			if(stringValue12!=null){
				String a=sites.getPageUrl("frwiki", stringValue12.getPageTitle());
				persoWiki.setWiki_page(a);
			}
			else{
				persoWiki.setWiki_page("");
			}



		} catch (InputMismatchException e) {
			System.out.println("entrez un chiffre entre 1 et" + results.length());
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("entrez le nom d'un personnage");
		Scanner sc1 = new Scanner(System.in);
		String str = sc1.nextLine();
		try {
			infoWikipersonnage(str);
			persoWiki.afficher();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaWikiApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




}