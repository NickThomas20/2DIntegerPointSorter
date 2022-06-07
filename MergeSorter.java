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
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {
	// Other private instance variables if needed

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter(Point[] pts) {

		super(pts);
		super.algorithm = "Merge Sort";
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		// call the method
		mergeSortRec(points);
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] pts) {
		// Initial check before splitting the array
		if (pts.length <= 1) {
			return;
		}
		int midPoint = pts.length / 2;
		// start from the mid to get the left side
		Point[] lSide = new Point[midPoint];
		// cut in half to get right side
		Point[] rSide = new Point[pts.length - midPoint];
		int i = 0;
		for (int j = 0; j < lSide.length; j++) {
			// copy left side to points array
			lSide[j] = pts[i];
			i++;
		} // end for
		for (int j = 0; j < rSide.length; j++) {
			// copy r side to points array
			rSide[j] = pts[i];
			i++;
		} // end for

		// Call methods for merge sort now
		mergeSortRec(lSide);
		mergeSortRec(rSide);
		merge(pts, lSide, rSide);
	}

	/**
	 * 
	 * @param result
	 * @param lSide
	 * @param rSide  This function is used to merge the two sub arrays back into one
	 *               big sorted array.
	 */
	private void merge(Point[] result, Point[] lSide, Point[] rSide) {
		int left = 0;
		int right = 0;
		int i = 0;
		while (left < lSide.length && right < rSide.length) {
			// compare the two arrays and compare l & r side
			if (lSide[left].compareTo(rSide[right]) < 0) {
				// if conditions are met then copy the completed lhs back into the completed
				// array
				result[i] = lSide[left];
				i++;
				left++;
			} else {
				result[i] = rSide[right];
				i++;
				right++;
			} // end if else
		} // end while
			// loop through left and right hand side to copy left side to array
		while (left < lSide.length) {
			result[i] = lSide[left];
			i++;
			left++;
		}
		while (right < rSide.length) {
			result[i] = rSide[right];
			i++;
			right++;
		}

	}

}
