package WWcodingExercises.Exercises;


import java.util.Arrays;
import java.util.Random;


public class codingExercise3 {

	public static void main(String[] args) {
		
		System.out.println(  " nth smallest is: " + findNthSmallest(1));

	}

	
	/***
	 *  Creates 500 random numbers and identify the smallest number at the nth position. 
	 * @param nth
	 * @return the number at the nth position
	 */
	public static int findNthSmallest(int nth) {
		// Checking for 0
		if(nth == 0) {
			System.out.println("nth cannot be 0");
			return 0;
		}
		// Instance of Random class
		Random rand = new Random();
		

		// Create an array size 500 to store the numbers 
		int[] numbArr = new int[500];
		System.out.println("-------------Random numbers---------");
		// Populate the array with random numbers
		for (int i = 0; i < numbArr.length; i++) {
			// I will set a boundary of 1000
			numbArr[i] = rand.nextInt(1000);
			System.out.print(numbArr[i]+ ", ");
			
		}
		// Use Java build-in function .sort() to sort the array
		Arrays.sort(numbArr);
		System.out.println("end of randoms");
		System.out.println("--------------nth-------------------");
		
		// Returns the nth smallest number by subtracting one position down since array start a 0
		return numbArr[nth - 1 ];
	}

}
