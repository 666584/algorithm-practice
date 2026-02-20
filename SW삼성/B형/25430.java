import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class UserSolution {
	int N, K;
	ArrayList<Node>[] graph;
	int[] distToCoffee, distToBakery;
	boolean[] notHomes;
	int INF = Integer.MAX_VALUE/4;
	
	class Node implements Comparable<Node>{
		int idx; //도작지점
		int cost;
		Node(int idx, int cost){
			this.idx = idx;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	public void init(int N, int K, int sBuilding[], int eBuilding[], int mDistance[]) {
		this.N = N;
		this.K = K;
		graph = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i < K; i++) {
			int u = sBuilding[i];
			int v = eBuilding[i];
			int w = mDistance[i];
			
			graph[u].add(new Node(v, w));
			graph[v].add(new Node(u, w));
		}
		distToCoffee = new int[N];
		distToBakery = new int[N];
		notHomes = new boolean[N];
		return;
	}

	public void add(int sBuilding, int eBuilding, int mDistance) {
		graph[sBuilding].add(new Node(eBuilding, mDistance));
		graph[eBuilding].add(new Node(sBuilding, mDistance));
		
		return;
	}

	public int calculate(int M, int mCoffee[], int P, int mBakery[], int R) {
		Arrays.fill(distToCoffee, INF);
		Arrays.fill(distToBakery, INF);
		Arrays.fill(notHomes, false);
		dijkstra(mCoffee, distToCoffee);
		dijkstra(mBakery, distToBakery);
		int result = getMin(R, M, P);
		//System.out.println(result);
		return result;
	}
		
	private int getMin(int R, int M, int P) {
		int min = INF;
		for(int i = 0; i < N; i++) {
			int coffee = distToCoffee[i];
			int bakery = distToBakery[i];
			if(notHomes[i]) continue;
			if(coffee > R || bakery > R) continue;
			if(coffee == 0 || bakery == 0) continue;
			min = Math.min(min, coffee+bakery);
		}
		if (min == INF) return -1;
		else return min;
	}
	
	private void dijkstra(int[] startNodes, int[] dist) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i = 0; i < startNodes.length; i++) {
			if(startNodes[i] < 0) continue;
			pq.add(new Node(startNodes[i], 0));
			notHomes[startNodes[i]] = true;
			dist[startNodes[i]] = 0;
			
		}
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int idx = node.idx;
			int cost = node.cost;
			if(dist[idx] < cost) continue;
			for(Node next: graph[idx]) {
				if(dist[next.idx] > next.cost + cost) {
					int newCost = next.cost + cost;
					dist[next.idx] = newCost;
					pq.add(new Node(next.idx, newCost));
				}
			}
		}
	}
}
