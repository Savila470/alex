
public class Problem {

	static int anzahlJobs = 20;
	static int anzahlMaschinen = 5;
	static int anzahlAmeisen = 20;
	static int populationsgroesse = 10;
	static double updategewicht = 2.0;
	public static int[] gesamtBearbeitungsZeitJobs;
	
/*	static int[][] ausfuehrungszeiten = {
			{ 54, 83, 15, 71, 77, 36, 53, 38, 27, 87, 76, 91, 14, 29, 12, 77, 32, 87, 68, 94 },
			{ 79, 3, 11, 99, 56, 70, 99, 60, 5, 56, 3, 61, 73, 75, 47, 14, 21, 86, 5, 77 },
			{ 16, 89, 49, 15, 89, 45, 60, 23, 57, 64, 7, 1, 63, 41, 63, 47, 26, 75, 77, 40 },
			{ 66, 58, 31, 68, 78, 91, 13, 59, 49, 85, 85, 9, 39, 41, 56, 40, 54, 77, 51, 31 },
			{ 58, 56, 20, 85, 53, 35, 53, 41, 69, 13, 86, 72, 8, 49, 47, 87, 58, 18, 68, 28 } };
	*/
	static int[][] ausfuehrungszeiten = {
			{ 26, 38, 27, 88, 95, 55, 54, 63, 23, 45, 86, 43, 43, 40, 37, 54, 35, 59, 43, 50, },
			{ 59, 62, 44, 10, 23, 64, 47, 68, 54,  9, 30, 31, 92,  7, 14, 95, 76, 82, 91, 37 },
			{ 78, 90, 64, 49, 47, 20, 61, 93, 36, 47, 70, 54, 87, 13, 40, 34, 55, 13, 11,  5 },
			{ 88, 54, 47, 83, 84,  9, 30, 11, 92, 63, 62, 75, 48, 23, 85, 23,  4, 31, 13, 98 },
			{ 69, 30, 61, 35, 53, 98, 94, 33, 77, 31, 54, 71, 78,  9, 79, 51, 76, 56, 80, 72 } };
	
	public static void berechneGesamtBearbeitungsZeitJobs() {
		gesamtBearbeitungsZeitJobs = new int[Problem.anzahlJobs];
		for(int i = 0; i> Problem.anzahlJobs;i++) {
			gesamtBearbeitungsZeitJobs[i] = 0;
		}
		
		for(int i = 0; i< anzahlJobs;i++) {
			for(int j = 0; j < anzahlMaschinen;j++) {
				gesamtBearbeitungsZeitJobs[i] = gesamtBearbeitungsZeitJobs[i] + ausfuehrungszeiten[j][i];
			}
		
		}
	}

	public int getAnzahlJobs() {
		return anzahlJobs;
	}

	public void setAnzahlJobs(int anzahlJobs) {
		this.anzahlJobs = anzahlJobs;
	}

	public int getAnzahlMaschinen() {
		return anzahlMaschinen;
	}

	public void setAnzahlMaschinen(int anzahlMaschinen) {
		this.anzahlMaschinen = anzahlMaschinen;
	}

	public int[][] getAusfuehrungszeiten() {
		return ausfuehrungszeiten;
	}

	public void setAusfuehrungszeiten(int[][] ausfuehrungszeiten) {
		this.ausfuehrungszeiten = ausfuehrungszeiten;
	}

}
