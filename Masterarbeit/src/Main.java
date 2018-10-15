import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		
		Population population = new Population();
		
		Problem.berechneGesamtBearbeitungsZeitJobs();
		for(int i = 0; i<Problem.gesamtBearbeitungsZeitJobs.length;i++) {
			System.out.println(Problem.gesamtBearbeitungsZeitJobs[i]);
		}

	
		

				System.out.println(population.toString());
		for (int i = 0; i < 10000; i++) {
			population.generiereLoesung();
		}
		Loesung loesung = population.generiereLoesung();
		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < Problem.anzahlJobs; j++) {

				loesung = population.lokaleSucheInsertion(loesung, j);

			}
		}
		System.out.println(loesung.berechneTFT());
	}

}
