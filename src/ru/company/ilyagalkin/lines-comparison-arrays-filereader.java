package org.example.test;

import java.io.*;
import java.util.*;

public class Main {

	//https://ilyagalkin2.github.io/CV/
	//ilya.en.ru.fr.galkin@gmail.com

	static final String errorOfFileSyntax = "Check the file, its structure shall to remind this one: \n" +
			"4\n" +
			"гвоздь\n" +
			"шуруп\n" +
			"краска синяя\n" +
			"ведро для воды\n" +
			"3\n" +
			"краска\n" +
			"корыто для воды\n" +
			"шуруп 3х1.5";

	public static void main(String[] args) {


		/**
		 * Reading from file
		 */
		String line = readFileIntoArray();


		/**
		 * Creating array of lines
		 */
		String[] lineSplitByLines = line.split("\n");
		for (int i = 0; i < lineSplitByLines.length; i++) {
			System.out.println(lineSplitByLines[i]);
		}


		/**
		 * Getting integers that define the amount of lines
		 */
		int n = Integer.parseInt(lineSplitByLines[0]);
		if (n < 1) {
			throw new AssertionError("Number n in the file input.txt is less than 1, \n" +
					errorOfFileSyntax);
		}
		int m = Integer.parseInt(lineSplitByLines[n+1]);
		if (m < 0) {
			throw new AssertionError("Number m in the file input.txt is less than 1, \n" +
					errorOfFileSyntax);
		} else if (m == 0) {
			System.out.println("\n\n\n" + "Number m in the file input.txt is set to 0 (zero), \n" +
					"most likely you may want to check the file's syntax. \n" + errorOfFileSyntax);
		}


		/**
		 * Instantiating two arrays and filling them up
		 */
		String[] arrayN = new String[n];
		String[] arrayM = new String[m];
		for (int i = 0; i < n; i++) {
			arrayN[i] = lineSplitByLines[1 + i];
			System.out.println("arrayN[" + i + "] " + arrayN[i]);
		}
		System.out.println();
		for (int i = 0; i < m; i++) {
			arrayM[i] = lineSplitByLines[n+2 +i];
			System.out.println("arrayM[" + i + "] " + arrayM[i]);
		}
		System.out.println();
		//arrayN[0] гвоздь
		//arrayN[1] шуруп
		//arrayN[2] краска синяя
		//arrayN[3] ведро для воды

		//arrayM[0] краска
		//arrayM[1] корыто для воды
		//arrayM[2] шуруп 3х1.5


		/**
		 * Creating the String where we compare two arrays
		 * using one the algorithm
		 */
		StringBuilder stringBuilder = new StringBuilder();

		if (n == 1 && m == 1) {
			stringBuilder.append(arrayN[0] + " -- " + arrayM[0]);
		} else if (n > m) {
			for (int j = 0; j < n; j++) {
//			System.out.print(arrayN[j]);
				stringBuilder.append(arrayN[j]);
				for (int i = 0; i < m; i++) {
					String[] ar = arrayM[i].split(" ");
					int count = 0;
					//to get words of arrayN
					for (int k = 0; k < ar.length; k++) {
						String wordLong = ar[k].toLowerCase();

						/**
						 * For words that are longer than 3 symbols we leave them as is;
						 * For words that are of 5 symbols we shorten it on 1 symbol;
						 * For words that are longer than 5 symbols we shorten it on 2 symbol;
						 */
						String wordShort = wordLong;
						int wordLength = wordLong.length();
						if (wordLength == 5) {
							wordShort = wordLong.substring(0, wordLong.length() - 1);
							System.out.println(wordShort);
						} else if (wordLength > 5) {
							wordShort = wordLong.substring(0, wordLong.length() - 2);
							System.out.println(wordShort);
						}

						/**
						 * We omit short words with 2 symbols
						 */
						if (( arrayN[j].toLowerCase() ).contains( wordShort ) && wordLength > 2 ) {
							count++;
						}
					}
					/**
					 * setting the word as quite similar according to
					 * our simplified method of comparing the lines of text
					 */
					if (count > 0) {
//					System.out.print(" -- " + arrayM[i]);
						stringBuilder.append(" -- " + arrayM[i]);
					}
				}
//			System.out.println();
				stringBuilder.append("\n");
			}
		} else if (n < m) {

			for (int j = 0; j < m; j++) {
//			System.out.print(arrayN[j]);
				stringBuilder.append(arrayM[j]);
				for (int i = 0; i < n; i++) {
					String[] ar = arrayN[i].split(" ");
					int count = 0;
					//to get words of arrayN
					for (int k = 0; k < ar.length; k++) {
						String wordLong = ar[k].toLowerCase();
						System.out.println("wordLong " + wordLong);

						/**
						 * For words that are longer than 3 symbols we leave them as is;
						 * For words that are of 5 symbols we shorten it on 1 symbol;
						 * For words that are longer than 5 symbols we shorten it on 2 symbol;
						 */
						String wordShort = wordLong;
						int wordLength = wordLong.length();
						if (wordLength == 5) {
							wordShort = wordLong.substring(0, wordLong.length() - 1);
							System.out.println(wordShort);
						} else if (wordLength > 5) {
							wordShort = wordLong.substring(0, wordLong.length() - 2);
							System.out.println(wordShort);
						}

						/**
						 * We omit short words with 2 symbols
						 */
						if (( arrayM[j].toLowerCase() ).contains( wordShort ) && wordLength > 2  ) {
							count++;
							System.out.println("count " + count);
						}
					}
					/**
					 * setting the word as quite similar according to
					 * our simplified method of comparing the lines of text
					 */
					if (count > 0) {
//					System.out.print(" -- " + arrayM[i]);
						stringBuilder.append(" -- " + arrayN[i]);
					}
				}
//			System.out.println();
				stringBuilder.append("\n");
			}
		}
		String output = stringBuilder.toString();
		System.out.println(output);
		//гвоздь
		//шуруп -- шуруп 3х1.5
		//краска синяя -- краска
		//ведро для воды -- корыто для воды -- корыто для воды

		/**
		 * Writing to the file
		 */
		writingToFile(output);

	}

