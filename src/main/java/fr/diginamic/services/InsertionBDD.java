package fr.diginamic.services;

import java.util.List;

import javax.persistence.EntityManager;
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

/**
 * Permet d'inserer chaque objet cree par la classe Splitteur en BdD
 * 
 * @author Rousseau.DIGINAMIC
 *
 */
public class InsertionBDD {

	public InsertionBDD() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Si la SessionJO n'existe pas en base,on ajoute une Equipe et un Sport a ses
	 * listes et on la persiste
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
	 * @return objet SessionJO
	 */
	public static SessionJO extraireJO(EntityManager em, SessionJO jo, Sport sport, Equipe equipe) {
		// La methode ne retourne rien car son resultat n'est pas reutilise dans l'Appli

		// requete qui cherche en base la SessionJO mise en param (en JPQL
		// "table=classe" et "colonne=atribut")
		TypedQuery<SessionJO> query = em.createQuery("SELECT j FROM SessionJO j WHERE j.libelle = ?1 ",
				SessionJO.class);
		query.setParameter(1, jo.getLibelle());

		// je stocke ma liste de resultats dans une list de SessionJO
		List<SessionJO> olympicsBase = query.getResultList();

		// si ma liste est vide, ma SessionJO n'existe pas en base, donc je l'y persiste
		if (olympicsBase.isEmpty()) {

			em.persist(jo);

			// si le Sport en param n'est pas dans la liste des Sport de la SessionJO, on
			// l'ajoute apres avoir persister idealememt (mais avant fonctionne aussi)

			Doublon.eviter(jo.getSports(), sport);

			// si l'Equipe en param n'est pas dans la liste des Equipe de la SessionJO, on
			// l'ajoute
			Doublon.eviter(jo.getEquipes(), equipe);

			return jo;
		}
		// si ma liste n'est pas vide, ma SessionJO existe en base,je la retourne
		else {
			// get(0) car le 1er element de la liste existe forcement alors que les autres
			// peuvent ne pas encore exister
			jo = olympicsBase.get(0);
			Doublon.eviter(jo.getSports(), sport);
			Doublon.eviter(jo.getEquipes(), equipe);
			return jo;
		}
	}

	/**
	 * Persiste en base un Athlete s'il n'y est pas deja et le retourne pour qu'il
	 * soit utilisable dans la suite de l'Aplli
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
	 * @return objet Athlete
	 */
	public static Athlete ajouterAthlete(EntityManager em, Athlete athlete) {

		TypedQuery<Athlete> query = em
				.createQuery("SELECT a FROM Athlete a WHERE a.nom = ?1 AND a.genre =?2 AND a.age =?3", Athlete.class);
		query.setParameter(1, athlete.getNom());
		query.setParameter(2, athlete.getGenre());
		query.setParameter(3, athlete.getAge());

		List<Athlete> athletes = query.getResultList();

		if (athletes.isEmpty()) {
			em.persist(athlete);
			return athlete;
		} else {
			return athletes.get(0);
		}
	}

	/**
	 * Persiste un Sport en base s'il n'y est pas deja et le retourne pour qu'il
	 * soit utilisable dans la suite de l'Aplli
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
	 */
	public static Sport ajouterSport(EntityManager em, Sport sport) {

		TypedQuery<Sport> query = em.createQuery("SELECT s FROM Sport s WHERE s.libelleInt = ?1", Sport.class);
		query.setParameter(1, sport.getLibelleInt());

		List<Sport> sports = query.getResultList();

		if (sports.isEmpty()) {
			em.persist(sport);
			return sport;
		} else {
			return sports.get(0);
		}
	}

	/**
	 * Persiste une Epreuve en base si elle n'y est pas deja et lui associe son
	 * Sport
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
	 * @param Sport         auquel l'objet Epreuve appartient
	 */
	public static void ajouterEpreuve(EntityManager em, Epreuve epreuve, Sport sport) {

		TypedQuery<Epreuve> query = em.createQuery("SELECT e FROM Epreuve e WHERE e.libelleInt = ?1", Epreuve.class);
		query.setParameter(1, epreuve.getLibelleInt());

		List<Epreuve> epreuves = query.getResultList();
		if (epreuves.isEmpty()) {
			// j'attribue a Epreuve son Sport apres avoir persiste en BdD
			em.persist(epreuve);
			epreuve.setSport(sport);
		} else {
			int id = epreuves.get(0).getId();
			epreuve.setId(id);
		}
	}

	/**
	 * Persiste un Pays en base s'il n'y est pas deja et le retourne
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
	 * @return objet Pays
	 */
	public static Pays ajouterPays(EntityManager em, Pays pays, Athlete athlete) {

		TypedQuery<Pays> query = em.createQuery("SELECT p FROM Pays p WHERE p.cio = ?1", Pays.class);
		query.setParameter(1, pays.getCio());

		List<Pays> paysliste = query.getResultList();

		if (paysliste.isEmpty()) {
			em.persist(pays);
			Doublon.eviter(pays.getMembres(), athlete);
			return pays;
		} else
			pays = paysliste.get(0);
		Doublon.eviter(pays.getMembres(), athlete);
		return pays;
	}

