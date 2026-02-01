import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++){
			
			int N = sc.nextInt();
			String[] list = new String[N];
			int x = 0;
			int n = N;
			if(N % 2 != 0) {
				n++;
			}
			
			for(int i = 0; i < N; i++) {
				if (i < n / 2) {
					list[i*2] = sc.next();
				}
				else {
					list[x*2+1] = sc.next();
					x++;
				}
			}
			
			System.out.print("#"+test_case);
			for(int i = 0; i < N; i++) {
				System.out.print(" " +list[i]);
			}
			System.out.println();
		}
		
		
	}

}
