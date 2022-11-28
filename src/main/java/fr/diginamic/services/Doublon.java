package fr.diginamic.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Any;
import org.hibernate.type.AnyType;

import fr.diginamic.entites.Athlete;
import fr.diginamic.entites.Equipe;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.Sport;

public class Doublon {

	public Doublon() {
		// TODO Auto-generated constructor stub
	}

	public static void supprimerEquipe(List<Equipe> equipes) {

		for (int j = 2; j <= equipes.size(); j++) {
			if (equipes.size() >= 2 && equipes.get(equipes.size() - 1).equals(equipes.get(equipes.size() - j))) {
				System.out.println(equipes.get(equipes.size() - 1).equals(equipes.get(equipes.size() - j)));
				equipes.remove(equipes.size() - 1);
			}
		}
	}

	public static void supprimerAthlete(List<Athlete> athletes) {

		for (int j = 2; j <= athletes.size(); j++) {
			if (athletes.size() >= 2 && athletes.get(athletes.size() - 1).equals(athletes.get(athletes.size() - j))) {
				athletes.remove(athletes.size() - 1);
			}
		}
	}

	public static void supprimerSport(List<Sport> sports) {

		for (int j = 2; j <= sports.size(); j++) {
			if (sports.size() >= 2 && sports.get(sports.size() - 1).equals(sports.get(sports.size() - j))) {
				sports.remove(sports.size() - 1);
			}
		}
	}

	public static void supprimerPays(List<Pays> pays) {

		for (int j = 2; j < pays.size(); j++) {
			if (pays.size() >= 2 && pays.get(pays.size() - 1).equals(pays.get(pays.size() - j))) {
				pays.remove(pays.size() - 1);
			}

		}
	}

}
