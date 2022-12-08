package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Représente une Traduction de libelle de Sport
 * 
 * @author rousseau.DIGINAMIC
 *
 */
@Entity
@Table(name = "TRADUCTION_SPORT")
public class TraductionSport {

	/** libelle international : String */
	// @Transient
	private String libelleInt;
	/** libelle francais : String */
	@Id
	private String libelleFr;

	/**
	 * Relation maitre ManyTo1 avec la classe Sport
	 */
	@ManyToOne
	@JoinColumn(name = "SPORT_ID")
	private Sport sportTraduit;

	/**
	 * Constructeur sans argument
	 */
	public TraductionSport() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur avec arguments
	 */
	public TraductionSport(String libelleInt, String libelleFr) {
		super();
		this.libelleInt = libelleInt;
		this.libelleFr = libelleFr;
	}

	/**
	 * Getters and Setters
	 * 
	 * @return
	 */
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

	public Sport getSportTraduit() {
		return sportTraduit;
	}

	public void setSportTraduit(Sport sportTraduit) {
		this.sportTraduit = sportTraduit;
	}

}
