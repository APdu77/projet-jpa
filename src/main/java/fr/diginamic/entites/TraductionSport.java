package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TRADUCTION_SPORT")
public class TraductionSport {
	
	@Transient
	private String libelleInt;
	@Id
	private String libelleFr;
	
	@ManyToOne
	@JoinColumn(name="SPORT_ID")
	private Sport tradSport;

	public TraductionSport() {
		// TODO Auto-generated constructor stub
	}

	public TraductionSport(String libelleInt, String libelleFr) {
		super();
		this.libelleInt = libelleInt;
		this.libelleFr = libelleFr;
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

	public Sport getTradSport() {
		return tradSport;
	}

	public void setTradSport(Sport tradSport) {
		this.tradSport = tradSport;
	}

	
	
	
}
