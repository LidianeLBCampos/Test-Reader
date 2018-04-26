package com.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ID3Reader {
	
	static final String FILE_NAME ="ejemplo.mp3";

	public static void main(String[] args) throws FileNotFoundException {
		File mp3 = new File(FILE_NAME);
		long size = mp3.length();
		
		long position = size - 128;
		
		
		
		
		try (FileInputStream fIs = new FileInputStream(mp3)){
			fIs.skip(position);
			
			byte[] infoID3 = new byte[128];
			fIs.read(infoID3);
			
			String data = new String(infoID3);
			
			boolean has = hasTAG(data);
			if(!has) {
				System.out.println("El mp3 no tiene información ID3");
			} else {
				System.out.println("Titulo: " + data.substring(3, 32));
				System.out.println("Artista: " + data.substring(33, 62));
				System.out.println("Album: " + data.substring(63, 91));
				System.out.println("Año: "+data.substring(93, 97));
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
	}
	
	private static boolean hasTAG(String data) {
		String threeFirstCars = data.substring(0, 3);
		return threeFirstCars.equals("TAG");
	}

}
