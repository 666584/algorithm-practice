import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.lang.Math;

public class BJ_2470_두_용액 {
	static int n;
	static int[] values;
	
	public static int[] findMaxValues() {
		int[] result = new int[2];
		int min = 1_000_000_000 + 1_000_000_001;
		int left = 0;
		int right = n - 1;
		while(left < right) {
			int sum = values[left] + values[right];
			if(Math.abs(sum) < min) { 
				min = Math.abs(sum);
				result[0] = values[left];
				result[1] = values[right];
			}
			if(sum < 0) left++;
			else if(sum > 0) right--;
			else return result;
		}
		return result;
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		values = new int[n];
		for(int i = 0; i < n; i++) {
			values[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	public static void main(String[] args) throws IOException {
		input();
		Arrays.sort(values);
		for(int i = 0; i < n; i++) {
			System.out.println(values[i]);
		}
		int[] result = findMaxValues();
		System.out.println(result[0]+ " "+ result[1]);
	}
}
