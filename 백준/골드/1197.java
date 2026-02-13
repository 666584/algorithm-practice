import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int V, E; // 정점 개수, 간선 개수
	static int[][] info; //간선 정보
	static int[] parent; //각 정점의 루트 번호
	static int total; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		info = new int[E][3];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
			info[i][2] = Integer.parseInt(st.nextToken());
		}
		parent = new int[V+1];
		for(int i = 1; i < V+1; i++) {
			parent[i] = i;
		}
		
		Arrays.sort(info, (i1, i2) -> Integer.compare(i1[2], i2[2]));
		total = 0;
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
		
		if(pA == pB) return;
		else {
			parent[pB] = pA;
		}
	}
	
	public static void mst() {
		for(int i = 0; i < E; i++) {
			int a = info[i][0];
			int b = info[i][1];
			int cost = info[i][2];
			if(find(a) != find(b)) {
				union(a, b);
				total += cost;
			}
		}
	}
}
