package Functions;

//import Functions.Matrics_LU;

public class Matrics_BasicFuncs {
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

	public static void mult_squa(double[][] A, double[][] B, double[][] Solution) {
		int m = A.length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				Solution[i][j] = 0;
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < m; k++) {
					Solution[i][j] += A[i][k] * B[k][j];
				}
			}
		}
	}

	public static double determinant(double[][] A) {
		int n = A.length;
		double[][] L = new double[n][n];
		double[][] U = new double[n][n];

		decompose_LU(A, L, U);

		double det = 1;
		for (int i = 0; i < n; i++) {
			det *= U[i][i];
		}
		return det;
	}
//
//	public static double[][] multiply(double[][] ...list_matr) {
////		int m = list_matr[0].length; // number of columns of the first matrix
////		int n = list_matr[-1][0].length; // number of rows of the last matrix
////		double[][] M = new double[m][n];
//		
//		public static double[][] mult_two_matr(double[][] A, double[][] B) {
//int mA = A.length;
//int nA = A[0].length;
//int mB = B.length;
//int nB = B[0].length;
//double[][] M = new double[mA][nB];
//	    	
//	    	for (int i=0; i<mA; i++) {
//	    		for (int j=0; j<nB; j++) {
//	    			for (int k=0; k<mB; k++) {
//	    				M[i][j] += A[i][k] * B[k][j];
//	    			}
//	    		}
//	    	}
//	    	return M;
//		}
//		for(int num=0; num<list_matr.length; num++) {
//		mult_two_matr(list_matr[num], list_matr[num+1]);
//		
//	}
//	}
}
