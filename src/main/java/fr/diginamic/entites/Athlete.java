package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ATHLETES")
public class Athlete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private char genre;
	@Column(nullable = true)
	private byte age;
	@Column(nullable = true)
	private short taille;	
	@Column(nullable = true)
	private double poids;
	
	@ManyToMany(mappedBy="champions")
	private	List<Medaille> medailles = new ArrayList<Medaille>();
	
	public Athlete() {
		// TODO Auto-generated constructor stub
	}
	
	public Athlete(String nom, char genre, byte age, short taille, double poids) {
		super();
		this.nom = nom;
		this.genre = genre;
		this.age = age;
		this.taille = taille;
		this.poids = poids;
	}
	
	@Override
	public boolean equals(Object obj) {
	      return (obj instanceof Athlete) && 
		  ((Athlete)obj).getNom().equals(nom) && 
		  ((Athlete)obj).getGenre()==genre &&
		  ((Athlete)obj).getAge()==age &&
		  ((Athlete)obj).getTaille()==taille &&
		  ((Athlete)obj).getPoids()==poids ;
	    }

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

	public List<Medaille> getMedailles() {
		return medailles;
	}

	
	
}
