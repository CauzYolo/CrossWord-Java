import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import java.util.regex.*;

public class Main {
	public static void main(String[] args) {
		System.setProperty( "file.encoding", "UTF-8" );
		System.out.println(System.getProperty("file.encoding"));
	    FileInputStream fis;
	    FileOutputStream fos;
		BufferedInputStream bis;   
		BufferedReader fichier;
		BufferedOutputStream bos;  
		BufferedWriter writer = null;
		Pattern pattern;
		Matcher matcher;   
		int bytesRead=0;
	    	try {
		    //bis = new BufferedInputStream(new FileInputStream(new File("thes_fr.dat")));
		    fis = new FileInputStream("thes_fr.dat");
		    fichier = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
		    bis = new BufferedInputStream(new FileInputStream(new File("thes_fr.dat")));
			//fos = new FileOutputStream(new File("out.dat"));
			writer = new BufferedWriter( new FileWriter("word.txt"));
			//pattern = Pattern.compile("^([.]+[\\|]{1}[1]{1})$");
			pattern = Pattern.compile("(.+\\|1)");
			System.out.println(pattern);
			byte[] buf = new byte[1];
			long startTime = System.currentTimeMillis();
			String str = "";
			System.out.println(bis.available());
			/*while((bytesRead = bis.read(buf)) != -1){
				//str += bis.read();
				str += new String(buf, 0, bytesRead);
				//System.out.println(str);
				matcher = pattern.matcher(str);
				if(matcher.find()){
					System.out.println(matcher.group());
					String[] split = matcher.group().split("\\|");
					System.out.println(split[0]);
					writer.write(split[0] + "\n");
					str = "";
					matcher.reset();
				}
					
					//str += (char)bit;		

            }*/
			
            System.out.println("Out");
            buf = new byte[32];
            str = "";
            
            for (String line; (line = fichier.readLine()) != null; ) {
				//System.out.println(line);
				matcher = pattern.matcher(line);
					if(matcher.find()){
						System.out.println(matcher.group());
						String[] split = matcher.group().split("\\|");
						System.out.println(split[0]);
						writer.write(split[0] + "\n");
						line = "";
						//matcher.reset();
					}
			}
            
            /*do{
				str = fichier.readLine();
				System.out.println(str);
				if(str != null){
					matcher = pattern.matcher(str);
					if(matcher.find()){
						System.out.println(matcher.group());
						String[] split = matcher.group().split("\\|");
						System.out.println(split[0]);
						writer.write(split[0] + "\n");
						str = "";
						//matcher.reset();
					}
				}
			}
			while(fichier.read() != -1);*/
			
			
            fichier.close();
            bis.close();
			//fos.close();
			writer.close();
			System.out.println("Temps de lecture avec BufferedInputStream : " + (System.currentTimeMillis() - startTime) + "ms");
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}       
	}

}
