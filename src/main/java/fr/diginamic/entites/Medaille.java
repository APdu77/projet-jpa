package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Représente une Medaille
 * 
 * @author rousseau.DIGINAMIC
 *
 */
@Entity
@Table(name = "MEDAILLES")
public class Medaille {

	/** id : int auto-incremente */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** type (Or, Argent ou Bronze) : String */
	// @Column(unique=true)
	private String type;

	/**
	 * Relation maitre ManyToMany avec la classe Athlete
	 */
	@ManyToMany
	@JoinTable(name = "PODIUM", joinColumns = @JoinColumn(name = "ID_MED", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_ATHL", referencedColumnName = "ID"))
	private List<Athlete> champions = new ArrayList<Athlete>();

	/**
	 * Relation maitre ManyTo1 avec la classe Epreuve
	 */
	@ManyToOne
	@JoinColumn(name = "EPREUVE_ID")
	private Epreuve epreuve;

	/**
	 * Relation maitre ManyTo1 avec la classe SessioJO
	 */
	@ManyToOne
	@JoinColumn(name = "JO_ID")
	private SessionJO jO;

	/**
	 * Constructeur sans argument
	 */
	public Medaille() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur avec arguments
	 */
	public Medaille(String type) {
		super();
		this.type = type;
	}

	/**
	 * Getters and Setters
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	public SessionJO getjO() {
		return jO;
	}

	public void setjO(SessionJO jO) {
		this.jO = jO;
	}

	public List<Athlete> getChampions() {
		return champions;
	}

}
