package lecture5;

import Functions.Matrics_Gauss;
import Functions.Matrics_Print;

public class Report5 {
	private static final double EPSILON = 1e-4;

	public static void main(String[] args) {

		//		double[][] A = {
		//				{  -5, 2, 2 },
		//				{ 3, -7, 2 },
		//				{  3, 4, -7 },
		//          	};
		//		double[] b = { -8, -8, -11 };
		//		double[] x = { 0.0, 0.0, 0.0 };
		//        

		// Report5_1
		//        Matrics_Iteral.Gauss_Seidel(A, x, b, EPSILON);
		//		Matrics_Iteral.Jacobi(A, x, b, EPSILON);

		//Report5_2
		//        double omega = 1.35;
		//        Matrics_Iteral.SOR(A, x, b, EPSILON, omega);

		//Report5_3
		int n = 2; // 式の数
		double[][] J = new double[n][n];
		double[] b = new double[n];
		double[] delta_x = new double[n];

		int iter = 0;
		double[] x = { -10, 50 }; // 解{x}の初期近似値を設定
		while (true) {
			boolean flag = true;
			b = func(x, n);

			J = setJacobian(x, b, n);
			for (int k = 0; k < n; k++)
				b[k] = -b[k];
			System.out.printf("Iter=%2d\n", iter);
			System.out.println("Jacobian=");
			Matrics_Print.m_print(J);
			System.out.println("x="); 
			Matrics_Print.v_print(x);
			System.out.println("b=");
			Matrics_Print.v_print(b);
			System.out.println();

			Matrics_Gauss.forw_elim(J, b);
			delta_x = Matrics_Gauss.back_subs(J, b);
			for (int i = 0; i < n; i++) {
				if (Math.abs(delta_x[i]) > EPSILON)
					flag = false;
				x[i] += delta_x[i];
			}
			iter++;
			if (flag)
				break;
		}

		// 解の印刷
		System.out.println();
		System.out.println("X=");
		Matrics_Print.v_print(x);
	}

	// Prob5_6  Jacobian行列を数値微分でセット
	private static final double delta = 1e-6;

	public static double[][] setJacobian(double[] x, double[] f, int n) {
		double[][] Jacobian = new double[n][n];
		double[][] x_forward = new double[n][n];
		double[][] f_forward = new double[n][n];
		double[] del_x = new double[n];
		for (int i = 0; i < n; i++) {
			del_x[i] = delta * x[i];
			for (int k = 0; k < n; k++)
				x_forward[i][k] = x[k];
			x_forward[i][i] += del_x[i];
			f_forward[i] = func(x_forward[i], 2);
		}
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++)
				Jacobian[i][k] = (f_forward[k][i] - f[i]) / del_x[k];
		}

		return Jacobian;
	}

	public static double[] func(double[] x, int n) {
		double[] f = new double[n];
		// f[0] = x0^2 + x0*x1 -10
		// f[1] = x1 + 3*x0*x1^2 -57
		f[0] = Math.pow(x[0], 2) - x[0] + x[1] - 0.5;
		f[1] = - x[1] + Math.pow(x[0], 2) - 5*x[0]*x[1];
		return f;
	}

}
