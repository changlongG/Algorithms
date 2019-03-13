//zoj1003 CrashingBallon 递归查找唯一公因数
package algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CrashingBallon {
	public static int a, b = 0;
	public static boolean flaga, flagb = false;

	public static void dfs(int a, int b, int step) {
		if (b == 1) {
			flagb = true;
			if (a == 1) {
				flaga = true;
			}
		}
		if (step == 1) {
			return;
		}
		if (a % step == 0) {
			dfs(a / step, b, step - 1);
		}
		if (b % step == 0) {
			dfs(a, b / step, step - 1);
		}
		dfs(a, b, step - 1);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("CrashingBallon.txt"));
		while (sc.hasNext()) {
			int tempa = sc.nextInt();
			int tempb = sc.nextInt();
			a = tempa > tempb ? tempa : tempb;
			b = tempa > tempb ? tempb : tempa;
			flaga = false;
			flagb = false;
			dfs(a, b, 100);
			if (!flaga && flagb) {
				System.out.println(b);
			} else {
				System.out.println(a);
			}
		}
		sc.close();
	}
}
