import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 상하좌우 이동 가능. 
 * 이동하려는 방 번호가 1 커야 한다.
 * 가장 많은 개수의 방을 이동할 수 있는 시작방 구하기.
 * 그중 가장 작은 번호의 방을 출력. 최대 몇개의 방을 이동할 수 있는지 .
 * */
public class SWEA_1861_정사각형_방_조유림 {
	static int T, N, maxRooms, roomNo;
	static int[][] matrix;
	static int[] visitRooms;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			matrix = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visitRooms = new int[N*N+1];
			Arrays.fill(visitRooms, -1);
			maxRooms = 0;
			roomNo = N*N;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int currRoom  = matrix[i][j];
					int result = findMaxRooms(currRoom, 1, i, j);
					//System.out.println(result+"result");
					visitRooms[currRoom] = result;
					if(maxRooms < result) {
						maxRooms = result;
						roomNo = currRoom;
					}else if(maxRooms == result) {
						roomNo = Math.min(roomNo, currRoom);
					}
					//System.out.println(Arrays.toString(visitRooms));
				}
			}
			//System.out.println(Arrays.toString(visitRooms));
			System.out.println("#"+t+" " + roomNo +" "+maxRooms);
			
		}
		
	}
	
	public static int findMaxRooms(int currRoom, int currTotal, int i, int j) {
		//System.out.println("currRoom: "+ currRoom+ "currTotal"+ currTotal);
		if(currRoom == N*N) {
			//System.out.println(currRoom+ " "+currTotal);
			return currTotal;
		}
		
		// 사방검색
		for(int k = 0; k < 4; k++) {
			int nx = i+dx[k];
			int ny = j+dy[k];
			if(nx < N && ny < N && nx >= 0 && ny >= 0) {
				int nextRoomNo = matrix[nx][ny];
				if(currRoom + 1 == nextRoomNo) {
					if(visitRooms[nextRoomNo] != -1) {
						currTotal += visitRooms[nextRoomNo];
						return currTotal;
					}
					int curResult = findMaxRooms(nextRoomNo, currTotal+1, nx, ny);
					//System.out.println("curResult"+curResult);
					//System.out.println("currRoom:"+currRoom);
					
					return curResult;
				}
			}
		}
		
		return currTotal;
	}
}
