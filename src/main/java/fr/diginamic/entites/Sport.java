package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SPORTS")
public class Sport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String libelleInt;
	
	@OneToMany(mappedBy="sport")
	private	List<Epreuve> epreuves = new ArrayList<Epreuve>();
	
	@OneToMany(mappedBy="libSport")
	private	List<TraductionSport> langues = new ArrayList<TraductionSport>();
	
	public Sport() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Sport(String libelleInt) {
		super();
		this.libelleInt = libelleInt;
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

	public List<Epreuve> getEpreuves() {
		return epreuves;
	}

	public List<TraductionSport> getLangues() {
		return langues;
	}

	
	
}