	/**
	 * Persiste une Equipe en base si elle n'y est pas deja et la retourne
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
	 * @return objet Equipe
	 */
	public static Equipe ajouterEquipe(EntityManager em, Equipe equipe, Athlete athlete) {

		TypedQuery<Equipe> query = em.createQuery("SELECT e FROM Equipe e WHERE e.libelle = ?1", Equipe.class);
		query.setParameter(1, equipe.getLibelle());

		List<Equipe> equipes = query.getResultList();

		if (equipes.isEmpty()) {
			em.persist(equipe);
			Doublon.eviter(equipe.getAthletes(), athlete);
			return equipe;
		} else {
			equipe = equipes.get(0);
			Doublon.eviter(equipe.getAthletes(), athlete);
			return equipe;
		}
	}

	/**
	 * Persiste une Medaille en base si elle n'y est pas deja et la retourne
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param Medaille      qu'on veut persister si elle n'est pas dans la BdD
	 * @param SessionJO     que l'on veut associer a la Medaille
	 * @param Epreuve       que l'on veut associer a la Medaille
	 * @return objet Medaille
	 */
	public static Medaille ajouterMedaille(EntityManager em, Medaille medaille, SessionJO jO, Epreuve epreuve,
			Athlete athlete) {

		TypedQuery<Medaille> query = em.createQuery(
				"SELECT m FROM Medaille m WHERE m.type = ?1 AND m.jO = ?2 AND m.epreuve = ?3", Medaille.class);
		// le caractere unique d'une medaille est defini par la combinaison de ces 3
		// parametres
		query.setParameter(1, medaille.getType());
		query.setParameter(2, jO);
		query.setParameter(3, epreuve);

		List<Medaille> medailles = query.getResultList();

		if (medaille.getType() == null) {
			return medaille;
		} else if (medailles.isEmpty()) {
			// j'attribue a Medaille sa SessionJO & son Epreuve avant de la persister en BdD
			em.persist(medaille);
			medaille.setjO(jO);
			medaille.setEpreuve(epreuve);
			medaille.getChampions().add(athlete);
			return medaille;
		} else
			medaille = medailles.get(0);
		medaille.getChampions().add(athlete);
		return medaille;
	}

	/**
	 * Persiste une TraductionSport en base si elle n'y est pas deja et lui associe
	 * le Sport auquel elle correspond
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param Traduction    que l'on veut persister si elle n'est pas dans la BdD
	 */
	public static void ajouterTradSport(EntityManager em, TraductionSport traductionSport) {

		// infos: fichier1= fichier principal et fichier2=fichier des sports traduits
		// Requete pour savoir si le sport de fichier2 existe dans la table Sport en BdD
		TypedQuery<Sport> query = em.createQuery("SELECT s FROM Sport s WHERE s.libelleInt = ?1", Sport.class);
		query.setParameter(1, traductionSport.getLibelleInt());
		List<Sport> sportBase = query.getResultList();

		// Requete pour savoir si le sport de fichier2 existe dans la table
		// TraductionSport en BdD
		TypedQuery<TraductionSport> query2 = em.createQuery("SELECT t FROM TraductionSport t WHERE t.libelleInt = ?1",
				TraductionSport.class);
		query2.setParameter(1, traductionSport.getLibelleInt());
		List<TraductionSport> sportTradBase = query2.getResultList();

		// on cherche a savoir si le sport existe aux JO (toutes SessionJO confondues)
		// et s'il n'a pas deja ete persiste avec sa traduction en BdD
		if (!sportBase.isEmpty() && sportTradBase.isEmpty()) {
			em.persist(traductionSport);
			traductionSport.setSportTraduit(sportBase.get(0));
		}
	}

	/**
	 * Persiste une TraductionEpreuve en base si elle n'y est pas deja et lui
	 * associe l'Epreuve a laquelle elle correspond, non fonctionnelle car les
	 * libelles des 2 fichiers sont differents seules les quelques libelles qui
	 * coincident seront persistes en BdD (a resoudre)
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param Traduction    que l'on veut persister si elle n'est pas dans la BdD
	 */
	public static void ajouterTradEpreuve(EntityManager em, TraductionEpreuve traductionEpreuve) {

		TypedQuery<Epreuve> query = em.createQuery("SELECT e FROM Epreuve e WHERE e.libelleInt = ?1", Epreuve.class);
		query.setParameter(1, traductionEpreuve.getLibelleInt());
		List<Epreuve> epreuveBase = query.getResultList();

		TypedQuery<TraductionEpreuve> query2 = em
				.createQuery("SELECT t FROM TraductionEpreuve t WHERE t.libelleInt = ?1", TraductionEpreuve.class);
		query2.setParameter(1, traductionEpreuve.getLibelleInt());
		List<TraductionEpreuve> epreuveTradBase = query2.getResultList();

		if (!epreuveBase.isEmpty() && epreuveTradBase.isEmpty()) {
			em.persist(traductionEpreuve);
			traductionEpreuve.setEpreuveTraduite(epreuveBase.get(0));
		}
	}

}
