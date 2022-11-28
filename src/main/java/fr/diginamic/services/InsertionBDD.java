package fr.diginamic.services;

import java.awt.JobAttributes;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.engine.query.spi.ReturnMetadata;

import fr.diginamic.entites.Athlete;
import fr.diginamic.entites.Epreuve;
import fr.diginamic.entites.Equipe;
import fr.diginamic.entites.Medaille;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.SessionJO;
import fr.diginamic.entites.Sport;

/**
 * Permet d'inserer chaque objet cree par la classe Splitteur en BdD fichiers
 * 
 * @author Rousseau.DIGINAMIC
 *
 */
public class InsertionBDD {

	public InsertionBDD() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Persiste en base une SessionJO s'elle n'y est pas deja et la retourne
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
	 */
	public static SessionJO extraireJO(EntityManager em, SessionJO jo) {

		// requete qui cherche en base la SessionJO mise en param
		TypedQuery<SessionJO> query = em.createQuery("SELECT j FROM SessionJO j WHERE j.libelle = ?1 ",
				SessionJO.class);
		query.setParameter(1, jo.getLibelle());
		// je stocke ma liste de resultats dans une list de SessionJO
		List<SessionJO> olympicsBase = query.getResultList();
		// si ma liste est vide, ma SessionJO n'existe pas en base, donc je l'y persiste
		if (olympicsBase.isEmpty()) {
			em.persist(jo);
			return jo;
		}
		// si ma liste n'est pas vide, ma SessionJO existe en base,je retourne donc cet
		// objet
		else {
			// get(0) car le 1er element de la liste existe forcement alors que les autres
			// peuvent ne pas encore exister
			return olympicsBase.get(0);
		}
	}

//	public static void ajouterJO(EntityManager em, SessionJO jo, Equipe equipe) {
//
//		// requete qui cherche en base la SessionJO mise en param
//		TypedQuery<SessionJO> query = em.createQuery("SELECT j FROM SessionJO j WHERE j.libelle = ?1 ",
//				SessionJO.class);
//		query.setParameter(1, jo.getLibelle());
//		// je range ma liste de resultats dans une list de SessionJO
//		List<SessionJO> olympicsBase = query.getResultList();
//
//		// si ma liste est vide, ma SessionJO n'existe pas en base, donc je l'y persiste
//		if (olympicsBase.isEmpty()) {
//			em.persist(jo);
//		}
//
//		// si ma liste n'est pas vide, ma SessionJO existe en base,je recupere son id
//		else {
//			// get(0) car le 1er element de la liste existe forcement alors que les autres
//			// peuvent ne pas encore exister
//			int id = olympicsBase.get(0).getId();
//			// j'attribue donc le bon id a ma SessionJO mise en param
//			jo.setId(id);
//		}
//		//jo.getEquipes().add(equipe);
//	}
	
	
	/**
	 * Persiste en base un Athlete s'il n'y est pas deja et le retourne
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
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
	 * Persiste un Sport en base s'il n'y est pas deja et le retourne
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
	 */
	public static void ajouterSport(EntityManager em, Sport sport) {

		TypedQuery<Sport> query = em.createQuery("SELECT s FROM Sport s WHERE s.libelleInt = ?1", Sport.class);
		query.setParameter(1, sport.getLibelleInt());

		List<Sport> sports = query.getResultList();

		if (sports.isEmpty()) {
			em.persist(sport);
		} else {
			int id = sports.get(0).getId();
			sport.setId(id);
		}
	}

	
	/**
	 * Persiste une Epreuve en base si elle n'y est pas deja et lui associe son Sport
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
	 * @param Sport auquel l'objet Epreuve appartient
	 */
	public static void ajouterEpreuve(EntityManager em, Epreuve epreuve, Sport sport) {
		epreuve.setSport(sport);
		TypedQuery<Epreuve> query = em.createQuery("SELECT e FROM Epreuve e WHERE e.libelleInt = ?1", Epreuve.class);
		query.setParameter(1, epreuve.getLibelleInt());

		List<Epreuve> epreuves = query.getResultList();
		if (epreuves.isEmpty()) {
			em.persist(epreuve);
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
	 */
	public static Pays ajouterPays(EntityManager em, Pays pays) {

		TypedQuery<Pays> query = em.createQuery("SELECT p FROM Pays p WHERE p.cio = ?1", Pays.class);
		query.setParameter(1, pays.getCio());

		List<Pays> paysliste = query.getResultList();

		if (paysliste.isEmpty()) {
			em.persist(pays);
			return pays;
		} else
			return paysliste.get(0);
	}

	
	/**
	 * Persiste une Equipe en base si elle n'y est pas deja et la retourne
	 * 
	 * @param EntityManager en relation avec la BdD
	 * @param objet         qu'on veut persister si elle n'est pas dans la BdD
	 */
	public static Equipe ajouterEquipe(EntityManager em, Equipe equipe) {

		TypedQuery<Equipe> query = em.createQuery("SELECT e FROM Equipe e WHERE e.libelle = ?1", Equipe.class);
		query.setParameter(1, equipe.getLibelle());

		List<Equipe> equipes = query.getResultList();

		if (equipes.isEmpty()) {
			em.persist(equipe);
			return equipe;
		} else {
			return equipes.get(0);
		}
	}

	public static Medaille ajouterMedaille(EntityManager em, Medaille medaille, SessionJO jO, Epreuve epreuve) {
		medaille.setjO(jO);
		medaille.setEpreuve(epreuve);

		TypedQuery<Medaille> query = em.createQuery(
				"SELECT m FROM Medaille m WHERE m.type = ?1 AND m.jO = ?2 AND m.epreuve = ?3", Medaille.class);
		query.setParameter(1, medaille.getType());
		query.setParameter(2, jO);
		query.setParameter(3, epreuve);

		List<Medaille> medailles = query.getResultList();

		if (medailles.isEmpty() && medaille.getType() == null) {
			// em.persist(medaille);
			return medaille;
		} else if (medailles.isEmpty()) {
			em.persist(medaille);
			return medaille;
		} else
			return medailles.get(0);

	}

}
