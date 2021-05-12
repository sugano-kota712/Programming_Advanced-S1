package Functions;

public class Matrics_LU {

	private static final double EPSILON = 1e-10;

	public static void decompose_LU(double[][] A, double[][] L, double[][] U) {
		int n = A.length;
		// 単位行列IをLに設定し，UにAのコピーを作成
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				L[i][j] = (i == j) ? 1.0 : 0.0;
				U[i][j] = A[i][j];
			}
		}

		// Gauss消去法を使って，LとUに分解
		for (int p = 0; p < n - 1; p++) {

			// pivot element is nearly zero (対角要素が0に近いときは，プログラム終了)
			if (Math.abs(U[p][p]) <= EPSILON) {
				throw new ArithmeticException("Pivot element is nearly zero. Need pivot operation!");
			}

			// Forward elimination (前進消去)
			for (int i = p + 1; i < n; i++) {
				L[i][p] = U[i][p] / U[p][p];
				U[i][p] = 0.0;
				for (int j = p + 1; j < n; j++) {
					U[i][j] -= L[i][p] * U[p][j];
				}
			}
		}
	}

	public static double[] solve_LU(double[][] A, double[] b) {
		int n = A.length;
		double[][] L = new double[n][n];
		double[][] U = new double[n][n];

		//		decompose_LU(A, L, U);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				L[i][j] = (i == j) ? 1.0 : 0.0;
				U[i][j] = A[i][j];
			}
		}

		// Gauss消去法を使って，LとUに分解
		        for (int p = 0; p < n-1; p++) {
		
		            // pivot element is nearly zero (対角要素が0に近いときは，プログラム終了)
		            if (Math.abs(U[p][p]) <= EPSILON) {
		                throw new ArithmeticException
		                		("Pivot element is nearly zero. Need pivot operation!");
		            }
//		for (int p = 0; p < n; p++) {
//			// find pivot row and swap (ピボットを見つけて，現在行pとピボット行maxを入れ替える)
//			int max = p;
//			for (int i = p + 1; i < n; i++) {
//				if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
//					max = i;
//				}
//			}
//			double[] temp = A[p];
//			A[p] = A[max];
//			A[max] = temp;
//			double t = b[p];
//			b[p] = b[max];
//			b[max] = t;
			// singular or nearly singular (ピボット要素が0に近いときは，プログラム終了)

			// Forward elimination (前進消去)
			for (int i = p + 1; i < n; i++) {
				L[i][p] = U[i][p] / U[p][p];
				U[i][p] = 0.0;
				for (int j = p + 1; j < n; j++) {
					U[i][j] -= L[i][p] * U[p][j];
				}
			}
		}

		double[] y = new double[n];
		for (int i = 0; i < n; i++) {
			double sum = 0;
			for (int j = 0; j < i; j++) {
				sum += L[i][j] * y[j];
			}
			y[i] = (b[i] - sum) / L[i][i];
		}
		double[] x = new double[n];
		for (int i = n - 1; i >= 0; i--) {
			double sum = 0;
			for (int j = i + 1; j < n; j++) {
				sum += U[i][j] * x[j];
			}
			x[i] = (y[i] - sum) / U[i][i];
		}
		return x;
	}
}
