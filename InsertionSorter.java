package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @nickthomas
 *
 */

/**
 * 
 * This class implements insertion sort.
 *
 */

public class InsertionSorter extends AbstractSorter {
	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts
	 */
	public InsertionSorter(Point[] pts) {
		// call super constructor
		super(pts);
		// set super algorithm
		super.algorithm = "Insertion Sort";

	}

	/**
	 * Perform insertion sort on the array points[] of the parent class
	 * AbstractSorter.
	 */
	@Override
	public void sort() {
		int n = points.length;
		// start out at the second element. Left side is sorted side
		for (int i = 1; i < n; i++) {
			// keep the index and compare
			Point temp = points[i];
			// j goes to the point before i to get ready to compare
			int j = i - 1;
			// check for out of bounds and checks compare to get ready for swap.
			while (j > -1 && (points[j].compareTo(temp) > 0)) {
				points[j + 1] = points[j];
				--j;
			} // end while
			points[j + 1] = temp;
		} // end for
	}
	
//	{
//        int n = arr.length;
//        for (int i = 1; i < n; ++i) {
//            int key = arr[i];
//            int j = i - 1;
// 
//            /* Move elements of arr[0..i-1], that are
//               greater than key, to one position ahead
//               of their current position */
//            while (j >= 0 && arr[j] > key) {
//                arr[j + 1] = arr[j];
//                j = j - 1;
//            }
//            arr[j + 1] = key;
//        }
//    }
}
