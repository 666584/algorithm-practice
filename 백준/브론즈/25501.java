import java.util.Scanner;

public class Main {
	public static int[] recursion(String s, int l, int r, int call) {
		if (l >= r ) {
			return new int[]{1, ++call};
		} else if (s.charAt(l) != (s.charAt(r))){
			return new int[]{0, ++call};
		} else {
			return recursion(s, l+1, r-1, ++call);
		}
	}
	
	public static int[] isPalindrome(String s) {
		return recursion(s, 0, s.length()-1, 0);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			String s = sc.next();
			int[] result = isPalindrome(s);
			System.out.println(result[0] + " " + result[1]);
		}
	}

}
