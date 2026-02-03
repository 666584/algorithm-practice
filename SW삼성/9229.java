import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_9229_한빈이와_Sport_Mart_조유림 {
	private static BufferedReader br;
	
	public static int twoPointer(int N, ArrayList<Integer> snacks, int M) {
		Collections.sort(snacks);
		
		int max = -1;
		int left = 0;
		int right = snacks.size() - 1;

		while (left < right) {
		    int sum = snacks.get(left) + snacks.get(right);
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
			ArrayList<Integer> snacks = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				snacks.add(Integer.parseInt(st.nextToken()));
			}
			int result = twoPointer(N, snacks, M);
			System.out.println("#"+(t+1)+" "+result);
		}
	}
}
