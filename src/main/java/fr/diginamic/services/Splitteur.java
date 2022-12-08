package fr.diginamic.services;

import java.io.IOException;
import java.time.Year;
import java.util.List;

import fr.diginamic.entites.Athlete;
import fr.diginamic.entites.Epreuve;
import fr.diginamic.entites.Equipe;
import fr.diginamic.entites.Medaille;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.SessionJO;
import fr.diginamic.entites.Sport;
import fr.diginamic.entites.TraductionEpreuve;
import fr.diginamic.entites.TraductionSport;

/**
 * Permet de constituer nos objets progressivement a partir des lignes des
 * fichiers
 * 
 * @author Rousseau.DIGINAMIC
 *
 */
public class Splitteur {

	public Splitteur() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Splitte une ligne et retourne un objet Pays a partir de 2 fichiers
	 * 
	 * @param ligne de laquelle on extrait l'objet
	 * @return objet Pays
	 * @throws IOException
	 */
	public static Pays ajouterPays(String ligne1) throws IOException {

		// on stocke chaque ligne du fichier des pays dans une liste
		List<String> lignesPays = Traitement.extraireLignesWin("./src/main/resources/wikipedia-iso-country-codes.csv");

		// On splitte la ligne du fichier principal
		String[] tabJO = ligne1.split(";", 0);

		// on recupere la "portion" qui contient le CIO du Pays
		String cioJO = tabJO[7];
		if (tabJO[7].equals("WIF")) {
			cioJO = "BWI";
		}
		// on initialise les attributs de l'objet
		String fr = null;
		String en = null;
		String iso = null;
		boolean obs = false;

		// On cree une boucle qui va lire chaque ligne du fichier des pays
		for (int i = 0; i < lignesPays.size(); i++) {
			String[] tabPays = lignesPays.get(i).split(";", 0);
			String cioPays = tabPays[0];
			// on cherche la ligne du fichier des pays dont CIO=cioJO
			if (cioJO.equals(cioPays)) {
				// on valorise les attributs de l'objet
				fr = tabPays[1];
				en = tabPays[2];
				iso = tabPays[3];

				if (tabPays[4].equals("O")) {
					obs = true;
				}
				// une fois qu'on a recupere les infos, on peut quitter la boucle
				break;
			}
		}
		// On cree maintenant l'objet avec tous ses attributs et on le retourne
		return new Pays(cioJO, iso, en, fr, obs);
	}

	/**
	 * Splitte une ligne et retourne un objet Athlete
	 * 
	 * @param ligne de laquelle on extrait l'objet
	 * @return objet Athlete
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

		String cio = tab[7];
		if (tab[7].equals("WIF")) {
			cio = "BWI";
		}

		// On cree maintenant notre Athlete avec tous ses attributs et on le retourne
		return new Athlete(nom, genre, age, taille, poids, cio);
	}

	/**
	 * Splitte une ligne et retourne un objet Equipe
	 * 
	 * @param ligne de laquelle on extrait l'objet
	 * @return objet Equipe
	 */
	public static Equipe ajouterEquipe(String ligne) {

		String[] tab = ligne.split(";", 0);

		String nom = tab[6];

		// On cree maintenant l'objet avec tous ses attributs et on le retourne
		return new Equipe(nom);
	}

	/**
	 * Splitte une ligne et retourne un objet SessionJO
	 * 
	 * @param ligne de laquelle on extrait l'objet
	 * @return objet SessionJO
	 */
	public static SessionJO ajouterJO(String ligne) {

		String[] tab = ligne.split(";", 0);
		String nom = tab[8];
		Year annee = Year.parse(tab[9]);
		String saison = tab[10];
		String ville = tab[11];

		// On cree maintenant l'objet avec tous ses attributs et on le retourne
		return new SessionJO(nom, annee, saison, ville);
	}

