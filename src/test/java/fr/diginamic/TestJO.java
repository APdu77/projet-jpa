package fr.diginamic;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import fr.diginamic.entites.Athlete;
import fr.diginamic.entites.Medaille;

public class TestJO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("app_jo");
	private EntityManager em = emf.createEntityManager();
	
	/**
	 * Extraire tous les athletes camerounais qui ont une medaille d'Or au Football
	 */
	@Test
	public void testExtraireAthleteParMedaille() {
		
		TypedQuery<Athlete> query = em.createQuery("SELECT a FROM Athlete a JOIN a.medailles m WHERE a.cio = ?1 AND m.type = ?2 AND m.epreuve = ?3", Athlete.class);
		List<Athlete> athletes = query.getResultList();
		query.setParameter(1,"CMR");
		query.setParameter(2,"Or");
		query.setParameter(3,"Football Men's Football");
		
		assertEquals(18, athletes.size());
		assertEquals("Jouan Patrice Abanda Etong", athletes.get(0).getNom());
		assertEquals("Pierre Nlend Wome", athletes.get(17).getNom());
	}
	
	
	/**
	 * Extraire tous les athletes qui ont une medaille d'Or
	 */
	@Test
	public void testExtraireAthleteMedailleOr() {
		
		TypedQuery<Athlete> query = em.createQuery("SELECT a FROM Athlete a WHERE a.age = ?1 ", Athlete.class);
		List<Athlete> athletesOr = query.getResultList();
		query.setParameter(1,25);
		
		System.out.println(athletesOr.size());
		
//		assertEquals(18, athletes.size());
//		assertEquals("Jouan Patrice Abanda Etong", athletes.get(0).getNom());
//		assertEquals("Pierre Nlend Wome", athletes.get(17).getNom());
	}
	
	
	
}
