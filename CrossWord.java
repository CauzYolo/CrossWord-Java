import java.util.ArrayList;
import java.io.*;
import java.util.Random;

public class CrossWord{

	public CrossWord(int taille){

		ArrayList<String> listMotByTaille = getMotsByTailleMax(taille);
		/*for(int i = 0; i < listMotByTaille.size(); i++){
			System.out.println(listMotByTaille.get(i));
		}*/

		int nbCaractMaxi = taille * taille;
		int nbCasesVidesMax = nbCaractMaxi / 3;

		ArrayList<String> listMotsPick = this.getListPickMot(nbCaractMaxi, nbCasesVidesMax, listMotByTaille);
		GestionFichier ges = new GestionFichier();
		listMotsPick = ges.TriBulleDecroissant(listMotsPick);
		
	}

	//public static 


	public static ArrayList<String> getMotsByTailleMax(int taille){

    	ArrayList<String> listMotByTailleMax = new ArrayList<String>();
    	try {
			FileInputStream fis = new FileInputStream("TriCroissant.txt");
    		BufferedReader fichier = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            for (String line; (line = fichier.readLine()) != null; ) {
				if(line.length() <= taille)
					listMotByTailleMax.add(line);
				else 
					return listMotByTailleMax;
			}
			fichier.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return listMotByTailleMax; 
	}

	public String pickMot(ArrayList<String> listMots){

		Random rand = new Random();

		int maxRandom = listMots.size();
		int random = rand.nextInt(maxRandom);

		//System.out.println(random);

		return listMots.get(random);
	}

	public ArrayList<String> getListPickMot(int nbCaractMaxi, int nbCasesVidesMax, ArrayList<String> listMots){

		ArrayList<String> listPickMot = new ArrayList<String>();
		System.out.println("Max : " + nbCaractMaxi + "; Cases Vides : " + nbCasesVidesMax);
		int nbCaractUtilises = 0;
		while(nbCaractMaxi - nbCaractUtilises >= nbCasesVidesMax){
			String motPick = pickMot(listMots);
			nbCaractUtilises += motPick.length();
			listPickMot.add(motPick);
		}
		System.out.println("Util : " + nbCaractUtilises+ "; Cases vides restantes : "+ (nbCaractMaxi - nbCaractUtilises));
		return listPickMot;
	}
}