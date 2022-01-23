package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is the program that simulates a hiker traversing a mountain. It
 * must be run with a command line argument that includes an input file name.
 * The program parses and validates the argument, and if valid, the input file
 * is read and parsed. Each valid line in the file will be added to the
 * BSTMountain as a node. Once the file is fully parsed, the program sends a
 * hiker down the mountain, and any valid paths are displayed.
 * 
 * Any invalid command line arguments or files will cause the program to display
 * an error message.
 * 
 * 
 * @author Joshua Forlenza
 *
 */

public class MountainHike {

	public static void main(String[] args) {
		//this is a test for Github

		// code for validating command line argument is taken from project 2 class
		// ColorConverted
		// verify that the command line argument exists
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

		// verify that command line argument contains a name of an existing file
		File inputFile = new File(args[0]);
		if (!inputFile.exists()) {
			System.err.println("Error: the file " + inputFile.getAbsolutePath() 
			+ " does not exist.\n");
			System.exit(1);
		}
		if (!inputFile.canRead()) {
			System.err.println("Error: the file " + inputFile.getAbsolutePath() 
			+ " cannot be opened for reading.\n");
			System.exit(1);
		}

		// open the file for reading
		Scanner inRestStops = null;

		try {
			inRestStops = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file " + inputFile.getAbsolutePath() 
			+ " cannot be opened for reading.\n");
			System.exit(1);
		}

		String line = null;
		String[] parseLine = null;

		BSTMountain newMountain = new BSTMountain();

		String curr = null;

		while (inRestStops.hasNextLine()) {
			String label = "";
			int food = 0;
			int raft = 0;
			int axe = 0;
			int fallenTree = 0;
			int river = 0;
			RestStop newRS = null;

			try {
				line = inRestStops.nextLine();
				parseLine = line.split(" ");
				if (parseLine.length == 0) {
					continue;
				}
				label = parseLine[0];

				// supplies processing
				int counter = 1;
				for (int i = 1; i < parseLine.length; i++) {
					curr = parseLine[i];

					// prevents any more supplies from being read once
					// the list of obstacles begins
					if (curr.equals("river")) {
						break;
					}
					if (curr.equals("fallen")) {
						if (parseLine[i + 1].equals("tree")) {
							break;
						}
					}

					if (curr.equals("food")) {
						food++;
					}
					if (curr.equals("raft")) {
						raft++;
					}
					if (curr.equals("axe")) {
						axe++;
					}

					counter++;
				}

				// obstacles processing
				for (int i = counter; i < parseLine.length; i++) {
					curr = parseLine[i];
					if (curr.equals("river")) {
						river++;
					}
					if (curr.equals("fallen")) {
						if (parseLine[i + 1].equals("tree")) {
							fallenTree++;
						}
					}
				}

				newRS = new RestStop(label, food, raft, axe, fallenTree, river);
				newMountain.add(newRS);

			}

			catch (NoSuchElementException ex) {
				// caused by an incomplete or miss-formatted line in the input file
				continue;
			} catch (IndexOutOfBoundsException ex) {
				// caused by [i+1] on line 77
				newRS = new RestStop(label, food, raft, axe, fallenTree, river);
				newMountain.add(newRS);
				continue;
			} catch (IllegalArgumentException ex) {
				// caused by creating a new RestStop object with an empty label
				continue;
			} catch (NullPointerException ex) {
				// caused by a null RestStop being added into the BSTMountatin
				continue;
			}

		}

		newMountain.traverse(new Hiker());

	}

}
