import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		/*
		 * int tft = 0; int[][] ausfuehrungszeiten = { { 54, 83, 15, 71, 77, 36, 53, 38,
		 * 27, 87, 76, 91, 14, 29, 12, 77, 32, 87, 68, 94 }, { 79, 3, 11, 99, 56, 70,
		 * 99, 60, 5, 56, 3, 61, 73, 75, 47, 14, 21, 86, 5, 77 }, { 16, 89, 49, 15, 89,
		 * 45, 60, 23, 57, 64, 7, 1, 63, 41, 63, 47, 26, 75, 77, 40 }, { 66, 58, 31, 68,
		 * 78, 91, 13, 59, 49, 85, 85, 9, 39, 41, 56, 40, 54, 77, 51, 31 }, { 58, 56,
		 * 20, 85, 53, 35, 53, 41, 69, 13, 86, 72, 8, 49, 47, 87, 58, 18, 68, 28 } };
		 * 
		 * int jobreihenfolge[] = { 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6,
		 * 5, 4, 3, 2, 1, 0 };
		 * 
		 * int[][] leavingTime = new int[5][20]; leavingTime[0][0] =
		 * ausfuehrungszeiten[0][jobreihenfolge[0]];
		 * 
		 * // leavingTimes für Job 1 for (int i = 1; i < leavingTime.length; i++) {
		 * 
		 * leavingTime[i][0] = leavingTime[i - 1][0] +
		 * ausfuehrungszeiten[i][jobreihenfolge[0]]; //
		 * System.out.println(leavingTime[i][0]); }
		 * 
		 * //leavingTimes für Maschine 1 for (int j = 1; j < leavingTime[0].length; j++)
		 * {
		 * 
		 * leavingTime[0][j] = leavingTime[0][j - 1] +
		 * ausfuehrungszeiten[0][jobreihenfolge[j]];
		 * 
		 * // System.out.println(leavingTime[0][j]); }
		 * 
		 * for (int i = 1; i < leavingTime.length; i++) {
		 * 
		 * for (int j = 1; j < leavingTime[0].length; j++) {
		 * 
		 * leavingTime[i][j] = Math.max(leavingTime[i][j - 1], leavingTime[i - 1][j]) +
		 * ausfuehrungszeiten[i][jobreihenfolge[j]];
		 * 
		 * }
		 * 
		 * }
		 * 
		 * for (int i = 0; i < leavingTime[0].length; i++) { tft = tft +
		 * leavingTime[leavingTime.length - 1][i]; }
		 * 
		 * System.out.println(tft);
		 */

		// System.out.println(tft);

		// Ameise ameise1 = new Ameise();
		Population population = new Population();
		// System.out.println("Ergebnis :" +
		// ameise1.naechsterKnoten(population.getPheromonmatrix()));
		// System.out.println(population.toString());
		Problem.berechneGesamtBearbeitungsZeitJobs();

	
		

				System.out.println(population.toString());
		for (int i = 0; i < 100; i++) {
			population.generiereLoesung();
		}
		Loesung loesung = population.generiereLoesung();
		for (int i = 0; i < 100; i++) {

			for (int j = 0; j < Problem.anzahlJobs; j++) {

				loesung = population.lokaleSucheInsertion(loesung, j);

			}
		}
		System.out.println(loesung.berechneTFT());
	}

}
