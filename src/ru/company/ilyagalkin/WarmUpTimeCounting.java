package ru.company.ilyagalkin;

import java.util.Scanner;

public class WarmUpTimeCounting {

	public static void main(String[] args) {
		System.out.println("Enter the line of text (that will be reversed): ");
		Scanner scanner = new Scanner(System.in);
		String inputLine = scanner.nextLine();

		String inputLetters[] = inputLine.split("");

		System.out.println("Initial line of text: ");
		for (int i = 0; i < inputLetters.length; i++) {
			System.out.print(inputLetters[i]);
		}
		String outputLetters[] = new String[inputLetters.length];
		for (int i = 0; i < inputLetters.length; i++) {
			outputLetters[i] = inputLetters[inputLetters.length - i - 1];
		}
		System.out.println();
		System.out.println("Reversed line of text: ");
		for (int i = 0; i < outputLetters.length; i++) {
			System.out.print(outputLetters[i]);
		}

		System.out.println();
		System.out.println("Time, in seconds (!), of repeats of reversing the line that had been \n" +
		"inputted, three numbers for 1_000, 10_000, and 100_000 repeats: ");
		System.out.println((double) reverseVariableTimes(inputLetters, 1_000) / 1_000_000_000);
		System.out.println((double) reverseVariableTimes(inputLetters, 10_000) / 1_000_000_000);
		System.out.println((double) reverseVariableTimes(inputLetters, 100_000) / 1_000_000_000);

	}

	private static long reverseVariableTimes(String splitStringAsArray[], int amountOfTimesToReverseTheLine) {
		long startTime = System.nanoTime();

		for (int j = 0; j < amountOfTimesToReverseTheLine; j++) {
			String splitStringAsArrayReversed[] = new String[splitStringAsArray.length];
			for (int i = 0; i < splitStringAsArray.length; i++) {
				splitStringAsArrayReversed[i] = splitStringAsArray[splitStringAsArray.length - i - 1];
			}
		}

		long stopTime = System.nanoTime();
		return stopTime - startTime;
	}

}
