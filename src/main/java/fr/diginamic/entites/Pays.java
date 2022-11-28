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

@Entity
@Table(name="PAYS")
public class Pays {

	@Id
	//@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(unique=true)
	private String cio;
	private String iso;
	private String libelleInt;
	private String libelleFr;
	private boolean obsol;
	
	@ManyToMany
	@JoinTable(name="ATHL_PAYS",
	joinColumns=@JoinColumn(name="CODE_PAYS", referencedColumnName="CIO"),
	inverseJoinColumns=@JoinColumn ( name="ID_ATHL", referencedColumnName="ID")
	)
	private	List<Athlete> membres = new ArrayList<Athlete>();
	
	public Pays() {
		// TODO Auto-generated constructor stub
	}

	public Pays(String cio) {
		super();
		this.cio = cio;
	}
	
	public Pays(String cio, String iso, String libelleInt, String libelleFr, boolean obsol) {
		super();
		this.cio = cio;
		this.iso = iso;
		this.libelleInt = libelleInt;
		this.libelleFr = libelleFr;
		this.obsol = obsol;
	}

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
