import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거_다이어트 {
	static BufferedReader br;
	static int T, N, L;	//N: 재료 수, L: 제한 칼로리
	static int info[][]; //재료
	static int max;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			info = new int[N][2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				info[i][0] = Integer.parseInt(st.nextToken()); //맛
				info[i][1] = Integer.parseInt(st.nextToken()); //칼로리
			}
			max = 0;
			possibleIngradient(0, 0, 0);
			System.out.println("#"+t+" "+max);
		}
	}
	
	public static void possibleIngradient(int totalCal, int curr, int totalScore) {
		if(curr == N) {
			if(totalCal <= L) {
				max = Math.max(max, totalScore);
			}
			return;
		}
		possibleIngradient(totalCal+info[curr][1], curr+1, totalScore+info[curr][0]); //포함
		possibleIngradient(totalCal, curr+1, totalScore); //미포함
	}
}
