import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N*N 크기의 농장
 * 크기는 항상 홀수
 * 1-49 
 * 다이아몬드 모양안에 있는 농작물 가치의 총합을 구한다. 
 * */
public class SWEA_2805_농작물수확하기_조유림 {
	static int T, N;
	static int map[][];
	static int total;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				char[] cNum = line.toCharArray();
				for (int j = 0; j < N; j++) {
		            map[i][j] = cNum[j] - '0';
		        }
			}
			total = 0;
			getTotal();
			System.out.println("#"+t+" "+total);
		}
	}
	
	public static void getTotal() {
		int cnt = 1;
		int mid = N/2;
		total += map[0][mid];
		for(int i = 1; i < N; i++) {
			//System.out.println("cnt "+cnt+"i "+i);
			for(int j = 0; j < N; j++) {
				if(j >= mid-cnt && j <= mid+cnt) {
					//System.out.print(j);
					total += map[i][j];
				}
			}
			//System.out.println();
			if(i < mid) {
				cnt++;
			}else{
				cnt--;
			}
		}
	}
}
