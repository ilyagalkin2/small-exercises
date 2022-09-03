package university.ylab.ilyagalkin.task01.part01;

public class MainForTask01Part1ArrayMinMaxAverage {
	public static void main(String[] args) {
		System.out.println("Заполните массив случайными числами, " +
				"и выведите максимальное, минимальное и среднее значение.");

		/**
		 * Performs a check if the programs works properly;
		 * uses manually assigned arrayForTest;
		 * compares arrayExpected to arrayActual
		 */
		int[][] arrayForTest = { {3,2,2,1,4}, {4,2,2,1,3}, {5,2,4,1,2} };
		int[][] arrayExpected = { {1,2,2,3,4}, {1,2,2,3,4}, {1,2,2,4,5} };
		int[][] arrayActual = (int[][]) sortArray(arrayForTest, 3, 5);
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 5; i++) {
				if (arrayActual[j][i] != arrayExpected[j][i]) {
					int jRow = j +1;
					int iColumn = i + 1;
					throw new AssertionError("\n\n" +
							"Program does not sort the array as intended: \n" +
							"element #row#column" + "#" + jRow + "#" + iColumn + " with value = " + arrayActual[j][i] +
							" of actual array does not match to the same element " +
							"of expected array with value = " + arrayExpected[j][i]);
				}
			}
		}
		int[] maximalValueAtRowActual = (int[]) maximalValue(arrayActual, 3, 5);
		int[] minimalValueAtRowActual = (int[]) minimalValue(arrayActual, 3, 5);

		int maxVActual =  getMaxV(maximalValueAtRowActual, 3);
		int minVActual =  getMinV(minimalValueAtRowActual, 3);

		if (maxVActual != 5 || minVActual != 1) {
			throw new AssertionError("Actual values (maxVActual = " +
					maxVActual + ", minVActual = " + minVActual +
					") differ from expected values '5' and '1'.");
		}

		//    n n n n i
		// m
		// m
		// m
		// j

		/**
		 * 'm' - amount of rows, 'n' - amount of columns;
		 * For convenience, 'j' is for rows, and 'i' is for columns
		 */
		int m = 3;
		int n = 4;
		int[][] array = new int[m][n];


		/**
		 * Creates a two-dimensional array of <em>m</em> rows
		 * and <em>n</em> columns filled with pseudo-random integers;
		 * finds average value of whole array
		 */
		double averageValue = 0;
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				array[j][i] = generateRandomInteger(10);
				System.out.print(array[j][i] + " ");
				averageValue = averageValue + Double.valueOf(array[j][i]) / (m * n);
			}
			System.out.println();
		}
		System.out.println("averageValue = " + averageValue);


		/**
		 * Sorts the array
		 */
		int[][] arraySorted = (int[][]) sortArray(array, m, n);

		/**
		 * Gets maximal and minimal values from rows of sorted array
		 * to array of values
		 */
		int[] maximalValueAtRow = (int[]) maximalValue(arraySorted, m, n);
		int[] minimalValueAtRow = (int[]) minimalValue(arraySorted, m, n);

		/**
		 * Gets maximal and minimum values from arrays
		 * of maximal and minimum values
		 */
		int maxV =  getMaxV(maximalValueAtRow, m);
		System.out.println("maxV = " + maxV);
		int minV =  getMinV(minimalValueAtRow, m);
		System.out.println("minV = " + minV);


		/**
		 * Prints sorted array
		 */
		System.out.println("Sorted array:");
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				System.out.print(arraySorted[j][i] + " ");
			}
			System.out.println();
		}

	}


	/**
	 * Finds maximal value of whole array
	 */
	static int getMaxV(int[] maximalValueAtRow, int m){
		int maxV = maximalValueAtRow[0];
		for (int j = 0; j < m; j++) {
			if (maxV < maximalValueAtRow[j]) {
				maxV = maximalValueAtRow[j];
			}
		}
		return maxV;
	}


	/**
	 * Finds minimum value of whole array
	 */
	static int getMinV(int[] minimalValueAtRow, int m) {
		int minV = minimalValueAtRow[0];
		for (int j = 0; j < m; j++) {
			if (minV > minimalValueAtRow[j]) {
				minV = minimalValueAtRow[j];
			}
		}
		return minV;
	}


	/**
	 * Assigns minimal value of each row to the
	 * array of minimal values
	 */
	static Object maximalValue(int[][] arraySorted, int m, int n) {
		int[] maximalValueAtRow = new int[m];
		for (int j = 0; j < m; j++) {
			maximalValueAtRow[j] = arraySorted[j][(n - 1)];
		}
		return maximalValueAtRow;
	}


	/**
	 * Assigns maximal value of each row to the
	 * array of maximal values
	 */
	static Object minimalValue(int[][] arraySorted, int m, int n) {
		int[] minimalValueAtRow = new int[m];
		for (int j = 0; j < m; j++) {
			minimalValueAtRow[j] = arraySorted[j][0];
		}
		return minimalValueAtRow;
	}


	static Object sortArray(int[][] array, int m, int n) {
		/**
		 * Sorts the array, writes maximal and minimal values of each row
		 * into corresponding arrays with similar names
		 */
		for (int j = 0; j < m; j++) {
			for (int i = 1; i < n; i++) {
				int current = array[j][i];
				int k = i - 1;
				while (k >= 0 && current < array[j][k]) {
					array[j][k + 1] = array[j][k];
					k--;
				}
				array[j][k + 1] = current;
			}

		}
		return array;
	}


	/**
	 * Generates a pseudo-random number (although random enough) using
	 * Derrick Henry Lehmer's linear congruential method (1949) for the algorithm
	 * @param upperLevel takes upper level (inclusive) for a random number
	 * @return a random number, <em>Integer</em>
	 */
	static int generateRandomInteger(int upperLevel) {
		// Taken at:
		// The Central Randomizer 1.3 (C) 1997 by Paul Houle (paul@honeylocust.com)
		// See:  http://www.honeylocust.com/javascript/randomizer.html
		long a = System.nanoTime();
		//System.out.println(a);
		//60604439030200
		long b = (a * 9301 + 49297) % 233280;
		//System.out.println(b);
		//135177
		double c = b / (233280.0);
		//System.out.println(c);
		//0.5794624485596708
		double d = Math.ceil(c * upperLevel);
		int numberAsInteger = (int) d;
		return numberAsInteger;
	}

}
//Tests:

//Exception in thread "main" java.lang.AssertionError: Actual values (maxVActual = 5, minVActual = 1) differ from expected values '6' and '1'.
//	at university.ylab.ilyagalkin.task01.part01.MainForTask01Part1ArrayMinMaxAverage.main(MainForTask01Part1ArrayMinMaxAverage.java:36)
//
//Process finished with exit code 1

//Exception in thread "main" java.lang.AssertionError:
//
//Program does not sort the array as intended:
//element #row#column#3#5 with value = 5 of actual array does not match to the same element of expected array with value = 6
//	at university.ylab.ilyagalkin.task01.part01.MainForTask01Part1ArrayMinMaxAverage.main(MainForTask01Part1ArrayMinMaxAverage.java:21)
//
//Process finished with exit code 1


//Normal work:

//Заполните массив случайными числами, и выведите максимальное, минимальное и среднее значение.
//3 1 8 1
//7 8 6 1
//1 8 5 8
//averageValue = 4.75
//maxV = 8
//minV = 1
//Sorted array:
//1 1 3 8
//1 6 7 8
//1 5 8 8