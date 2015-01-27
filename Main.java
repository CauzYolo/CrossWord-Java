import java.io.*;
import java.util.regex.*;
import java.text.Normalizer; 
import java.text.Normalizer.Form; 
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		System.setProperty( "file.encoding", "UTF-8" );
		GestionFichier ges = new GestionFichier();
		int nbMotsDictionnaire = ges.getNbMotsDictionnaire();
		int nbMotsImportes = ges.getNbMotsImportes();
		System.out.println("Nombres de mots dans le dictionnaire : " + nbMotsDictionnaire);
		System.out.println("Nombres de mots dans le fichier des mots importés : " + nbMotsImportes);

		if(nbMotsImportes != nbMotsDictionnaire){
			ges.MaJDictionnaire();
		}

		ArrayList<String> listMots = ges.Tri(nbMotsImportes, "croissant");
		ges.EcritureFichier("TriCroissant.txt", listMots);
		ArrayList<String> listMots2 = ges.Tri(nbMotsImportes, "decroissant");
		ges.EcritureFichier("TriDecroissant.txt", listMots2);



	}

}
