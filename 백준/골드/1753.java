import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 방향이 있는 그래프!!!
 * O(V² + V·E) 시간 복잡도
 * */
public class BJ__1753_최단경료_조유림 {
	static int V, E; //정점 개수, 간선 개수
	static int[] distance; //각 정점별 시작점으로부터의 거리
	static boolean[] visited;
	static int[][] info; //간선 정보
	static int start; //시작점 정점 정보.
	
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		info = new int[E][3];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
			info[i][2] = Integer.parseInt(st.nextToken());
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
 	
 	public static int getDistance(int a, int b) {
 		for(int i = 0; i < E; i++) {
 			int f = info[i][0];
			int s = info[i][1];
			if(f == a && s == b) {
				return info[i][2];
			}
 		}
 		return -1;
 	}
 	
 	public static int findNext() {
 		int next = 0;
 		int min = Integer.MAX_VALUE;
 		int totalVisited = 0;
 		for(int i = 1; i < V+1; i++) {
 			if(!visited[i]) {
 				if(distance[i] < min) {
 					min = distance[i];
 					next = i;
 				}
 			}else totalVisited++;
 		}
 		if (totalVisited == V || next == 0) return -1;
 		else return next;
 	}
 	
 	public static void dijkstra() {
 		Deque<Integer> queue = new ArrayDeque<>();
 		queue.add(start);
 		distance[start] = 0;
 		while(!queue.isEmpty()) {
	 		int curr = queue.poll();
	 		visited[curr] = true;
	 		for(int i = 1; i < V+1; i++) {
	 			if(!visited[i]) {
	 				//거리 구하기. 
	 				int d = getDistance(curr, i);
	 				if(d != -1) distance[i] = Math.min(distance[i], distance[curr] + d);//curr에서 i 정점 까지의 거리.
	 			}
	 		}
	 		int next = findNext();
	 		if(next == -1) break;
	 		queue.add(findNext());
 		}
 		
 	}

}
