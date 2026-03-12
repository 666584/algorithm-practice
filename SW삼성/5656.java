import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기_조유림 {
	static int[][] map;
	static int N, H, W;
	static int T;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int totalBlocks;
	static int block;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			totalBlocks = 0; 
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) totalBlocks++;
				}
			}
			//System.out.println(totalBlocks);
			permute(0, map, totalBlocks);
			/*for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}*/
			
			System.out.println("#"+t+" "+totalBlocks);
		}
	}
	
	public static void change(int c, int[][] mapCopy) {
		int r = -1;
		for(int i = 0; i < H; i++) {
			if(mapCopy[i][c] != 0) {
				r = i;
				break;
			}
		}
		if (r == -1) return;
		int cnt = mapCopy[r][c];
		mapCopy[r][c] = 0;
		block++;
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j < cnt; j++) {
				int nx = r + dx[i]*j;
				int ny = c + dy[i]*j;
				if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
				if(mapCopy[nx][ny] == 1) {
					mapCopy[nx][ny] = 0;
					block++;
				}
				if(mapCopy[nx][ny] > 1) {
					boom(nx, ny, mapCopy);
				}
			}
		}
	}
	
	public static void boom(int r, int c, int[][] mapCopy) {
		int cnt = mapCopy[r][c];
		mapCopy[r][c] = 0;
		block++;
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j < cnt; j++) {
				int nx = r + dx[i]*j;
				int ny = c + dy[i]*j;
				if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
				if(mapCopy[nx][ny] == 1) {
					mapCopy[nx][ny] = 0;
					block++;
				}
				if(mapCopy[nx][ny] > 1) {
					boom(nx, ny, mapCopy);
				}
			}
		}
	}
	
	public static void sort(int[][] mapCopy) {
	    for(int j = 0; j < W; j++) {
	        int idx = H - 1; // 벽돌이 채워질 위치

	        for(int i = H - 1; i >= 0; i--) {
	            if(mapCopy[i][j] != 0) {
	            	mapCopy[idx][j] = mapCopy[i][j];
	                if(idx != i) mapCopy[i][j] = 0;
	                idx--;
	            }
	        }

	        // 남은 위쪽은 0으로 채우기
	        for(int i = idx; i >= 0; i--) {
	        	mapCopy[i][j] = 0;
	        }
	    }
	}
	
	//중복 순열
	public static void permute(int n, int[][] currMap, int blocks) {
		if(n == N) {
			totalBlocks = Math.min(blocks, totalBlocks);
			return;
		}
		
		for(int i = 0; i < W; i++) {
			int[][] mapCopy = new int[H][W];
			mapCopy = copyMap(currMap);
			block = 0;
			change(i, mapCopy);
			//System.out.println(block);
			sort(mapCopy);
			permute(n+1, mapCopy, blocks-block);
		}
	}
	
	public static int[][] copyMap(int[][] map){
	    int[][] newMap = new int[H][W];

	    for(int i = 0; i < H; i++){
	        for(int j = 0; j < W; j++){
	            newMap[i][j] = map[i][j];
	        }
	    }

	    return newMap;
	}
}
