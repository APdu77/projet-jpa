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

@Entity
@Table(name="SPORTS")
public class Sport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique=true)
	private String libelleInt;
	
	@OneToMany(mappedBy="sport")
	private	List<Epreuve> epreuves = new ArrayList<Epreuve>();
	
	@OneToMany(mappedBy="tradSport")
	private	List<TraductionSport> langues = new ArrayList<TraductionSport>();
	
	public Sport() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Sport(String libelleInt) {
		super();
		this.libelleInt = libelleInt;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof Sport) && 
				  ((Sport)obj).getLibelleInt().equals(libelleInt);
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
