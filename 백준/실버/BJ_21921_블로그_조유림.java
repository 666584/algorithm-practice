import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_21921_블로그_조유림 {
	private static BufferedReader br;
	
	public static int[] getMaxVisitor(int[] list, int X, int N) {
		int maxVisitor = 0;
		int curr = 0;
		int count = 1;
		
		for(int j = 0; j < N && j < X; j++) {
			curr += list[j];
		}
		
		if(maxVisitor < curr) {
			maxVisitor = curr;
		}
		
		for(int i = 1; i < N && i+X-1 < N; i ++) {
			curr -= list[i-1];
			if((i+X-1) < N && (i+X-1) > 0) {
				curr += list[i+X-1];	
			}
			if(maxVisitor < curr) {
				count = 1;
				maxVisitor = curr;
			}
			else if(maxVisitor == curr) {
				count++;
			}
		}
		int[] result = {maxVisitor, count};
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] list = new int[N+1];
		for(int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] result = getMaxVisitor(list, X, N);
		if (result[0] != 0) {
			System.out.println(result[0]);
			System.out.println(result[1]);
		}
		else {
			System.out.println("SAD");
		}
	}
}
