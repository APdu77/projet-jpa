package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Représente une Traduction de libelle d'Epreuve
 * 
 * @author rousseau.DIGINAMIC
 *
 */
@Entity
@Table(name = "TRADUCTION_EPREUVE")
public class TraductionEpreuve {

	/** libelle international : String */
	@Id
	private String libelleInt;
	/** libelle francais : String */
	private String libelleFr;
	/** categorie (H, F ou M) : char */
	@Transient
	private char categorie;

	/**
	 * Relation maitre ManyTo1 avec la classe Epreuve
	 */
	@ManyToOne
	@JoinColumn(name = "EPREUVE_ID")
	private Epreuve epreuveTraduite;

	/**
	 * Constructeur sans arguments
	 */
	public TraductionEpreuve() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur avec arguments
	 */
	public TraductionEpreuve(String libelleInt, String libelleFr, char categorie) {
		super();
		this.libelleInt = libelleInt;
		this.libelleFr = libelleFr;
		this.categorie = categorie;
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

	public Epreuve getEpreuveTraduite() {
		return epreuveTraduite;
	}

	public void setEpreuveTraduite(Epreuve epreuveTraduite) {
		this.epreuveTraduite = epreuveTraduite;
	}

	public char getType() {
		return categorie;
	}

	public void setType(char categorie) {
		this.categorie = categorie;
	}

	
	
}
