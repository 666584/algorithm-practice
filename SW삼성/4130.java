import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 4개의 자석, 8개의 날
 * N극 또는 S극을 가지고 있다. 
 * 자석은 일렬 배치 되어 있다. 
 * 빨간색 화살표 위치에 날이 있다. 
 * 임의의 자석을 1칸씩 K번 회전 시킨다. 
 * 회전 할때 다른 극을 가지고 있는 옆의 자석 또한 회전된다. (반대 방향으로 회전 된다.)
 * 점수 계산 방식: 1번부터 4번까지 화살표에 자성이 S극이라면  각각 (1,2,4,8) 점을 부여 한다.
 * 
 * 입력값
 * 테스트 개수 : T
 * 자석을 회전하는 횟수: K (1 <= K <= 20)
 * 4개 줄에 1-4번의 8개의 날의 정보 (빨간색 위치 부터 시계방향 순서, N = 0, S = 1)
 * K개 줄에 자석을 회전 시키는 정보 (자석 번호 + (1: 시계방향, -1: 반시계방향)) 
 *  
 * 출력값
 * 점수 총합
 * 
 * */

//배열 시프트
// 초기 상태 확인후 동시에 다른 "모든" 배열의 시프트가 일어난다. (순차적 접근)

/*
 * 풀이 방법1:
 * 1. 회전할 자석들을 먼저 식별한다.
 * 1-1. 회전 여부를 저장한다.
 * 2. 모든 자석의 회전을 업데이트한다. 
 * 
 * 풀이 방법2:
 * 1. 주변 자석의 회전을 먼저 결정한 후에 자신을 회전 시킨다. 
 * 2. 순차적인 영향을 미치지 않기 때문에 이미 회전이 일어나지 않았다면 더이상 회전을 진행하지 않늗다. 
 * */
public class SWEA_4130_특이한_자석 {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int K; 
	private static int[][] magnetic;
	private static int[][] commands;
	
	private static final int NUM_ROLL = 8;
	private static final int NUM_MAG = 4;
 	
	public static void main(String[] args)  throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			input();
			for(int k = 0; k < K; k++) {
				spin(commands[k][0], commands[k][0], 0, commands[k][1], true);
			}
			
			int result = getTotalScore();
			System.out.println("#"+tc+" "+result);
		}
	}
	
	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		magnetic = new int[NUM_MAG+1][NUM_ROLL];
		
		for(int i = 1; i <= NUM_MAG; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < NUM_ROLL; j++) {
				magnetic[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		commands = new int[K][2];
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			commands[k][0] = Integer.parseInt(st.nextToken());
			commands[k][1] = Integer.parseInt(st.nextToken());
		} 
	}
	
	public static void spin(
			int startMag, // 시작 회전 자석
			int magNum, //현재 탐색 자석
			int dir,  // 탐색 방향
			int startDir, //처음 자석의 회전 방향
			boolean prevTurned //이전 탐색 자석이 회전을 하는지 
			) {
		
		//기저
		if(magNum == 5 || magNum == 0) {
			return;
		}
		
		//시작
		if(magNum == startMag) {
			spin(startMag, magNum+1, 1, startDir, true); //오른쪽 탐색
			spin(startMag, magNum-1, 0, startDir, true); //왼쪽 탐색
			turn(magNum, startMag, startDir);
			return;
		}
		
		else {
			boolean currTurn = false;
			if(dir == 1) {
				if(prevTurned) {
					if(magnetic[magNum-1][2] != magnetic[magNum][6]) {
						currTurn = true;
					}
				}
				spin(startMag, magNum+1, dir, startDir, currTurn);
				
				if(prevTurned && magnetic[magNum-1][2] != magnetic[magNum][6]) {
					turn(magNum, startMag, startDir);
				}
			}
			else {
				if(prevTurned) {
					if(magnetic[magNum][2] != magnetic[magNum+1][6]) {
						currTurn = true;
					}
				}
				spin(startMag, magNum-1, dir, startDir, currTurn);
				
				if(prevTurned && magnetic[magNum][2] != magnetic[magNum+1][6]) {			
					turn(magNum, startMag, startDir);
				}
			}
		}		
	}
	
	public static void turn(int magNum, int firstMagNum, int firstDir) {
		int[] newMagnetic = new int[NUM_ROLL];
 		// 처음 자석 회전 방향과 같은 자석: 원래 자석 번호 가 홀수 일때 지금 자석 번호도 홀수
		int dir = 0;
		if(firstMagNum % 2 == 0) {
			if (magNum % 2 == 0) dir = firstDir;
			else dir = -firstDir;
		}
		else {
			if (magNum % 2 != 0) dir = firstDir;
			else dir = -firstDir;
		}
		
		if(dir == -1) {
			newMagnetic[NUM_ROLL-1] = magnetic[magNum][0];
			for(int i = 0; i < NUM_ROLL-1; i++) {
				newMagnetic[i] = magnetic[magNum][i+1];
			}
		}else {
			newMagnetic[0] = magnetic[magNum][NUM_ROLL-1];
			for(int i = 1; i < NUM_ROLL; i++) {
				newMagnetic[i] = magnetic[magNum][i-1];
			}
		}
 		magnetic[magNum] = newMagnetic;
	}
	
	public static int getTotalScore() {
		int score = 0;
		for(int i = 1; i <= NUM_MAG; i++) {
			if(magnetic[i][0] == 1) score += Math.pow(2, i-1);
		}
		return score;
	}
}
