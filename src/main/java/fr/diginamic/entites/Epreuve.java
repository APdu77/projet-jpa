package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EPREUVES")
public class Epreuve {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String libelleInt;
	private char categorie;
	
	@ManyToOne
	@JoinColumn(name="SPORT_ID")
	private Sport sport;
	
	@OneToMany(mappedBy="epreuve")
	private	List<Medaille> distinctions = new ArrayList<Medaille>();
	
	public Epreuve() {
		// TODO Auto-generated constructor stub
	}

	public Epreuve(String libelleInt, char categorie) {
		super();
		this.libelleInt = libelleInt;
		this.categorie = categorie;
	}


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

	
	
}
