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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Représente un Athlete
 * 
 * @author rousseau.DIGINAMIC
 *
 */
@Entity
@Table(name = "ATHLETES")
public class Athlete {

	/** id : int auto-incremente */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** nom : String */
	private String nom;
	/** genre : char (H ou F) */
	private char genre;
	@Column(nullable = true)
	/** age : byte */
	private byte age;
	@Column(nullable = true)
	/** taille : short */
	private short taille;
	@Column(nullable = true)
	/** poids : double */
	private double poids;
	/** cio : String */
	private String cio;

	/**
	 * Relation esclave ManyToMany avec la classe Medaille
	 */
	@ManyToMany(mappedBy = "champions")
	private List<Medaille> medailles = new ArrayList<Medaille>();

	/**
	 * Relation esclave ManyToMany avec la classe Equipe
	 */
	@ManyToMany(mappedBy = "athletes")
	private List<Equipe> equipes;
	
	/**
	 * Constructeur sans argument
	 */
	public Athlete() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur avec arguments
	 */
	public Athlete(String nom, char genre, byte age, short taille, double poids, String cio) {
		super();
		this.nom = nom;
		this.genre = genre;
		this.age = age;
		this.taille = taille;
		this.poids = poids;
		this.cio = cio;
	}

	/**
	 * Redefinition de la methode .equals
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Athlete) && ((Athlete) obj).getNom().equals(nom);
	}

	/**
	 * Redefinition de la methode .toString
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nom;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public char getGenre() {
		return genre;
	}

	public void setGenre(char genre) {
		this.genre = genre;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public short getTaille() {
		return taille;
	}

	public void setTaille(short taille) {
		this.taille = taille;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(short poids) {
		this.poids = poids;
	}

	public String getCio() {
		return cio;
	}

	public void setCio(String cio) {
		this.cio = cio;
	}

	public List<Medaille> getMedailles() {
		return medailles;
	}

}