	/**
	 * Splitte une ligne et retourne un objet Sport
	 * 
	 * @param ligne de laquelle on extrait l'objet
	 * @return objet Sport
	 */
	public static Sport ajouterSport(String ligne) {

		String[] tab = ligne.split(";", 0);
		String nom = tab[12];

		// On cree maintenant l'objet avec tous ses attributs et on le retourne
		return new Sport(nom);
	}

	
	/**
	 * Splitte une ligne et retourne un objet Epreuve
	 * 
	 * @param ligne ligne de laquelle on extrait l'objet
	 * @return objet Epreuve
	 */
	public static Epreuve ajouterEpreuve(String ligne) {

		String[] tab = ligne.split(";", 0);
		String nom = tab[13];

		char categorie = 'H';
		if (nom.contains("Women's")) {
			categorie = 'F';
		} else if (nom.contains("Mixed") == true) {
			categorie = 'M';
		}

		// On cree maintenant l'objet avec tous ses attributs et on le retourne
		Epreuve epreuve = new Epreuve(nom, categorie);
		return epreuve;
	}

	/**
	 * Splitte une ligne et retourne un objet Medaille
	 * 
	 * @param ligne de laquelle on extrait l'objet
	 * @return objet Medaille
	 */
	public static Medaille ajouterMedaille(String ligne) {

		String[] tab = ligne.split(";", 0);

		String type = null;
		if (tab[14].equals("Gold")) {
			type = "Or";
		} else if (tab[14].equals("Silver")) {
			type = "Argent";
		} else if (tab[14].equals("Bronze")) {
			type = "Bronze";
		}
		// On cree maintenant l'objet avec tous ses attributs et on le retourne
		return new Medaille(type);
	}

	/**
	 * Splitte une ligne et retourne un objet TraductionSport
	 * 
	 * @param ligne de laquelle on extrait l'objet
	 * @return objet TraductionSport
	 */
	public static TraductionSport ajouterTradSport(String ligne) {

		// On splitte la ligne du fichier des sports
		String[] mainTab = ligne.split(";", 0);
		// on vqlorise nos variables
		String nomInt = mainTab[0];
		// on gere l'exception de la cellule vide dans le fichier des traduction
		String frTrad = "Patinage de vitesse piste courte";
		if (!mainTab[0].equals("Short Track Speed Skating")) {
			frTrad = mainTab[1];
		}

		// On cree maintenant l'objet avec tous ses attributs et on le retourne
		return new TraductionSport(nomInt, frTrad);
	}

	/**
	 * Splitte une ligne et retourne un objet TraductionEpreuve
	 * 
	 * @param ligne de laquelle on extrait l'objet
	 * @return objet TraductionEpreuve
	 */
	public static TraductionEpreuve ajouterTradEpreuve(String ligne) {

		// On splitte la ligne du fichier des epreuves
		String[] mainTab = ligne.split(";", 0);
		// on valorise nos attributs
		String nomInt = null;
		String frTrad = null;

		// code pour essayer de resoudre le probleme du libelle des epreuves qui
		// n'est pas sous le meme format que dans le fichier pricipal
		char categorie = 0;
		if (mainTab.length != 0) {
			nomInt = mainTab[0];
			frTrad = mainTab[1];

			if (nomInt.contains("Men") || nomInt.contains(" men") || nomInt.contains("Gundersen")) {
				categorie = 'H';
			} else if (nomInt.contains("Women") || nomInt.contains("women")) {
				categorie = 'F';
			} else if (nomInt.contains("Mixed") == true || nomInt.contains("mixed") || nomInt.contains("Eventing")
					|| nomInt.contains("One Open") || nomInt.contains("Two Open")) {
				categorie = 'M';
			}
		}
		//System.out.println(nomInt + "--------" + frTrad + "--------" + categorie);
		
		// On cree maintenant l'objet avec tous ses attributs et on le retourne
		return new TraductionEpreuve(nomInt, frTrad, categorie);

	}

}
