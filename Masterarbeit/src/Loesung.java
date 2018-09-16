import java.util.ArrayList;
import java.util.Arrays;

public class Loesung {

	public Loesung(int alter) {
		jobreihenfolge = new int[Problem.anzahlJobs];
		this.alter = alter;
	}
	
	int jobreihenfolge[];

	int alter = 0;

	public int berechneTFT() {
		Problem problem = new Problem();
		int[][] ausfuehrungszeiten = problem.getAusfuehrungszeiten();
		int tft = 0;
		int[][] leavingTime = new int[Problem.anzahlMaschinen][Problem.anzahlJobs];
		leavingTime[0][0] = ausfuehrungszeiten[0][jobreihenfolge[0]];

// leavingTimes für Job 1
		for (int i = 1; i < leavingTime.length; i++) {

			leavingTime[i][0] = leavingTime[i - 1][0] + ausfuehrungszeiten[i][jobreihenfolge[0]];
			// System.out.println(leavingTime[i][0]);
		}

//leavingTimes für Maschine 1
		for (int j = 1; j < leavingTime[0].length; j++) {

			leavingTime[0][j] = leavingTime[0][j - 1] + ausfuehrungszeiten[0][jobreihenfolge[j]];

			// System.out.println(leavingTime[0][j]);
		}

		for (int i = 1; i < leavingTime.length; i++) {

			for (int j = 1; j < leavingTime[0].length; j++) {

				leavingTime[i][j] = Math.max(leavingTime[i][j - 1], leavingTime[i - 1][j])
						+ ausfuehrungszeiten[i][jobreihenfolge[j]];

			}

		}

		for (int i = 0; i < leavingTime[0].length; i++) {
			tft = tft + leavingTime[leavingTime.length - 1][i];
		}

		return tft;

	}


	public int[] getJobreihenfolge() {
		return jobreihenfolge;
	}

	public void setJobreihenfolge(int[] jobreihenfolge) {
		this.jobreihenfolge = jobreihenfolge;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}
	
	@Override
	public String toString() {
		return alter + " -> " + Arrays.toString(jobreihenfolge);
	}

}
