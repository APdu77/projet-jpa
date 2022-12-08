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
import fr.diginamic.entites.TraductionEpreuve;
import fr.diginamic.entites.TraductionSport;
import fr.diginamic.services.InsertionBDD;
import fr.diginamic.services.Splitteur;
import fr.diginamic.services.Traitement;

/**
 * Application de traitement des données et de mise en BdD
 * 
 * @param args
 */
public class Application {

	public static void main(String[] args) throws IOException, NumberFormatException {

		/**
		 * Point d'entrée
		 * 
		 * @param args arguments
		 */

		// on extrait les lignes de nos differents fichiers

		// Fichier principal
		List<String> lines = Traitement.extraireLignesUTF("./src/main/resources/athlete_epreuves.csv");

		// Fichiers secondaires
		// fichier de traduction des sports
		List<String> linesTradSport = Traitement.extraireLignesUTF("./src/main/resources/liste des sports.csv");
		// fichier de traduction des epreuves
		List<String> linesTradEpreuve = Traitement.extraireLignesUTF("./src/main/resources/liste_des_epreuves.csv");

		// mise en relation avec la BdD
		EntityManagerFactory entManFac = Persistence.createEntityManagerFactory("app_jo");
		EntityManager entMan = entManFac.createEntityManager();
		EntityTransaction trans = entMan.getTransaction();

		// boucle qui va permettre de traiter toutes les lignes du fichier principal
		for (int i = 70596 ; i < lines.size() ; i++) {
			trans.begin();

			Athlete athlete = Splitteur.ajouterAthlete(lines.get(i));
			athlete = InsertionBDD.ajouterAthlete(entMan, athlete);

			Pays pays = Splitteur.ajouterPays(lines.get(i));
			pays = InsertionBDD.ajouterPays(entMan, pays, athlete);

			Equipe equipe = Splitteur.ajouterEquipe(lines.get(i));
			equipe = InsertionBDD.ajouterEquipe(entMan, equipe, athlete);

			Sport sport = Splitteur.ajouterSport(lines.get(i));
			sport = InsertionBDD.ajouterSport(entMan, sport);

			SessionJO jO = Splitteur.ajouterJO(lines.get(i));
			jO = InsertionBDD.extraireJO(entMan, jO, sport, equipe);

			Epreuve epreuve = Splitteur.ajouterEpreuve(lines.get(i));
			InsertionBDD.ajouterEpreuve(entMan, epreuve, sport);

			Medaille medaille = Splitteur.ajouterMedaille(lines.get(i));
			medaille = InsertionBDD.ajouterMedaille(entMan, medaille, jO, epreuve, athlete);

			trans.commit();
		}

		// boucle pour traiter les fichiers secondaires
		for (int i = 0; i < linesTradEpreuve.size(); i++) {
			trans.begin();

			TraductionEpreuve tradEpreuve = Splitteur.ajouterTradEpreuve(linesTradEpreuve.get(i));
			InsertionBDD.ajouterTradEpreuve(entMan, tradEpreuve);

			// traitement du plus petit (en nombre de lignes) des 2 fichiers secondaires
			if (i < linesTradSport.size()) {
				TraductionSport tradSport = Splitteur.ajouterTradSport(linesTradSport.get(i));
				InsertionBDD.ajouterTradSport(entMan, tradSport);
			
			}
			trans.commit();
		}

	}
}