package fr.diginamic.services;

import java.util.List;

public class Doublon {

	public Doublon() {
		// TODO Auto-generated constructor stub
	}

	public static <T> void eviter(List<T> listes, T objet) {
		// la methode "contains" necessite un Override de la methode "equals"
		if (!listes.contains(objet)) {
			listes.add(objet);
		}
	}

}
