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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="JO")
public class SessionJO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column (name ="EDITION", length = 30, nullable = false , unique = true)
	private String libelle;
	private Year annee;
	private String saison;
	private String ville;
	
	@ManyToMany
	@JoinTable(name="EQUIPE_JO",
	joinColumns=@JoinColumn(name="ID_JO", referencedColumnName="ID"),
	inverseJoinColumns=@JoinColumn ( name="ID_EQU", referencedColumnName="ID")
	)
	private	List<Equipe> equipes = new ArrayList<Equipe>();
	
	@ManyToMany
	@JoinTable(name="SPORT_JO",
	joinColumns=@JoinColumn(name="ID_JO", referencedColumnName="ID"),
	inverseJoinColumns=@JoinColumn ( name="ID_SPO", referencedColumnName="ID")
	)
	private	List<Sport> sports = new ArrayList<Sport>();
	
	@OneToMany(mappedBy="jO")
	private	List<Medaille> recompenses = new ArrayList<Medaille>();
	
	public SessionJO() {
		// TODO Auto-generated constructor stub
	}

	
	public SessionJO(String libelle, Year annee, String saison, String ville) {
		super();
		this.libelle = libelle;
		this.annee = annee;
		this.saison = saison;
		this.ville = ville;
	}


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
