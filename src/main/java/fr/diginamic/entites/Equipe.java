package fr.diginamic.entites;

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
import javax.persistence.Table;

/**
 * Représente une Equipe
 * 
 * @author rousseau.DIGINAMIC
 *
 */
@Entity
@Table(name = "EQUIPES")
public class Equipe {

	/** id : int auto-incremente */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** libelle : String */
	@Column(unique = true)
	private String libelle;

	/**
	 * Relation maitre ManyToMany avec la classe Athlete
	 */
	@ManyToMany
	@JoinTable(name = "ATHL_EQU", joinColumns = @JoinColumn(name = "ID_EQU", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_ATHL", referencedColumnName = "ID"))
	private List<Athlete> athletes = new ArrayList<Athlete>();

	/**
	 * Relation esclave ManyToMany avec la classe SessionJO
	 */
	@ManyToMany(mappedBy = "equipes")
	private List<SessionJO> sessionsJO;

	/**
	 * Constructeur sans argument
	 */
	public Equipe() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur avec arguments
	 */
	public Equipe(String libelle) {
		super();
		this.libelle = libelle;
	}

	/**
	 * Redefinition de la methode toString
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return libelle;
	}

	/**
	 * Redefinition de la methode .equals
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof Equipe) && ((Equipe) obj).getLibelle().equals(libelle);
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Athlete> getAthletes() {
		return athletes;
	}

	public void setAthletes(List<Athlete> athletes) {
		this.athletes = athletes;
	}

	public List<SessionJO> getSessionsJO() {
		return sessionsJO;
	}

	public void setSessionsJO(List<SessionJO> sessionsJO) {
		this.sessionsJO = sessionsJO;
	}

}
