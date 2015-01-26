import java.io.*;
import java.util.regex.*;
import java.text.Normalizer; 
import java.text.Normalizer.Form; 

public class Main {
	public static void main(String[] args) {
		System.setProperty( "file.encoding", "UTF-8" );
		System.out.println(System.getProperty("file.encoding"));
	    FileInputStream fis;
		BufferedReader fichier;
		BufferedWriter writerWorld = null;
		BufferedWriter writerAdj = null;
		Pattern pattern;
		Matcher matcher;   
	    	try {
		    fis = new FileInputStream("thes_fr.dat");
		    fichier = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			writerWorld = new BufferedWriter( new FileWriter("word.txt"));
			writerAdj = new BufferedWriter( new FileWriter("adj.txt"));
			pattern = Pattern.compile("(.+\\|1)");
			System.out.println(pattern);
			byte[] buf = new byte[1];
			long startTime = System.currentTimeMillis();
            for (String line; (line = fichier.readLine()) != null; ) {
				matcher = pattern.matcher(line);
					if(matcher.find()){
						String[] split = matcher.group().split("\\|");
						split[0] = translate(split[0]);
						//System.out.println(split[0]);
						if(split[0].length() <= 15)
							writerWorld.write(split[0] + "\n");
						String nextLine = fichier.readLine();
						String[] adj = nextLine.split("\\)\\|");
						adj[1] = translate(adj[1]);
						//System.out.println(adj[1]);
						if(split[0].length() <= 15)
							writerAdj.write(adj[1] + "\n");
						line = "";
					}
			}			
            fichier.close();
			writerWorld.close();
			System.out.println("Temps de lecture avec BufferedInputStream : " + (System.currentTimeMillis() - startTime) + "ms");
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}       
	}
	
    public static String translate(String src) {
		src = src.replace("œ", "oe");
		String tmp = src;
		String normalized = Normalizer.normalize(tmp, Normalizer.Form.NFD);
		//System.out.println(normalized);
		src = normalized.replaceAll("[^\\p{ASCII}]", "");
		//System.out.println(src);
		return src;
    }

}
