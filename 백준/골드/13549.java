import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ_13549_숨바꼭질3_조유림{
	static int N, K; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bfs();
	}
	
	public static void bfs() {
		ArrayDeque<int[]> deque = new ArrayDeque<>();
		deque.add(new int[] {N, 0});

		boolean[] visited = new boolean[100001];
		visited[N] = true;
		while(!deque.isEmpty()) {
			int[] curr = deque.poll();
			
			int num = curr[0];
			int cost = curr[1];
			//System.out.println(num);
			visited[num] = true;
			if(num == K) {
				System.out.println(cost);
				return;
			}
			
			if (num*2 <= 100000 && !visited[num*2]) { 
				deque.addFirst(new int[] {num*2, cost}); 
			}
			if (num+1 <= 100000 && !visited[num+1]) { 
				deque.addLast(new int[] {num+1, cost+1}); 
			}
			if (num-1 >= 0 && !visited[num-1]) {
				deque.addLast(new int[] {num-1, cost+1});
			}

		}
	}
}