	/**
	 * Gets <em>String</em> as parameter;
	 * writes it into the file
	 * @param output <em>String</em>
	 */
	private static void writingToFile(String output) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("output.txt");
			fileWriter.write(output);
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Error at attempt to write the file: " + e);
			e.printStackTrace();
		}
	}

	/**
	 *  Reads from file
	 * @return <em>String</em>
	 */
	static String readFileIntoArray() {
		StringBuilder stringbuilder = new StringBuilder();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("input.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Error at attempt to read the file: " + e);
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			stringbuilder.append(scanner.nextLine() + "\n");
		}
		scanner.close();
		String line = stringbuilder.toString();
		return line;
	}
}
//the output of the program:

//гвоздь
//шуруп -- шуруп 3х1.5
//краска синяя -- краска
//ведро для воды -- корыто для воды


//Бетон с присадкой -- Цемент


//присадка для бетона -- Бетон с присадкой
//доставка




//Some of the erorrs, example:
//4
//гвоздь
//шуруп
//краска синяя
//ведро для воды
//0
//краска
//корыто для воды
//шуруп 3х1.5

//Number m in the file input.txt is set to 0 (zero),
//most likely you may want to check the file's syntax.
//Check the file, its structure shall to remind this one:
//4
//гвоздь
//шуруп
//краска синяя
//ведро для воды
//3
//краска
//корыто для воды
//шуруп 3х1.5




//the task

//Для отклика на эту вакансию необходимо ответить на вопрос работодателя.
//
//Здравствуйте, для того чтобы оставить отклик, необходимо пройти тестовое задание
//
//Необходимо написать консольное приложение на Java(главный класс называть Main), в которое читает из файла input.txt входные данные:
//
//n - число
//
//далее n строк
//
//m - число
//
//далее m строк
//
//Пример 1:
//
//input.txt:
//
//4
//
//гвоздь
//
//шуруп
//
//краска синяя
//
//ведро для воды
//
//3
//
//краска
//
//корыто для воды
//
//шуруп 3х1.5
//
//
//
//ouput.txt:
//
//гвоздь:?
//
//шуруп:шуруп 3х1.5
//
//краска синяя:краска
//
//ведро для воды:корыто для воды
//
//
//
//Пример 2:
//
//1
//
//Бетон с присадкой
//
//1
//
//Цемент
//
//ouput.txt:
//
//Бетон с присадкой:Цемент
//
//
//
//Пример 3:
//
//1
//
//Бетон с присадкой
//
//2
//
//присадка для бетона
//
//доставка
//
//ouput.txt:
//
//Бетон с присадкой:присадка бля бетона
//
//доставка:?
//
//Программа должна сопоставить максимально похожие строки из первого множества со строками из второго множества (одна к одной) и вывести результат в файл output.txt.