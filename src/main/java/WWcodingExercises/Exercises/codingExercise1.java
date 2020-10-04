package WWcodingExercises.Exercises;

import java.io.File;


import java.util.Scanner;

public class codingExercise1 {

	public static void main(String[] args) {
		doesFileExist("dictionary.txt");

	}
	
	/***
	 *  Tries to read a file in a existing path and prints the information.
	 * @param path
	 *
	 *  
	 */
	public static void doesFileExist(String path) {
		try {
		// Pass the file path as File instance  and then uses it as a parameter for the Scanner constructor
		Scanner sc = new Scanner(new File(path)); 
		// create a string where will be storing the lines
		String line;

		    // iterate through each line 
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				// used split method which takes regex as parameter  and will return an array.
				String[] key = line.split("-");
				String [] meanings = key[1].split(",");
				System.out.println(key[0]);
				// iterate through meanings 
				for(String mean : meanings) {
					System.out.println(mean);
				}
			
				
			}
			sc.close(); // close sc object 
			
			
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
	}

}
