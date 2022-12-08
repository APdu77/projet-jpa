package fr.diginamic.services;

import java.util.List;

/**
 * Permet d'ajouter un Objet generique a une liste d'Objets (du meme type
 * que Objet) si la liste ne contient pas deja cet Objet
 * 
 * @author Rousseau.DIGINAMIC
 *
 */
public class Doublon {
	
	/**
	 * Ajoute un Objet dans une liste s'il n'y est pas deja
	 *
	 * @param <T> the generic type
	 * @param liste d'objets Objet
	 * @param objet Objet
	 */
	public static <T> void eviter(List<T> listes, T objet) {
		// la methode "contains" necessite un Override de la methode .equals de la
		// classe
		if (!listes.contains(objet)) {
			listes.add(objet);
		}
	}

}
