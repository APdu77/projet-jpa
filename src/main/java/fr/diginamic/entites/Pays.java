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
import javax.persistence.Table;

/**
 * Représente un Pays
 * 
 * @author rousseau.DIGINAMIC
 *
 */
@Entity
@Table(name = "PAYS")
public class Pays {

	/** code CIO : String */
	@Id
	// @GeneratedValue(strategy = GenerationType.TABLE)
	@Column(unique = true)
	private String cio;
	/** code ISO : String */
	private String iso;
	/** libelle ineternational : String */
	private String libelleInt;
	/** libelle francais : String */
	private String libelleFr;
	/** code CIO obsolete : booleen (true si obsolete) */
	private boolean obsol;

	/**
	 * Relation maitre ManyToMany avec la classe Athlete
	 */
	@ManyToMany
	@JoinTable(name = "ATHL_PAYS", joinColumns = @JoinColumn(name = "CODE_PAYS", referencedColumnName = "CIO"), inverseJoinColumns = @JoinColumn(name = "ID_ATHL", referencedColumnName = "ID"))
	private List<Athlete> membres = new ArrayList<Athlete>();

	/**
	 * Constructeur sans argument
	 */
	public Pays() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur avec un seul argument
	 */
	public Pays(String cio) {
		super();
		this.cio = cio;
	}

	/**
	 * Constructeur avec arguments
	 */
	public Pays(String cio, String iso, String libelleInt, String libelleFr, boolean obsol) {
		super();
		this.cio = cio;
		this.iso = iso;
		this.libelleInt = libelleInt;
		this.libelleFr = libelleFr;
		this.obsol = obsol;
	}

	/**
	 * Getters and Setters
	 * 
	 * @return
	 */
	public String getCio() {
		return cio;
	}

	public void setCio(String cio) {
		this.cio = cio;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
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

	public boolean isObsol() {
		return obsol;
	}

	public void setObsol(boolean obsol) {
		this.obsol = obsol;
	}

	public List<Athlete> getMembres() {
		return membres;
	}

}
