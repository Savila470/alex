package antcolopt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		String dateiName = "D:\\tailard\\tai50_5.txt";
		Reader reader = new Reader();
		reader.ladeDatei(dateiName);

		Problem.berechneGesamtBearbeitungsZeitJobs();
		for (int i = 0; i < Problem.gesamtBearbeitungsZeitJobs.length; i++) {
		}

		Population population = new Population();
		try {
			FileWriter fw = new FileWriter("D:\\QuickB3n\\logs\\ausgabe.txt");
			BufferedWriter bw = new BufferedWriter(fw);

			final long timeStart = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++) {

				population.generiereLoesung();
				final long timeEnd = System.currentTimeMillis();
				bw.write(population.getIterationsanzahl() + "; " + (timeEnd - timeStart) + "; "
						+ population.getEliteloesung().berechneTFT());
				bw.write("\n");
			}
			bw.close();
			/*
			 * Loesung loesung = population.generiereLoesung(); for (int i = 0; i < 10; i++)
			 * {
			 * 
			 * for (int j = 0; j < Problem.anzahlJobs; j++) {
			 * 
			 * loesung = population.lokaleSucheInsertion(loesung, j);
			 * 
			 * } } System.out.println(loesung.berechneTFT());
			 */
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
