import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.omg.Messaging.SyncScopeHelper;

public class Population {

	int alterAeltesteLoesung = 100000;
	int iterationsanzahl = 0;
	int anzahlLoesungen = 0;
	int anzahlJobs = 100;
	Loesung[] loesungenInPopulation = new Loesung[Problem.populationsgroesse];
	double[][] pheromonmatrix = new double[anzahlJobs][anzahlJobs];
	Loesung eliteloesung;
	Loesung besteLoesungIteration;

	public Population() {
		for (int i = 0; i < Problem.anzahlJobs; i++) {
			for (int j = 0; j < Problem.anzahlJobs; j++) {
				pheromonmatrix[i][j] = (double) 1 / (Problem.anzahlJobs);
			}
		}
	}

	public String toString() {
		String s = "";
		s += "populationsgroesse: " + anzahlLoesungen + "\n";
		s += "Pheromonmatrix: \n";
		for (int i = 0; i < Problem.anzahlJobs; i++) {
			for (int j = 0; j < Problem.anzahlJobs; j++) {
				s += pheromonmatrix[i][j] + " ";
			}

			s += "\n";
		}
		if (loesungenInPopulation[ermittleBesteLoesung(loesungenInPopulation)] != null) {
			s += "beste Lösung der aktuellen Iteration: " + besteLoesungIteration.berechneTFT();
			s += "\n";
			for (int i = 0; i < besteLoesungIteration.getJobreihenfolge().length; i++) {
				s += besteLoesungIteration.getJobreihenfolge()[i] + ", ";
			}
			s += "\n";
			s += "beste Lösung: " + eliteloesung.berechneTFT();
			s += "\n";
			for (int i = 0; i < eliteloesung.getJobreihenfolge().length; i++) {
				s += eliteloesung.getJobreihenfolge()[i] + ", ";
			}
		}
		s += "\n Anzahl Loesungen: " + anzahlLoesungen;
		s += "\n";
		s += "\n";

		return s;
	}

	public double[][] getPheromonmatrix() {
		return pheromonmatrix;
	}

	public void setPheromonmatrix(double[][] pheromonmatrix) {
		this.pheromonmatrix = pheromonmatrix;
	}

	public void updateMatrix(Loesung neueLoesung, Loesung alteLoesung) {
		for (int i = 0; i < pheromonmatrix.length; i++) {
			pheromonmatrix[i][neueLoesung.jobreihenfolge[i]] = pheromonmatrix[i][neueLoesung.jobreihenfolge[i]]
					+ (Problem.updategewicht / Problem.anzahlJobs);
			if (anzahlLoesungen >= Problem.populationsgroesse) {
				pheromonmatrix[i][alteLoesung.jobreihenfolge[i]] = pheromonmatrix[i][alteLoesung.jobreihenfolge[i]]
						- (Problem.updategewicht / Problem.anzahlJobs);
				loesungenInPopulation[ermittleIndexAeltesteLoesung()] = neueLoesung;
			}
		}
		if (anzahlLoesungen < Problem.populationsgroesse) {
			anzahlLoesungen++;
		}
	}

	public Loesung ermittleAeltesteLoesung() {
		Loesung aeltesteLoesung = loesungenInPopulation[0];
		for (int i = 0; i < anzahlLoesungen; i++) {
			if (loesungenInPopulation[i].alter <= alterAeltesteLoesung) {
				alterAeltesteLoesung = loesungenInPopulation[i].alter;
				aeltesteLoesung = loesungenInPopulation[i];
			}
		}
		return aeltesteLoesung;
	}

	public int ermittleIndexAeltesteLoesung() {
		int index = 0;
		int alter = loesungenInPopulation[0].alter;
		for (int i = 1; i < anzahlLoesungen; i++) {
			if (loesungenInPopulation[i].alter <= alter) {
				alter = loesungenInPopulation[i].alter;
				index = i;
			}
		}
		return index;
	}

