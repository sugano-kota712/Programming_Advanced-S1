package Functions;

public class Matrics_Print {
	public static void m_print(double[][] A) {	// 可変長引数 Variatic argument
//		if(digit.length==0) {digit[0] = 16;}
//		String str = String.format("[%." + digit[0] + "d]");	// printfに渡す文字列をつくる
		System.out.println("Matrics = ");
		for(double[] col: A) {
			for(double num: col) {
//				System.out.printf(str + " ", num);
				System.out.print(num + " ");
			}
			System.out.println();
		};
	}
	
	public static void v_print(double[] x) {
//		if(digit.length==0) {digit[0] = 16;}
//		String str = "[%." + String.format("%d", digit) + "f]";
		System.out.println("Vector = ");
		System.out.print("[ ");
		for(double num: x) {
//			System.out.printf(str + " ", num);
			System.out.print(num + " ");
			}
		System.out.println("]");
	}
}
