package learning;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.*; // for DatatypeConverter; requires Java 6 or JAXB 1.0

public class DigestThread extends Thread {
	private String filename;
	public DigestThread(String filename) {
		this. filename = filename;
	}
	
	@Override
	public void run() {
		try {
			FileInputStream in = new FileInputStream(filename);
			MessageDigest sha = MessageDigest. getInstance("SHA-256" );
			DigestInputStream din = new DigestInputStream(in, sha);
			while (din. read() != - 1) ;
			din. close();
			byte[] digest = sha. digest();
			StringBuilder result = new StringBuilder(filename);
			result.append(": " );
			result.append(DatatypeConverter. printHexBinary(digest));
			System.out.println(result);
		} 
		catch (IOException ex) {
			 System.err.println(ex);
		} 
		catch (NoSuchAlgorithmException ex) {
			 System.err.println(ex);
		}
	}
	
	public static void main(String[] args) {
			 
		//String path = "C:\\Users\\thucj_000\\Dropbox\\car";
		String path = "C:\\Users\\thucj_000\\Dropbox";
		
		List<Path> result = new ArrayList<Path>();
		try {
			result = Files.walk(Paths.get(path)).filter(Files::isRegularFile).collect(Collectors.toList());
			System.out.println("There are " + result.size() + " files altogether.");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}		 
		for (Path file : result) {
			String filename = file.toString(); 
			Thread t = new DigestThread(filename);
			t.start();
		}
	}
}
