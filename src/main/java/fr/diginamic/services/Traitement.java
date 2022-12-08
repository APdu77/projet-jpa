package fr.diginamic.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * Permet de traiter nos fichiers .csv selon leur charset
 * 
 * @author Rousseau.DIGINAMIC
 *
 */
public class Traitement {

	public Traitement() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Extrait les lignes d'un fichier UTF8 et retourne une liste de String
	 *
	 * @param filePath the file path
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<String> extraireLignesUTF (String filePath) throws IOException {

		Path path = Paths.get(filePath);
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

		lines.remove(0);
		
		return lines;
	}
	
	
	/**
	 * Extrait les lignes d'un fichier "win1252" et retourne une liste de String
	 *
	 * @param filePath the file path
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<String> extraireLignesWin (String filePath) throws IOException {

		File file = new File(filePath);
		List<String> lines = FileUtils.readLines(file, "windows-1252");

		lines.remove(0);
		
		return lines;
	}
	
}
