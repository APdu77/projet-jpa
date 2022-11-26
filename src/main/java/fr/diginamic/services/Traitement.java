package fr.diginamic.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Traitement {

	public Traitement() {
		// TODO Auto-generated constructor stub
	}

	public static List<String> extraireLignesUTF (String filePath) throws IOException {

		Path path = Paths.get(filePath);
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

		lines.remove(0);
		
		// On cree maintenant l'objet avec toutes ses données et on le retourne
		return lines;
	}
	
	public static List<String> extraireLignesWin (String filePath) throws IOException {

		File file = new File(filePath);
		List<String> lines = FileUtils.readLines(file, "windows-1252");

		lines.remove(0);
		
		// On cree maintenant l'objet avec toutes ses données et on le retourne
		return lines;
	}
	
}
