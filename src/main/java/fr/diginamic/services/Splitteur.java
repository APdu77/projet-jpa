package fr.diginamic.services;

import java.time.Year;

import fr.diginamic.entites.Athlete;
import fr.diginamic.entites.Epreuve;
import fr.diginamic.entites.Equipe;
import fr.diginamic.entites.Medaille;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.SessionJO;
import fr.diginamic.entites.Sport;

public class Splitteur {

	public Splitteur() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Ajoute une ligne contenant un objet Athlete
	 * 
	 * @param ligne ligne de laquelle on extrait l'objet
	 */
	public static Athlete ajouterAthlete(String ligne) {

		String[] tab = ligne.split(";", 0);

		String nom = tab[1];

		char genre = tab[2].charAt(0);

		byte age = 0;
		if (!tab[3].equals("NA")) {
			age = Byte.parseByte(tab[3]);
		}

		short taille = 0;
		if (!tab[4].equals("NA")) {
			taille = Short.parseShort(tab[4]);
		}

		double poids = 0;
		if (!tab[5].equals("NA")) {
			poids = Double.parseDouble(tab[5]);
		}

		// On cree maintenant la ville avec toutes ses données
		return new Athlete(nom, genre, age, taille, poids);

	}

	public static Equipe ajouterEquipe(String ligne) {

		String[] tab = ligne.split(";", 0);

		String nom = tab[6];

		// On cree maintenant l'objet avec toutes ses données et on le retourne
		return new Equipe(nom);
	}

	/**
	 * Ajoute une ligne contenant un objet SessionJO
	 * 
	 * @param ligne ligne de laquelle on extrait l'objet
	 */
	public static SessionJO ajouterJO(String ligne) {

		String[] tab = ligne.split(";", 0);

		String nom = tab[8];

		Year annee = Year.parse(tab[9]);

		String saison = tab[10];

		String ville = tab[11];

		// On cree maintenant l'objet avec toutes ses données et on le retourne
		return new SessionJO(nom, annee, saison, ville);
	}

	/**
	 * Ajoute une ligne contenant un objet Sport
	 * 
	 * @param ligne ligne de laquelle on extrait l'objet
	 */
	public static Sport ajouterSport(String ligne) {

		String[] tab = ligne.split(";", 0);

		String nom = tab[12];

		// On cree maintenant l'objet avec toutes ses données et on le retourne
		return new Sport(nom);
	}

	/**
	 * Ajoute une ligne contenant un objet Sport
	 * 
	 * @param ligne ligne de laquelle on extrait l'objet
	 */
	public static Pays ajouterPays(String ligne1) {

		String[] tab = ligne1.split(";", 0);

		String cio = null;
		// if (tab[1].equals("BWI")) {
		// cio = "WIF" ;
		// }
		// else
		cio = tab[0];

		String fr = tab[1];
		String en = tab[2];
		String iso = tab[3];
		boolean obs = false;
		if (tab[4].equals("O")) {
			obs = true;
		}

		// On cree maintenant l'objet avec toutes ses données et on le retourne
		return new Pays(cio, iso, en, fr, obs);
	}

	/**
	 * Ajoute une ligne contenant un objet Epreuve
	 * 
	 * @param ligne ligne de laquelle on extrait l'objet
	 */
	public static Epreuve ajouterEpreuve(String ligne) {

		String[] tab = ligne.split(";", 0);

		String nom = tab[13];
		
		char categorie = 'H';
		if (nom.contains("Women's")==true)	{
			categorie = 'F';
		}
		else if (nom.contains("Mixed")==true)	{
			categorie = 'M';
		}

		// On cree maintenant l'objet avec toutes ses données et on le retourne
		Epreuve epreuve = new Epreuve(nom, categorie);
		return epreuve;
	}

	public static Medaille ajouterMedaille(String ligne) {

		String[] tab = ligne.split(";", 0);

		String type = "NA";
		
		if (tab[14].equals("Gold"))	{
			type = "Or";
		}
		else if (tab[14].equals("Silver"))	{
			type = "Argent";
		}
		else if (tab[14].equals("Bronze"))	{
			type = "Bronze";
		}
		// On cree maintenant l'objet avec toutes ses données et on le retourne
		return new Medaille(type);
	}

	
}
