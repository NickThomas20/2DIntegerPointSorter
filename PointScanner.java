package edu.iastate.cs228.hw2;

import java.io.File;

/**
 * 
 * @nickthomas 
 *
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class PointScanner {
	private Point[] points;

	private Point medianCoordinatePoint; // point whose x and y coordinates are respectively the medians of
											// the x coordinates and y coordinates of those points in the array
											// points[].
	private Algorithm sortingAlgorithm;

	protected long scanTime; // execution time in nanoseconds.

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		sortingAlgorithm = algo;
		// conditionals that are needed
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}
		// copy points
		points = pts;

	}

	/**
	 * This constructor reads points from a file.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
		// TODO
		sortingAlgorithm = algo;
		// used to count # of int
		int counter = 0;

		try {
			// create file obj and scanner
			File file = new File(inputFileName);
			Scanner scan = new Scanner(file);

			while (scan.hasNext()) {
				// logic to increment the counter and * 0 so it only counts by 1
				counter += 1 + (scan.nextInt() * 0);

			} // end while
				// check for odds
			if (counter % 2 != 0) {
				System.out.println(counter);
				throw new InputMismatchException();
			}
			// create a point array. Divide by 2 to get it into pairs.
			points = new Point[counter / 2];
			Scanner scnr = new Scanner(file);
			for (int i = 0; i < points.length; i++) {
				// use next int twice for point x & y
				int x = scnr.nextInt();
				int y = scnr.nextInt();

				// fill the array with points
				points[i] = new Point(x, y);
			} // end for
				// close scanner
			scan.close();
			// close
			scnr.close();
		} catch (FileNotFoundException e) {
			// throw exception if caught
			throw new FileNotFoundException();
		}

	}// end constructor

	/**
	 * Carry out two rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan() {
		// TODO
		AbstractSorter aSorter;
		Long start;
		Long finish;

		// create an object to be referenced by aSorter according to sortingAlgorithm.
		// for each of the two
		// rounds of sorting, have aSorter do the following:
		//
		// a) call setComparator() with an argument 0 or 1.
		//
		// b) call sort().
		//
		// c) use a new Point object to store the coordinates of the
		// medianCoordinatePoint
		//
		// d) set the medianCoordinatePoint reference to the object with the correct
		// coordinates.
		//
		// e) sum up the times spent on the two sorting rounds and set the instance
		// variable scanTime.
		AbstractSorter aSort = null;

		if (sortingAlgorithm == Algorithm.InsertionSort) {
			aSort = new InsertionSorter(points);
		}
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			aSort = new SelectionSorter(points);
		}
		if (sortingAlgorithm == Algorithm.MergeSort) {
			aSort = new MergeSorter(points);
		}
		if (sortingAlgorithm == Algorithm.QuickSort) {
			aSort = new QuickSorter(points);
		}

		// 1 = y
		aSort.setComparator(1);
		// start before program
		start = System.nanoTime();
		aSort.sort();
		// end after sort
		finish = System.nanoTime();
		// calculate the difference
		Long ySec = finish - start;
		int y = aSort.getMedian().getY();

		// 0 = x
		aSort.setComparator(0);
		// start before program
		start = System.nanoTime();
		aSort.sort();
		// end after sort
		finish = System.nanoTime();
		// do this to calculate the difference
		Long xSec = finish - start;
		int x = aSort.getMedian().getX();
		// new obj made
		medianCoordinatePoint = new Point(x, y);
		// adds them up to get the total time
		scanTime = ySec + xSec;

	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats() {
		// create conditionals to check for each algorithm to give the stats out
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			return "Selection Sort     " + points.length + " " + scanTime;
		}
		if (sortingAlgorithm == Algorithm.InsertionSort) {
			return "Insertion Sort     " + points.length + " " + scanTime;
		}
		if (sortingAlgorithm == Algorithm.MergeSort) {
			return "Merge Sort         " + points.length + " " + scanTime;
		}
		if (sortingAlgorithm == Algorithm.QuickSort) {
			return "Quick Sort         " + points.length + " " + scanTime;
		}
		return null;

	}

	/**
	 * Write MCP after a call to scan(), in the format "MCP: (x, y)" The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 */
	@Override
	public String toString() {

		String retrn = "MCP: " + "(" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";

		return retrn;

	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException {

		String file = "/Users/nickthomas/Desktop/test1.txt";
		try {
			FileWriter fWriter = new FileWriter(file);
			// make file a string
			fWriter.write(medianCoordinatePoint.toString());
			// close file writer
			fWriter.close();

		} catch (Exception e) {
			// throw exception if caught
			throw new FileNotFoundException();
		}

	}

}
