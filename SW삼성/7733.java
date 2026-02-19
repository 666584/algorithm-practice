import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7733_치즈도둑_조유림 {
	static int N;
	static int T;
	static int[][] map;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int max;
	static int minDay;
	static int maxDay;
	static boolean[][] visited;
	// 시작시간: 1시 43분
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			minDay = 101;
			maxDay = 0;
			for(int i = 0; i < N ;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					int day = Integer.parseInt(st.nextToken());
					map[i][j] = day;
					minDay = Math.min(minDay, day);
					maxDay = Math.max(maxDay, day);
				}
			}
			max = 0;
			simulate();
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void bfs(int start) {
		int blocks = 0;
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				if(map[i][j] < start) continue;
				Queue<int[]> queue = new ArrayDeque<>();
				queue.add(new int[] {i, j});
				visited[i][j] = true;
				while(!queue.isEmpty()) {
					int[] curr = queue.poll();
					int x = curr[0];
					int y = curr[1];
					
					for(int z = 0; z < 4; z++) {
						int nx = x + dx[z];
						int ny = y + dy[z];
						
						if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
						if(map[nx][ny] < start || visited[nx][ny]) continue;
						
						visited[nx][ny] = true;
						queue.add(new int[] {nx, ny});
					}
				}
				blocks++;
			}
		}
		max = Math.max(max, blocks);
	}
	
	public static void simulate() {
		for(int i = minDay; i <= maxDay; i++) {
			bfs(i);
		}
	}

}
