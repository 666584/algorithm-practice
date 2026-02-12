import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최소 비용 신장 트리(mst)
public class BJ_1922_네트워크연결_조유림 {
	static int N, M; //N: 컴퓨터 수, M: 연결할 수 있는 선의 수
	static int cost[][];
	static int parent[]; // 부모정점을 저장하는 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		cost = new int[M][3];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(cost, (a, b) -> Integer.compare(a[2], b[2]));
		parent = new int[N+1];
		for (int i = 1; i < N+1; i++) {
            parent[i] = i;
        }
		int result = mstKruskal();
		System.out.println(result);
	}
	
	// Union-Find 구현
	// 초기 값은 자기 자신으로 한다.
	
	// 두 집합을 하나로 합친다. 
	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA != rootB) {
			parent[rootB] = rootA;
		}
	}
	
	// 집합의 루트를 찾는다. 
	// 재귀를 이용하여 한번 루트를 찾았다면 이 노드의 부모를 루트로 만들어준다. 
	public static int find(int x) {
		if(parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	
	// 서로소 집합으로 부모 정점을 저장한다. 
	// Union-Find: 사이클을 형성하는지 확인한다. 
	public static int mstKruskal() {
		int result = 0;
		for(int[] node: cost) {
			int from = node[0];
			int to = node[1];
			int costNode = node[2];
			if(find(from) != find(to)) {
				union(from, to);
				result += costNode;
			}
		}
		return result;
	}
}
