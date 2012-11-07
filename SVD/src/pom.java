
public class pom {
	public static double oblicz(double[] vec1, double[] vec2) {
		double similarity = 0;
		for (int i = 0; i < vec1.length; i++) {

				similarity += vec1[i] * vec2[i];
		}
			similarity = similarity / (dlugosc(vec1) * dlugosc(vec2));

		return similarity;
	}
	
	private static double dlugosc(double[] vec) {
		double len = 0;
		for (int i = 0; i < vec.length; i++) {
			len += vec[i] * vec[i];
		}
		len = Math.sqrt(len);
		return len;			
	}
}
