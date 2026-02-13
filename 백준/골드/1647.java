import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1647_도시분할계획_조유림 {
	static int N, M; // 정점, 간선
	static int total; //최소 비용
	static int[][] info; //간선 정보
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		info = new int[M][3];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
			info[i][2] = Integer.parseInt(st.nextToken());
		}
		total = 0;
		Arrays.sort(info, (x,y)->Integer.compare(x[2], y[2]));
		parent = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		mst();
		System.out.println(total);
	}
	
	public static int find(int x) {
		if(parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	
	public static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA != pB) {
			parent[pB] = pA;
		}
	}
	
	// 두개의 마을로 나누기 때문에 집 사이의 가중치가 가장 큰 것을 뺀다.
	public static void mst() {
		int max = 0;
		for(int i = 0; i < M; i++) {
			int a = info[i][0];
			int b = info[i][1];
			int cost = info[i][2];
			if(find(a)!= find(b)) {
				union(a,b);
				total += cost;
				max = Math.max(max, cost);
			}
		}
		
		total -= max;
	}
}
