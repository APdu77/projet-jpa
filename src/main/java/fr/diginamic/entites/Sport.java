package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Représente un Sport
 * 
 * @author rousseau.DIGINAMIC
 *
 */
@Entity
@Table(name="SPORTS")
public class Sport {

	/** id : int auto-incremente */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** libelle international : String */
	@Column(unique=true)
	private String libelleInt;
	
	/**
	 * Relation esclave 1ToMany avec la classe Epreuve
	 */
	@OneToMany(mappedBy="sport")
	private	List<Epreuve> epreuves = new ArrayList<Epreuve>();
	
	/**
	 * Relation esclave 1ToMany avec la classe TraductionSport
	 */
	@OneToMany(mappedBy="sportTraduit")
	private	List<TraductionSport> langues = new ArrayList<TraductionSport>();
	
	/**
	 * Constructeur sans argument
	 */
	public Sport() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur avec arguments
	 */
	public Sport(String libelleInt) {
		super();
		this.libelleInt = libelleInt;
	}

	/**
	 * Redefinition de la methode .equals
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof Sport) && 
				  ((Sport)obj).getLibelleInt().equals(libelleInt);
	}

	/**
	 * Getters and Setters
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelleInt() {
		return libelleInt;
	}

	public void setLibelleInt(String libelleInt) {
		this.libelleInt = libelleInt;
	}

	public List<Epreuve> getEpreuves() {
		return epreuves;
	}

	public List<TraductionSport> getLangues() {
		return langues;
	}

	
	
}
