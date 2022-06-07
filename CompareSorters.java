package edu.iastate.cs228.hw2;

/**
 *  
 * @nickthomas
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {
		// TODO
		//
		// Conducts multiple rounds of comparison of four sorting algorithms. Within
		// each round,
		// set up scanning as follows:
		//
		// a) If asked to scan random points, calls generateRandomPoints() to initialize
		// an array
		// of random points.
		//
		// b) Reassigns to the array scanners[] (declared below) the references to four
		// new
		// PointScanner objects, which are created using four different values
		// of the Algorithm type: SelectionSort, InsertionSort, MergeSort and QuickSort.
		//
		//

		// this array is made for the 4 sorter methods
		Scanner scan = new Scanner(System.in);
		int trial = 1;
		int choice = 0;
		PointScanner[] scanner = new PointScanner[4];
		
		while (choice != 3) {
			System.out.println(
					"Here are the the 4 preformances ( Quick, Merge, Insertion, Selction).\n keys:  1 (random integers)  2 (file input)  3 (exit)");
			 choice = scan.nextInt();

			System.out.println("Trial " + trial + ": " + choice);
			if (choice == 1) {
				System.out.println("Enter number of random points: ");
				int ran = scan.nextInt();
				// calls random method with new rand obj
				Point[] points = generateRandomPoints(ran, new Random());
				// all of the sorting algorithms called
				scanner[0] = new PointScanner(points, Algorithm.SelectionSort);
				scanner[1] = new PointScanner(points, Algorithm.InsertionSort);
				scanner[2] = new PointScanner(points, Algorithm.MergeSort);
				scanner[3] = new PointScanner(points, Algorithm.QuickSort);
			} // end rand choice
			else if (choice == 2) {
				System.out.println("Points from a file\nFile name: ");
				String fileName = scan.next();
				// all of the sorting algorithms called
				scanner[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanner[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanner[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanner[3] = new PointScanner(fileName, Algorithm.QuickSort);
			} else {
				// get out if its not either of these two
				break;
			}
			System.out.println("algorithm size time (ns)\n----------------------------------");
			// loop through array to print out stats
			for (int i = 0; i < scanner.length; i++) {
				// call method below to print
				scanner[i].scan();
				System.out.println(scanner[i].stats());
			}
			System.out.println("----------------------------------");
			// itterate for each trial
			trial++;
		} // end while
		scan.close();

	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] � [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
		Point[] pts = new Point[numPts];
		// condition needed
		if (numPts < 1) {
			throw new IllegalArgumentException();
		}
		// loop through for random cords
		for (int i = 0; i < pts.length; i++) {
			// x cord
			int x = rand.nextInt(101) - 50;
			// y cord
			int y = rand.nextInt(101) - 50;
			// set for the random points
			pts[i] = new Point(x, y);
		}
		return pts;

	}

}
