package fr.diginamic;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.entites.Athlete;
import fr.diginamic.entites.Epreuve;
import fr.diginamic.entites.Equipe;
import fr.diginamic.entites.Medaille;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.SessionJO;
import fr.diginamic.entites.Sport;
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
		List<String> linesPays = Traitement.extraireLignesWin("./src/main/resources/wikipedia-iso-country-codes.csv");
		// Path path = Paths.get("./athlete_epreuves.csv");

		/*
		 * Creation de la liste contenant toutes les lignes du fichier csv
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
		//trans.begin();

		
		/*
		 * Instanciation de la variable qui contiendra tou
		 * arguments
		 */

		for (int i = 0; i < 200; i++) {
			trans.begin();
			Athlete athlete = Splitteur.ajouterAthlete(lines.get(i));
			InsertionBDD.ajouterAthlete(entMan, athlete);

			Equipe equipe = Splitteur.ajouterEquipe(lines.get(i));
			equipe = InsertionBDD.ajouterEquipe(entMan, equipe);
			equipe.getAthletes().add(athlete);
			Doublon.supprimerAthlete(equipe.getAthletes());
			
			Sport sport = Splitteur.ajouterSport(lines.get(i));
			InsertionBDD.ajouterSport(entMan, sport);
			
			SessionJO jO = Splitteur.ajouterJO(lines.get(i));
			//InsertionBDD.ajouterEquipeJO(entMan, equipe, jO);
			//InsertionBDD.ajouterJO(entMan, jO);
			//InsertionBDD.ajouterJO(entMan, jO, equipe);
			System.out.println(jO.getLibelle()+" "+jO.getId());
			System.out.println(equipe.getLibelle()+" "+equipe.getId());
			jO = InsertionBDD.extraireJO(entMan, jO);
			//InsertionBDD.ajouterEquipeJO(entMan, equipe, jO);
			jO.getEquipes();
			System.out.println(jO.getEquipes());
			System.out.println(jO.getEquipes().contains(equipe));
			jO.getEquipes().add(equipe);//maitre
			jO.getSports().add(sport);
			System.out.println(lines.get(i));
			System.out.println(jO.getEquipes());
			System.out.println(jO.getEquipes().contains(equipe));
			System.out.println(jO.getLibelle()+" "+jO.getId());
			System.out.println(equipe.getLibelle()+" "+equipe.getId());

			Doublon.supprimerEquipe(jO.getEquipes());
			
//			for (int j=0; j < jO.getEquipes().size() ;j++)	{
//				if (jO.getEquipes().size()>=2 && jO.getEquipes().get(jO.getEquipes().size()-1).equals(jO.getEquipes().get(jO.getEquipes().size()-2))  ) 	{
//					System.out.println(jO.getEquipes().get(jO.getEquipes().size()-1).equals(jO.getEquipes().get(jO.getEquipes().size()-2)));
//					jO.getEquipes().remove(jO.getEquipes().size()-1);
//				}				
//			}
			
			
			System.out.println(jO.getEquipes());
			System.out.println("--------------------------------------------------------");
			

			//jO.getSports().add(sport);
			
			Epreuve epreuve = Splitteur.ajouterEpreuve(lines.get(i));
			//epreuve.setSport(sport);
			InsertionBDD.ajouterEpreuve(entMan, epreuve, sport);

			

			if (i < linesPays.size()) {
				Pays pays = Splitteur.ajouterPays(linesPays.get(i));
				//pays.getMembres().add(athlete);
				InsertionBDD.ajouterPays(entMan, pays);
			}

			Medaille medaille = Splitteur.ajouterMedaille(lines.get(i));
//			medaille.setjO(jO);
//			medaille.setEpreuve(epreuve);
			InsertionBDD.ajouterMedaille(entMan, medaille, jO, epreuve);

			trans.commit();
		}

		//trans.commit();
	}
}