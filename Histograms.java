package week4;

import acm.program.*;
import acm.util.*;
import java.io.*;

public class Histograms extends ConsoleProgram {

	public void run() {
		initHistogram();
		readScoresIntoHistogram();
		printHistogram();
	}

	/* Initializes the histogram array */
	private void initHistogram() {
		histogramArray = new int[11];
		for (int i = 0; i < histogramArray.length; i++) {
			histogramArray[i] = 0;
		}
	}

	/* Reads the exam scores, updating the histogram */
	private void readScoresIntoHistogram() {
		try {
			BufferedReader rd = new BufferedReader(new FileReader(DATA_FILE));
			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				line = line.trim();
				int score = Integer.parseInt(line);
				if (score < 0 || score > 100) {
					throw new ErrorException("That score is out of range");
				} else {
					int range = score / 10;
					histogramArray[range]++;
				}
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/* Displays the histogram */
	private void printHistogram() {
		for (int range = 0; range <= 10; range++) {
			String label;
			if (range == 0) {
				label = "00-09";
			} else if (range == 10) {
				label = " 100";
			} else {
				label = (10 * range) + "-" + (10 * range + 9);
			}
			String stars = createStars(histogramArray[range]);
			println(label + ": " + stars);
		}
	}

	/* Creates a string consisting of n stars */
	private String createStars(int n) {
		String stars = "";
		for (int i = 0; i < n; i++) {
			stars += "*";
		}
		return stars;
	}

	/* Private instance variables */
	private int[] histogramArray;
	/* Name of the data file */
	private static final String DATA_FILE = "week4/MidtermScores.txt";
}
