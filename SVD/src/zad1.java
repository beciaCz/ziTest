import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class zad1 {

	static double[][] array = { { 1., 0., 0., 1., 0., 0., 0., 0., 0. },
			{ 1., 0., 1., 0., 0., 0., 0., 0., 0. },
			{ 1., 1., 0., 0., 0., 0., 0., 0., 0. },
			{ 0., 1., 1., 0., 1., 0., 0., 0., 0. },
			{ 0., 1., 1., 2., 0., 0., 0., 0., 0. },
			{ 0., 1., 0., 0., 1., 0., 0., 0., 0. },
			{ 0., 1., 0., 0., 1., 0., 0., 0., 0. },
			{ 0., 0., 1., 1., 0., 0., 0., 0., 0. },
			{ 0., 1., 0., 0., 0., 0., 0., 0., 1. },
			{ 0., 0., 0., 0., 0., 1., 1., 1., 0. },
			{ 0., 0., 0., 0., 0., 0., 1., 1., 1. },
			{ 0., 0., 0., 0., 0., 0., 0., 1., 1. } };

	//Tablica A po odjêciu od ka¿dego elementu œredniej arytmetycznej z kolumny
	static double[][] array2 = new double[12][9];
	
	//Tablica A' po odjêciu od ka¿dego elementu œredniej arytmetycznej z kolumny
	static double[][] array3 = new double[12][9];

	static double dlugosc(double[] wektor) {
		double wynik = 0.;

		for (int i = 0; i < wektor.length; i++) {
			wynik = wynik + wektor[i];
		}

		return wynik;

	}

	static double[] pobierzKolumne(double[][] tablica, int numer) {
		double[] wynik = new double[12];

		for (int i = 0; i < 12; i++) {
			wynik[i] = tablica[i][numer];
		}

		return wynik;
	}

	static void przygotujA1() {
		double dlugosc = 0.;
		for (int i = 0; i < 9; i++) {
			dlugosc = dlugosc(pobierzKolumne(array, i));
			for (int j = 0; j < 12; j++) {
				array2[j][i] = array[j][i] - dlugosc / 12.0;
			}

		}

	}
	
	static void przygotujA2(double[][] arrayX) {
		double dlugosc = 0.;
		for (int i = 0; i < 9; i++) {
			dlugosc = dlugosc(pobierzKolumne(arrayX, i));
			for (int j = 0; j < 12; j++) {
				array3[j][i] = arrayX[j][i] - dlugosc / 12.0;
			}

		}

	}
	
	public static void printmat2(double[][] mat)
	 {
	  System.out.println("drukowanie macierzy");
	  for(int i=1; i < mat.length; i++)
	  {
	   for(int j=0; j<mat[i].length-1; j++)
	   {
	    if(i>j)
	    System.out.format("%.2f,\t",mat[i][j]);
	   }
	   System.out.print("\n");
	  }
	 }

	public static void main(String[] args) {
		
		/*Przygotowanie tablicy array2*/
		przygotujA1();
	
		
		/* Dekompozycja */
		SingularValueDecomposition svd = new SingularValueDecomposition(
				new Matrix(array));

		Matrix V = svd.getV();
		Matrix U = svd.getU();
		Matrix S = svd.getS();
		
		System.out.println("Tablica V");
		V.print(0,2);
		System.out.println("\nTablica U");
		U.print(0,2);
		System.out.println("\nTablica S");
		S.print(0,2);


		/*Wyœwietlenia array2*/
//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 9; j++)
//				System.out.format("%.2f\t", array2[i][j]);
//			System.out.println();
//		}
//		System.out.println();
		/*Obliczenie podobieñstwa cosinusowego*/
		double[][] wyswietl = new double[12][9];
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 9; j++) {
				wyswietl[i][j] = pom.oblicz(
						pobierzKolumne(array2, i), pobierzKolumne(array2, j));
			}
		}
		
		/*Bez SVD*/
		Matrix pom1 = new Matrix(wyswietl);
		pom1 = pom1.transpose();

		
		printmat2(pom1.getArray());

		/*Przygotowanie do pracy z SVD*/
		int[] wierszeU = {0,1,2,3,4,5,6,7,8,9,10,11};
		int[] kolumnyU = {0,1};
		
		int[] wierszeS = {0,1};
		int[] kolumnyS = {0,1};
		
		int[] wierszeV = {0,1,2,3,4,5,6,7,8};
		int[] kolumnyV = {0,1};
		
		Matrix V1 = V.getMatrix(wierszeV, kolumnyV);
		Matrix S1 = S.getMatrix(wierszeS, kolumnyS);
		Matrix U1 = U.getMatrix(wierszeU, kolumnyU);

		
		Matrix arrayX = U1.times(S1);
		arrayX = arrayX.times(V1.transpose());

		arrayX.print(0,2);
		System.out.println();
		
		przygotujA2(arrayX.getArray());
		
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 9; j++) {
				wyswietl[i][j] = pom.oblicz(
						pobierzKolumne(array3, i), pobierzKolumne(array3, j));
			}
		}
		
		System.out.println();
		
//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 9; j++)
//				System.out.format("%.2f\t", array3[i][j]);
//			System.out.println();
//		}
		
		pom1 = new Matrix(wyswietl);
		pom1 = pom1.transpose();

		
		printmat2(pom1.getArray());
		
	}

}
