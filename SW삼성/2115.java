import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2115_벌꿀채취{
	private static int N; // 벌통들의 크기 N*N
	private static int M; // 벌통의 수
	private static int C; // 채취할 수 있는 꿀의 최대 양
	private static int[][] honeys;
	private static int[][] honeyA;
	private static int cntA;
	private static int[][] honeyStorage;
	private static int sCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			honeys = new int[N][N];
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < N; k++) {
					honeys[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			honeyA = new int[N*N][M];
			cntA = 0;
			honeyStorage = new int[N*N][M];
			sCnt = 0;
			permute(0,0);
			//System.out.println(Arrays.toString(honeyA));
			/*for(int j = 0; j < cntA; j++) {
				for(int k = 0; k < M; k++) {
					System.out.print(honeyA[j][k]);
				}
				System.out.println();
			}*/
			int result = findMaxHoney();
			System.out.println(result);
		}
	}
	
	// 순열 (가능한 모든 블럭)
	// B: 같은 공간에 있지 않는 (같은 윈도우에 있지 않은 그 수를 제외한 모든 수) 
	public static void permute(int start, int cnt) {
		if (start == N) return;
		
		for(int i = 0; i <= N-M; i++) {
			for(int j = 0; j < M && j < N; j++) {				
				honeyA[cntA][j] = honeys[start][i+j];
			}
			cntA++;
		}
		
		permute(start+1, cnt);
	}
	
	// 최대 2개만 선택 가능하다. 
	// 조합 (각 블럭별 조합)
	public static int[] combination(int[] numbers, int totalCnt, int startIndex, int[] pHoney) {	
 		if(pHoney.length == 1) {
 			honeyStorage[sCnt][0] = pHoney[0];
 			honeyStorage[sCnt][1] = 0;
 			sCnt++;
 			return numbers;
 		}
		
		int[] result = new int[2];
		if(totalCnt == 2) {
			//System.out.println("result"+Arrays.toString(numbers));
			honeyStorage[sCnt][0] = numbers[0];
			honeyStorage[sCnt][1] = numbers[1];
			sCnt++;
			return numbers;
		}
 		
		for(int i = startIndex; i < pHoney.length; i++) {
			numbers[totalCnt] = pHoney[i];
			
			combination(numbers, totalCnt+1, i+1, pHoney);
		}
		return result;
	}
	
	// 각 블록별 가장 높은 점수 구하기.
	public static int findMaxScoreByElement(int[][] honeyStorage, int sCnt) {
		int max = 0;
		
		for(int i = 0; i < sCnt; i++) {	
			int total = 0;
			int[] numbers = honeyStorage[i];
			if (numbers[0] > C && numbers[1] <= C) {
				total += numbers[1] * numbers[1];
			}else if(numbers[0] <= C && numbers[1] > C) {
				total += numbers[0] * numbers[0];
			}else if(numbers[0] + numbers[1] <= C) {
				total += (numbers[0] * numbers[0] + numbers[1] * numbers[1]);
			}else if (numbers[0] + numbers[1] > C) {
				int maxNum =  Math.max(numbers[0], numbers[1]);
				total += (maxNum * maxNum);
			}
			
			if(total > max) {
				max = total;
				System.out.println("max");
				System.out.println(numbers[0]);
				System.out.println(numbers[1]);
			}
		}
		
		return max;
	}
	
	// 겹치는 숫자가 있는지 판단
	public static boolean isNotSame(int[] A, int[] B) {
		for(int b: B) {
			if (Arrays.asList(A).contains(b)) return false;
		}
		return true;
	}
	
	
	public static int findMaxHoney() {
		int lineCnt = N - M + 1;
		int max = 0;
		for(int i = 0; i < cntA; i++) {
			int[] honey1 = honeyA[i];
			int currLine = i / lineCnt;
			int numbers[] = new int[2];
			honeyStorage = new int[N*N][2];
			sCnt = 0;
			combination(numbers, 0, 0, honey1);
			for(int k = 0; k < sCnt; k++) {
				System.out.println("a: "+Arrays.toString(honeyStorage[k]));
			}
			
			for(int j = 0; j < cntA; j++) {
				int jLine = j / lineCnt;
				if(jLine != currLine ) {
					numbers = new int[2];
					int score1 = findMaxScoreByElement(honeyStorage, sCnt);
					honeyStorage = new int[N*N][2];
					sCnt = 0;
					combination(numbers, 0, 0, honeyA[j]);
					for(int k = 0; k < sCnt; k++) {
						System.out.println("b: "+Arrays.toString(honeyStorage[k]));
					}
					int score2 = findMaxScoreByElement(honeyStorage, sCnt);
					if(max < score1+score2) {
						max = score1+score2;
					}
				}else if (jLine == currLine && isNotSame(honey1, honeyA[j])) {
					numbers = new int[2];
					int score1 = findMaxScoreByElement(honeyStorage, sCnt);
					honeyStorage = new int[N*N][2];
					sCnt = 0;
					combination(numbers, 0, 0, honeyA[j]);
					for(int k = 0; k < sCnt; k++) {
						System.out.println("b: "+Arrays.toString(honeyStorage[k]));
					}
					int score2 = findMaxScoreByElement(honeyStorage, sCnt);
					if(max < score1+score2) {
						max = score1+score2;
					}
				}
			}
		}
		
		return max;
	}
	
}
