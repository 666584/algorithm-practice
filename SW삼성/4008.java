import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int T, N; //T: 테스트 개수, N: 숫자 개수
	public static int plus, minus, times, divide; // 각 연산자의 개수
	public static int[] numbers;// 수식에 사용되는 숫자
	public static int min, max;
	public static int dC;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			boolean isDuplicated = false; 
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			plus = Integer.parseInt(st.nextToken());
			minus = plus + Integer.parseInt(st.nextToken());
			times = minus + Integer.parseInt(st.nextToken());
			divide = times + Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			numbers = new int[N];
			for(int n = 0; n < N; n++) {
				numbers[n] = Integer.parseInt(st.nextToken());
			}
			
			if(plus == N-1 || minus-plus == N-1 || times-minus == N-1 || divide-times == N-1) {
				min = 0;
				max = 0;
				isDuplicated = true;
			}
			
			if(!isDuplicated) {
				List<Integer> duplicated = new ArrayList<>();
				dC = 0;
				min = 100_000_001;
				max = -100_000_001;
				findMaxMin(0, numbers[0], 0, duplicated); //N-1: 연산자 총 개수
			}
			System.out.println("#"+tc+" "+(max-min));
		}
	}
	
	public static void findMaxMin(int cnt, int result, int flag, List<Integer> duplicated){
		if(cnt == N-1) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}
		
		for(int i = 0; i < N-1; i++) {
			if((flag & 1<<i) != 0) continue;
			// 이미 같은 범위의 operator를 사용하려고 한다면 continue한다. 
			boolean isTaken = false;
			for(int duplicate: duplicated) {
				if(duplicate == 1 && i < plus) {
					isTaken = true;
					break;
				}
				if(duplicate == 2 && i < minus) {
					isTaken = true;
					break;
				}
				if(duplicate == 3 && i < times) {
					isTaken = true;
					break;
				}
				if(duplicate == 4 && i < divide) {
					isTaken = true;
					break;
				}
			}
			if(isTaken) continue;
			if(i < plus) {
    			findMaxMin(cnt+1,result+numbers[cnt+1], flag | 1<<i, new ArrayList<>());
    			// 다음 같은 cnt 일때 확인. 
    			duplicated.add(1);
			}
			else if(i < minus) {
    			findMaxMin(cnt+1, result-numbers[cnt+1], flag | 1<<i, new ArrayList<>());
    			duplicated.add(2);
			}
			else if(i < times) {
    			findMaxMin(cnt+1, result*numbers[cnt+1], flag | 1<<i, new ArrayList<>());
    			duplicated.add(3);
			}
			else if(i < divide) {
    			findMaxMin(cnt+1, result/numbers[cnt+1], flag | 1<<i, new ArrayList<>());
    			duplicated.add(4);
			}
		}
	}
}
