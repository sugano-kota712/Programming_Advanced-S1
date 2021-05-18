package Functions;

public class Matrics_Iteral {

	public static void Jacobi(double[][] A, double[] x0, double[] b, double Epsilon) {
		int n = A.length;
		int iter = 0;
		double[] x = new double[n];
		double[] error = new double[n];
		boolean flag = true;

		while (flag) {
			for (int i = 0; i < n; i++) {
				double sum = 0;
				for (int j = 0; j < n; j++) {
					if (j != i)
						sum += A[i][j] * x0[j];
				}
				x[i] = (b[i] - sum) / A[i][i];
				
				error[i] = Math.abs(x[i] - x0[i]);
				if (error[i] < Epsilon)
					flag = false;
				else
					flag = true;
			}
			for(int i= 0; i< n; i++) x0[i] = x[i];
			
			System.out.println("Iter" + iter);
			Matrics_Print.v_print(x0);
//			System.out.print("Error:");
//			Matrics_Print.v_print(error);
//			System.out.println();

			iter++;
		}
	}

	public static void Gauss_Seidel(double[][] A, double[] x0, double[] b, double Epsilon) {
		int n = A.length;
		int iter = 0;
		double[] x = new double[n];
		double[] error = new double[n];
		boolean flag = true;
		
		while (flag) {
			for (int i = 0; i < n; i++) {
				double sum = 0;
				for (int j = 0; j < n; j++) {
					if (j != i)
						sum += A[i][j] * x0[j];
				}
				x[i] = (b[i] - sum) / A[i][i];

				error[i] = Math.abs(x[i] - x0[i]);
				if (error[i] < Epsilon)
					flag = false;
				else
					flag = true;
				x0[i] = x[i];
			}
			
			System.out.println("Iter" + iter);
			Matrics_Print.v_print(x0);
//			System.out.print("Error:");
//			Matrics_Print.v_print(error);
//			System.out.println();

			iter++;
		}
	}
	
	public static void SOR(double[][] A, double[] x0, double[] b, double Epsilon, double omega) {
		int n = A.length;
		int iter = 0;
		double[] x = new double[n];
		double[] error = new double[n];
		boolean flag = true;
		
		while (flag) {
			for (int i = 0; i < n; i++) {
				double sum = 0;
				for (int j = 0; j < n; j++) {
					if (j != i)
						sum += A[i][j] * x0[j];
				}
				x[i] = (b[i] - sum) / A[i][i];

				error[i] = Math.abs(x[i] - x0[i]);
				if (error[i] < Epsilon)
					flag = false;
				else
					flag = true;
				x0[i] += omega*(x[i]-x0[i]);
			}
			
			System.out.println("Iter" + iter);
			Matrics_Print.v_print(x0);
//			System.out.print("Error:");
//			Matrics_Print.v_print(error);
//			System.out.println();

			iter++;
		}
	}
	
}
