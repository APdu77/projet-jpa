package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TRADUCTION_EPREUVE")
public class TraductionEpreuve {
	
	@Id
	private String libelleInt;
	private String libelleFr;
	private char categorie;
	
	@ManyToOne
	@JoinColumn(name="EPREUVE_ID")
	private Epreuve tradEpreuve;

	@OneToMany(mappedBy="tradEpreuve")
	private	List<TraductionEpreuve> langues = new ArrayList<TraductionEpreuve>();

	
	public TraductionEpreuve() {
		// TODO Auto-generated constructor stub
	}

	public TraductionEpreuve(String libelleInt, String libelleFr, char categorie) {
		super();
		this.libelleInt = libelleInt;
		this.libelleFr = libelleFr;
		this.categorie = categorie;
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

	public Epreuve getTradEpreuve() {
		return tradEpreuve;
	}

	public void setTradEpreuve(Epreuve tradEpreuve) {
		this.tradEpreuve = tradEpreuve;
	}

	public char getType() {
		return categorie;
	}

	public void setType(char categorie) {
		this.categorie = categorie;
	}

}
