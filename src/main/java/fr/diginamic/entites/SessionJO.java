package fr.diginamic.entites;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Représente une Session (edition) des JO
 * 
 * @author rousseau.DIGINAMIC
 *
 */
@Entity
@Table(name = "JO")
public class SessionJO {

	/** id : int auto-incremente */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** libelle : String */
	@Column(name = "EDITION", length = 30, nullable = false, unique = true)
	private String libelle;
	/** annee : Year */
	private Year annee;
	/** saison : String */
	private String saison;
	/** ville : String */
	private String ville;
	
	/**
	 * Relation maitre ManyToMany avec la classe Equipe
	 */
	@ManyToMany
	@JoinTable(name = "EQUIPE_JO", joinColumns = @JoinColumn(name = "ID_JO", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_EQU", referencedColumnName = "ID"))
	private List<Equipe> equipes = new ArrayList<Equipe>();
	
	/**
	 * Relation maitre ManyToMany avec la classe Sport
	 */
	@ManyToMany
	@JoinTable(name = "SPORT_JO", joinColumns = @JoinColumn(name = "ID_JO", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_SPO", referencedColumnName = "ID"))
	private List<Sport> sports = new ArrayList<Sport>();
	
	/**
	 * Relation esclave 1ToMany avec la classe Medaille
	 */
	@OneToMany(mappedBy = "jO")
	private List<Medaille> recompenses = new ArrayList<Medaille>();

	/**
	 * Constructeur sans argument
	 */
	public SessionJO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur avec arguments
	 */
	public SessionJO(String libelle, Year annee, String saison, String ville) {
		super();
		this.libelle = libelle;
		this.annee = annee;
		this.saison = saison;
		this.ville = ville;
	}

	/**
	 * Getters and Setters
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Year getAnnee() {
		return annee;
	}

	public void setAnnee(Year annee) {
		this.annee = annee;
	}

	public String getSaison() {
		return saison;
	}

	public void setSaison(String saison) {
		this.saison = saison;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public List<Sport> getSports() {
		return sports;
	}

	public List<Medaille> getRecompenses() {
		return recompenses;
	}

}
