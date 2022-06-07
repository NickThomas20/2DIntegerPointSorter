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
 * This class implements selection sort.
 *
 */

public class SelectionSorter extends AbstractSorter {
	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts
	 */
	public SelectionSorter(Point[] pts) {

		super(pts);
		super.algorithm = "Selection Sort";
	}

	/**
	 * Apply selection sort on the array points[] of the parent class
	 * AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		int n = points.length;

		for (int i = 0; i < n; i++) {
			// set the min index to the current point
			int minIndex = i;

			// loop through the index to add one and compare points
			for (int j = i + 1; j < n; j++) {
				// compare j to the current min index
				if (points[j].compareTo(points[minIndex]) < 0) {

					// j gets set also
					minIndex = j;
				}

			} // end j for
				// use the swap method
			this.swap(i, minIndex);
		} // end i for

	}
}
