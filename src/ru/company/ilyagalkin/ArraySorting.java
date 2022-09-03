package university.ylab.ilyagalkin.task01.part02;

public class MainForTask01Part2ArraySorting {
	public static void main(String[] args) {
		System.out.println("Отсортируйте массив [5,6,3,2,5,1,4,9].");


		/**
		 * We manually create an array of <em>Integers</em>
		 */
		int[] array = {5,6,3,2,5,1,4,9};

		int[] arraySorted = (int[]) sortArrayOfIntegers(array);
		for (int i = 0; i < arraySorted.length; i++) {
			System.out.print(arraySorted[i] + " ");
		}
		System.out.println();


		/**
		 * Performs a check if the programs works properly;
		 * uses manually assigned arrayForTest;
		 * compares arrayExpected to arrayActual
		 */
		int[] arrayForTest = {3,2,2,1,4};
		int[] arrayExpected = {1,2,2,3,4};
		int[] arrayActual = (int[]) sortArrayOfIntegers(arrayForTest);

		if (arrayExpected.length == arrayActual.length) {
			for (int i = 0; i < arrayExpected.length; i++) {
				if (arrayActual[i] != arrayExpected[i]) {
					throw new AssertionError("\n\n" +
							"Program does not sort the array as intended: \n" +
							"element #" + i + " with value = " + arrayActual[i] +
							" of actual array does not match to the same element " +
							"of expected array with value = " + arrayExpected[i]);
				}
			}
		} else {
			throw new AssertionError("\n\n" +
					"Program does not sort the array as intended: \n" +
					"the length of sorted array = " + arrayActual.length + " elements" +
					" differs from the length of expected array = " + arrayExpected.length + " elements");
		}

	}


	/**
	 * Sorts an array and returns a sorted array
	 * @param array takes an array (as parameter) to sort
	 * @return a sorted <em>Array</em>
	 */
	static Object sortArrayOfIntegers(int[] array) {
		/**
		 * Sorts the array
		 */
		for (int i = 1; i < array.length; i++) {
			int current = array[i];
			int j = i - 1;
			while (j >= 0 && current < array[j]) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = current;
		}

		return array;
	}

}

//Exceptions:

//Exception in thread "main" java.lang.AssertionError:
//
//Program does not sort the array as intended:
//element #4 with value = 4 of actual array does not match to the same element of expected array with value = 5
//	at university.ylab.ilyagalkin.task01.part02.MainForTask01Part2ArraySorting2.main(MainForTask01Part2ArraySorting2.java:31)
//
//Process finished with exit code 1

//Exception in thread "main" java.lang.AssertionError:
//
//Program does not sort the array as intended:
//the length of sorted array = 5 elements differs from the length of expected array = 4 elements
//	at university.ylab.ilyagalkin.task01.part02.MainForTask01Part2ArraySorting2.main(MainForTask01Part2ArraySorting2.java:39)
//
//Process finished with exit code 1
