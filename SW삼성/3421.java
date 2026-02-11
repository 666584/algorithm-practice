import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_3421_수제버거장인_조유림 {
	static int T, N, M; // N: 재료 개수, M: 맞지 않는 쌍 개수 
	static int totalBurgers, selected;
	static boolean[][] conflict;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			conflict = new boolean[N+1][N+1];
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				conflict[a][b] = true;
		        conflict[b][a] = true;
			}
			
			totalBurgers = 0;
			selected = 0;
			getTotalBurgers(selected, 1);
			System.out.println("#"+t+" "+totalBurgers);
			
		}
	}
	
	public static void getTotalBurgers(int selected, int cnt) {
		if(cnt == N+1) {
			totalBurgers++;
			return;
		}
		getTotalBurgers(selected, cnt+1);
		
		for(int i = 1; i < cnt; i++) {
			if(((selected & (1<<i)) != 0) && conflict[i][cnt]) {
				return;
			}
		}
		
		getTotalBurgers(selected |= (1<<cnt), cnt+1);
	}
}