	public int ermittleBesteLoesung(Loesung[] loesungen) {
		int tft = 999999;
		if (anzahlLoesungen < 0) {
			tft = loesungen[0].berechneTFT();
		}
		int besteLoesung = 0;
		for (int i = 1; i < anzahlLoesungen; i++) {
			if (loesungen[i].berechneTFT() < tft) {
				tft = loesungen[i].berechneTFT();
				besteLoesung = i;
			}
		}
		return besteLoesung;
	}

	public Loesung generiereLoesung() {
		Ameise[] ameisen = new Ameise[Problem.anzahlAmeisen];
		Loesung[] loesungen = new Loesung[Problem.anzahlAmeisen];
		for (int i = 0; i < Problem.anzahlAmeisen; i++) {
			ameisen[i] = new Ameise();
			loesungen[i] = new Loesung(iterationsanzahl);
		}

		for (int j = 0; j < Problem.anzahlAmeisen; j++) {

			for (int i = 0; i < Problem.anzahlJobs; i++) {
				ameisen[j].naechsterKnoten(pheromonmatrix);
			}

			loesungen[j].jobreihenfolge = ameisen[j].getBesuchteKnoten();

		}

		for (int i = 0; i < loesungen.length; i++) {

			for (int j = 0; j < Problem.anzahlJobs; j++) {

				loesungen[i] = lokaleSucheInsertion(loesungen[i], j);

			}
		}

		int besteLoesung = ermittleBesteLoesung(loesungen);
		besteLoesungIteration = loesungen[besteLoesung];
		if (iterationsanzahl < Problem.populationsgroesse) {
			loesungenInPopulation[iterationsanzahl] = loesungen[besteLoesung];
		}
		if (iterationsanzahl == 0) {
			eliteloesung = loesungen[besteLoesung];
		} else {
			if (loesungen[besteLoesung].berechneTFT() < eliteloesung.berechneTFT()) {
				eliteloesung = loesungen[besteLoesung];
			}
		}

		updateMatrix(loesungen[besteLoesung], ermittleAeltesteLoesung());
		System.out.println(toString());
		iterationsanzahl++;
		return eliteloesung;
	}

	public Loesung lokaleSucheInsertion(Loesung loesung, int index) {
		int tft = loesung.berechneTFT();
		int tftTemp = 0;
		int indexwert = 0;
		Loesung neueLoesung = new Loesung(loesung.getAlter());
		Loesung besteLoesung = loesung;
if(index == 19) {indexwert = loesung.getJobreihenfolge()[19];}
		for (int i = 0; i < neueLoesung.getJobreihenfolge().length - 1; i++) {
			if (i < index) {
				neueLoesung.getJobreihenfolge()[i] = loesung.getJobreihenfolge()[i];
			}
			if (i == index) {
				indexwert = loesung.getJobreihenfolge()[i];
			}
			if (i >= index) {
				neueLoesung.getJobreihenfolge()[i] = loesung.getJobreihenfolge()[i + 1];
			}
		}

		Loesung tempLoesung = new Loesung(loesung.getAlter());

		for (int i = 0; i < loesung.getJobreihenfolge().length; i++) {

			tempLoesung.jobreihenfolge = Arrays.copyOf(neueLoesung.getJobreihenfolge(),
					neueLoesung.getJobreihenfolge().length);

			for (int j = loesung.getJobreihenfolge().length - 1; j > i; j--) {
				tempLoesung.getJobreihenfolge()[j] = neueLoesung.getJobreihenfolge()[j - 1];

			}
			tempLoesung.getJobreihenfolge()[i] = indexwert;
			tftTemp = tempLoesung.berechneTFT();
			if (tftTemp < tft) {
				besteLoesung.jobreihenfolge = Arrays.copyOf(tempLoesung.getJobreihenfolge(),
						tempLoesung.getJobreihenfolge().length);
				tft = tftTemp;
			}

		}
		return besteLoesung;
	}
}
