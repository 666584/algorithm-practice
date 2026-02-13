import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1774_우주신과의교감 {
	static int N, M; //정점 수, 이미 연결된 정점들의 개수 
	static int[] parent;
	static int[][] info; //정점의 위치.
	static double total;
	static int[][] connected;
	static double[][] distances; // 간선 정보 (정점1, 정점2, 거리)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		info = new int[N+1][2];
		// 가능한 간선 개수
		int cnt = 0;
		for(int i = N-1; i > 0; i--) {
			cnt += i;
		}
		distances = new double[cnt][3];
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		connected = new int[M][2];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
			
		total = 0;
		//System.out.println(find(1)==find(4));
		getDistance();
		Arrays.sort(distances, (x,y)-> Double.compare(x[2], y[2]));
		mst();
		
		System.out.println(String.format("%.2f", total));
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
	
	public static void getDistance() {
		int c = 0;
		for(int i = 1; i < N+1; i++) {
			for(int j = i+1; j < N+1; j++) {
				int ax = info[i][0];
				int ay = info[i][1];
				int bx = info[j][0];
				int by = info[j][1];
				double d = Math.sqrt(Math.pow(Math.abs(ax-bx), 2)+(Math.pow(Math.abs(ay-by), 2)));
				//System.out.println("distance:"+d);
				distances[c][0] = i;
				distances[c][1] = j;
				distances[c][2] = d;
				c++;
			}
		}
	}
	
	public static void mst() {
		for(int i = 0; i < distances.length; i++) {
			int a = (int) distances[i][0];
			int b = (int) distances[i][1];
			double cost = distances[i][2];
			if(find(a) != find(b)) {
				union(a,b);
				total += cost;
			}
		}
	}
}
