package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Représente une Epreuve
 * 
 * @author rousseau.DIGINAMIC
 *
 */
@Entity
@Table(name="EPREUVES")
public class Epreuve {

	/** id : int auto-incremente */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** libelle international : String */
	@Column(unique=true)
	private String libelleInt;
	/** categorie : char (H, F ou M pour Mixte) */
	private char categorie;
	
	/**
	 * Relation maitre ManyTo1 avec la classe Sport
	 */
	@ManyToOne
	@JoinColumn(name="SPORT_ID")
	private Sport sport;
	
	/**
	 * Relation esclave 1toMany avec la classe Medaille
	 */
	@OneToMany(mappedBy="epreuve")
	private	List<Medaille> distinctions = new ArrayList<Medaille>();
	
	/**
	 * Relation esclave 1ToMany avec la classe TraductionEpreuve
	 */
	@OneToMany(mappedBy = "epreuveTraduite")
	private List<TraductionEpreuve> langues = new ArrayList<TraductionEpreuve>();
	
	/**
	 * Constructeur sans argument
	 */
	public Epreuve() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur avec arguments
	 */
	public Epreuve(String libelleInt, char categorie) {
		super();
		this.libelleInt = libelleInt;
		this.categorie = categorie;
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

	public char getCategorie() {
		return categorie;
	}

	public void setCategorie(char categorie) {
		this.categorie = categorie;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public List<Medaille> getDistinctions() {
		return distinctions;
	}

	public List<TraductionEpreuve> getLangues() {
		return langues;
	}

	public void setLangues(List<TraductionEpreuve> langues) {
		this.langues = langues;
	}

	
	
}
