import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1816_파핑파핑지뢰찾기_조유림 {
	static int T, N;
	static int[][] map;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	static int cx = -1, cy = -1;
	static int totalCnt;
	static int totalNTrap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			totalCnt = 0;
			totalNTrap = 0;
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				for(int j = 0; j < N; j++) {
					char c = line.charAt(j);
					if(c == '.') {
						map[i][j] = -1;
						totalNTrap++;
					}else map[i][j] = -2;
				}
				
			}

			getZero();
			System.out.println("#"+t+" "+(totalNTrap+totalCnt));
		}
	}
	
	public static void getZero() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == -1) {
					Queue<int[]> queue = new ArrayDeque<>();
					boolean isZero = true;
					for(int z = 0; z < 8; z++) {
						int nx = i + dx[z];
						int ny = j + dy[z];
						if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
						if(map[nx][ny] == -2) {
							isZero = false;
							break;
						}
						if(map[nx][ny] == -1) queue.add(new int[] {nx,ny});
					}
					
					if(isZero) {
						map[i][j] = 0;
						totalNTrap--;
						while(!queue.isEmpty()) {
							int cnt = 0;
							int[] curr = queue.poll();
							int x = curr[0];
							int y = curr[1];
							for(int z = 0; z < 8; z++) {
								int nx = x + dx[z];
								int ny = y + dy[z];
								if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
								if(map[nx][ny] == -2) cnt++;
							}
							
							if(map[x][y] == -1) {
								map[x][y] = cnt;
								totalNTrap--;
								if(cnt == 0) {
									for(int z = 0; z < 8; z++) {
										int nx = x + dx[z];
										int ny = y + dy[z];
										if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
										if(map[nx][ny] == -1) queue.add(new int[] {nx, ny});
									}
								}
							}
						}
						totalCnt++;
					}
				}
			}
		}
	}
}
