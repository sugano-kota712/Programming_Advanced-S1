package Functions;

public class Matrics_Gauss {

	// Forward elimination
	public static double[][] forw_elim(double[][] A, double[] b) {
		int n = A.length;
		for (int p = 0; p < n; p++) {
			// find pivot row and swap (ピボットを見つけて，現在行pとピボット行maxを入れ替える)
			int max = p;
			for (int i = p + 1; i < n; i++) {
				if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
					max = i;
				}
			}
			double[] temp = A[p];
			A[p] = A[max];
			A[max] = temp;
			double t = b[p];
			b[p] = b[max];
			b[max] = t;
			// singular or nearly singular (ピボット要素が0に近いときは，プログラム終了)

			for (int i = p + 1; i < n; i++) {
				double alpha = A[i][p] / A[p][p];
				b[i] -= alpha * b[p];
				for (int j = p; j < n; j++) {
					A[i][j] -= alpha * A[p][j];
				}
			}
		}
		return A;
	}

	// Back substitution (後退代入)
	public static double[] back_subs(double[][] A, double[] b) {
		int n = A.length;
		double[] x = new double[n];
		for (int i = n - 1; i >= 0; i--) {
			double sum = 0.0;
			for (int j = i + 1; j < n; j++) {
				sum += A[i][j] * x[j];
			}
			x[i] = (b[i] - sum) / A[i][i];
		}
		return x;
	}
}
