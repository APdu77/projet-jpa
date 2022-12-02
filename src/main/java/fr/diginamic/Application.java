package fr.diginamic;

import java.io.IOException;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Athlete;
import fr.diginamic.entites.Epreuve;
import fr.diginamic.entites.Equipe;
import fr.diginamic.entites.Medaille;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.SessionJO;
import fr.diginamic.entites.Sport;
import fr.diginamic.entites.TraductionEpreuve;
import fr.diginamic.entites.TraductionSport;
import fr.diginamic.services.Doublon;
import fr.diginamic.services.InsertionBDD;
import fr.diginamic.services.Splitteur;
import fr.diginamic.services.Traitement;

public class Application {

	public Application() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException, NumberFormatException {
		// TODO Auto-generated method stub

		/*
		 * Adressage du fichier recensement.csv
		 * 
		 */
		// String filePath =
		// ClassLoader.getSystemClassLoader().getResource("athlete_epreuves.csv").getFile();
		// Path path = Paths.get(filePath);

		List<String> lines = Traitement.extraireLignesUTF("./src/main/resources/athlete_epreuves.csv");
		List<String> linesTradSport = Traitement.extraireLignesUTF("./src/main/resources/liste des sports.csv");
		List<String> linesTradEpreuve = Traitement.extraireLignesUTF("./src/main/resources/liste_des_epreuves.csv");
		//List<String> linesTradSport = Traitement.extraireLignesWin("./src/main/resources/liste des sports.csv");
		// Path path = Paths.get("./athlete_epreuves.csv");

		System.out.println(linesTradSport.size());
		System.out.println(linesTradEpreuve.size());
		//LinesTraduction = linesTradSport.addAll(linesTradEpreuve);
		System.out.println(linesTradSport.size());
		System.out.println(linesTradEpreuve.size());
		
		
		/*
		 * Creation de la liste contenant toutes les lignes du fichier cs
		 * 
		 */
//		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
//		System.out.println(lines.get(1));
//		lines.remove(0);
//		System.out.println(lines.get(0));
//
//		String pathPays = "./src/main/resources/wikipedia-iso-country-codes.csv";
//		File file = new File(pathPays);
//		List<String> linesPays = FileUtils.readLines(file, "windows-1252");
//		System.out.println(linesPays.get(1));
//		linesPays.remove(0);
//		System.out.println(linesPays.get(0));
//		//lignes = FileUtils.readLines(pathPays, "windows-1252");

		EntityManagerFactory entManFac = Persistence.createEntityManagerFactory("app_jo");
		EntityManager entMan = entManFac.createEntityManager();
		EntityTransaction trans = entMan.getTransaction();
		// trans.begin();

		
		
		
		
		
		/*
		 * Instanciation de la variable qui contiendra tou arguments
		 */

		for (int i = 0; i < 200; i++) {
			trans.begin();
			// if (i < linesPays.size()) {

			// }
			Athlete athlete = Splitteur.ajouterAthlete(lines.get(i));
			athlete = InsertionBDD.ajouterAthlete(entMan, athlete);
			// athlete.getNationalites().add(pays);

			Pays pays = Splitteur.ajouterPays(lines.get(i));
			pays = InsertionBDD.ajouterPays(entMan, pays, athlete);
			System.out.println("avant ajout" + " " + pays.getMembres());
			System.out.println(athlete);
			// pays.getMembres().add(athlete);
//			if (!pays.getMembres().contains(athlete)) {
//				pays.getMembres().add(athlete);
//			}
			// System.out.println("apres ajout et avant supp doubon"+" "+pays.getMembres());
			// Doublon.supprimerAthlete(pays.getMembres());
			System.out.println("apres supp doubon" + " " + pays.getMembres());

			Equipe equipe = Splitteur.ajouterEquipe(lines.get(i));
			equipe = InsertionBDD.ajouterEquipe(entMan, equipe, athlete);
			// System.out.println("avant ajout"+" "+equipe.getAthletes());
			// System.out.println(athlete);
			// System.out.println(equipe.getAthletes().contains(athlete));
//			if (!equipe.getAthletes().contains(athlete)) {
//				equipe.getAthletes().add(athlete);
//			}
			// equipe.getAthletes().add(athlete);
			// Doublon.supprimerAthlete(equipe.getAthletes());
			// System.out.println("apres supp doubon"+" "+equipe.getAthletes());
			// equipe.getAthletes().add(athlete);
			// Doublon.supprimerAthlete(equipe.getAthletes());

			Sport sport = Splitteur.ajouterSport(lines.get(i));
			sport =  InsertionBDD.ajouterSport(entMan, sport);

			SessionJO jO = Splitteur.ajouterJO(lines.get(i));
			// InsertionBDD.ajouterEquipeJO(entMan, equipe, jO);
			// InsertionBDD.ajouterJO(entMan, jO);
			// InsertionBDD.ajouterJO(entMan, jO, equipe);
			System.out.println(jO.getLibelle() + " " + jO.getId());
			System.out.println(equipe.getLibelle() + " " + equipe.getId());
			jO = InsertionBDD.extraireJO(entMan, jO, sport, equipe);
			// InsertionBDD.ajouterEquipeJO(entMan, equipe, jO);
			// jO.getEquipes();
			System.out.println(jO.getEquipes());
			// jO.getEquipes().add(equipe);//maitre
			// jO.getSports().add(sport);
			System.out.println(lines.get(i));
			System.out.println(jO.getEquipes().contains(equipe));

//			if (!jO.getSports().contains(sport)) {
//				jO.getSports().add(sport);
//			}
//			if (!jO.getEquipes().contains(equipe)) {
//				jO.getEquipes().add(equipe);
//			}

			// Doublon.supprimerEquipe(jO.getEquipes());
			// Doublon.supprimerSport(jO.getSports());
//			for (int j=0; j < jO.getEquipes().size() ;j++)	{
//				if (jO.getEquipes().size()>=2 && jO.getEquipes().get(jO.getEquipes().size()-1).equals(jO.getEquipes().get(jO.getEquipes().size()-2))  ) 	{
//					System.out.println(jO.getEquipes().get(jO.getEquipes().size()-1).equals(jO.getEquipes().get(jO.getEquipes().size()-2)));
//					jO.getEquipes().remove(jO.getEquipes().size()-1);
//				}				
//			}

			System.out.println(jO.getEquipes());
			System.out.println("--------------------------------------------------------");

			Epreuve epreuve = Splitteur.ajouterEpreuve(lines.get(i));
			// epreuve.setSport(sport);
			InsertionBDD.ajouterEpreuve(entMan, epreuve, sport);
		
			Medaille medaille = Splitteur.ajouterMedaille(lines.get(i));
//			medaille.setjO(jO);
//			medaille.setEpreuve(epreuve);
			medaille = InsertionBDD.ajouterMedaille(entMan, medaille, jO, epreuve, athlete);
			//medaille.getChampions().add(athlete);

			trans.commit();
		}

		
		for (int i = 0; i < linesTradEpreuve.size(); i++) {
			trans.begin();
			
//			TraductionEpreuve tradEpreuve = Splitteur.ajouterTrad(linesTradSport.get(i));
//			InsertionBDD.ajouterTradEpreuve(entMan, tradEpreuve);
			
			TraductionEpreuve tradEpreuve = Splitteur.ajouterTradEpreuve(linesTradEpreuve.get(i));
			InsertionBDD.ajouterTradEpreuve(entMan, tradEpreuve);
			
			if (i < linesTradSport.size()) {
			TraductionSport tradSport = Splitteur.ajouterTradSport(linesTradSport.get(i));
			InsertionBDD.ajouterTradSport(entMan, tradSport);
			}	
				
			trans.commit();
		}
		
		// trans.commit();
	}
}