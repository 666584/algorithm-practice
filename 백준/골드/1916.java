import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1916_최소비용구하기 {
	static int N, M; // 도시 개수, 간선 수 
	static ArrayList<Node>[] info;
	static int start, end;
	static boolean[] visited;
	static int[] distance;
	
	static class Node implements Comparable<Node>{
		int n, weight;
		Node(int n, int weight){
			this.n = n;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		info  = new ArrayList[N+1];
		for(int i = 1; i < N+1; i++) {
			info[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			info[a].add(new Node(b, cost));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		distance = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		dijkstra();
		System.out.println(distance[end]);
	}
	
	public static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start,0));
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			if(curr.n == end) return;
			if(visited[curr.n]) continue;
			visited[curr.n] = true;
			for(Node next: info[curr.n]) {
				if(distance[next.n] > curr.weight + next.weight) {
					distance[next.n] = curr.weight + next.weight;
					pq.add(new Node(next.n, distance[next.n]));
				}
			}
		}
	}
}
