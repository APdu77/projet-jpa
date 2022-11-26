package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRADUCTION")
public class TraductionSport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String libelleInt;
	private String libelleFr;
	
	@ManyToOne
	@JoinColumn(name="SPORT_ID")
	private Sport libSport;

	public TraductionSport() {
		// TODO Auto-generated constructor stub
	}

	public String getLibelleInt() {
		return libelleInt;
	}

	public void setLibelleInt(String libelleInt) {
		this.libelleInt = libelleInt;
	}

	public String getLibelleFr() {
		return libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	public Sport getLibSport() {
		return libSport;
	}

	public void setLibSport(Sport libSport) {
		this.libSport = libSport;
	}

	
	
	
}
