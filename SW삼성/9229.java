import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br;
	
	public static int twoPointer(int N, int[] snackList, int M) {
		Arrays.sort(snackList);
		int max = -1;
		int left = 0;
		int right = N - 1;

		while (left < right) {
		    int sum = snackList[left] + snackList[right];
		    if (sum > M) right--;
		    else {
		        max = Math.max(max, sum);
		        left++;
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
