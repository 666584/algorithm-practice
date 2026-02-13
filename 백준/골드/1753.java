import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 방향이 있는 그래프!!!
 * */
public class BJ__1753_최단경료_조유림 {
	static int V, E; //정점 개수, 간선 개수
	static int[] distance; //각 정점별 시작점으로부터의 거리
	static boolean[] visited;
	static ArrayList<Node>[] info; //간선 정보
	static int start; //시작점 정점 정보.
	
	static class Node implements Comparable<Node>{
		int v, weight;
		Node(int v, int weight) {
			this.v = v;
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
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		info = new ArrayList[V+1];
		for(int i = 1; i <= V; i++) {
			info[i] = new ArrayList<>();
		}
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			info[a].add(new Node(b,w));
		}

		distance = new int[V+1];
		// 1. 거리 초기 설정 (inf)
		for(int i = 1; i < V+1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		visited = new boolean[V+1];
		dijkstra();
		for(int i = 1; i < V+1; i++) {
			int result = distance[i];
			if(result == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else System.out.println(result);
		}
 	}
 	
 	public static void dijkstra() {
 		// 가장 거리가 작은 정점을 꺼내준다. 
 		PriorityQueue<Node> queue = new PriorityQueue<>();
 		queue.add(new Node(start, 0));
 		distance[start] = 0;
 		while(!queue.isEmpty()) {
	 		Node curr = queue.poll();
	 		if(visited[curr.v]) continue;
	 		visited[curr.v] = true;
	 		for(Node next : info[curr.v]) {
	 	       if(distance[next.v] > distance[curr.v] + next.weight) {
	 	            distance[next.v] = distance[curr.v] + next.weight;
	 	            queue.add(new Node(next.v, distance[next.v]));
	 	        }
	 	    }
 		}
 		
 	}

}
