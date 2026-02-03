import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br;
	final int MAX = 2;
	
	public static int twoPointer(int N, int[] snackList, int M) {
		Arrays.sort(snackList);
		int max = -1;
		int curr = 0;

		for(int i = N - 1; i > 0; i--) {			
			int end = i - 1; 
			while(end >= 0) {
				curr = snackList[i] + snackList[end];
				if(curr > max && curr <= M) {
					max = curr;
					
				}
				end -= 1;
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] snackList = new int[N];
			for(int i = 0; i < N; i++) {
				snackList[i] = Integer.parseInt(st.nextToken());
			}
			int result = twoPointer(N, snackList, M);
			System.out.println("#"+(t+1)+" "+result);
		}
	}
}
