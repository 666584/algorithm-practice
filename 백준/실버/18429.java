import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_18429_근손실_조유림 {
	private static int N, K; // N: 운동키트 개수/운동일, K: 감소 중량/하루
	private static int[] weights; // 운동키트 별 중량 증가량
	private static int total;
	
	// 매일 중량이 500이상 
	// 하루 하나의 운동 키트만 가능.

	//완전탐색 (순열)
	// 중복 안됨. 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		weights = new int[N];
		for(int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		boolean[] isSelected = new boolean[N];
		int[] numbers = new int[N];
		findRoutine(0, 500, isSelected, numbers);
		System.out.println(total);
	}
	
	public static void findRoutine(int cnt, int amount, boolean[] isSelected, int[] numbers) {
		
		if(amount < 500) {
			return;
		}
		
		if(cnt == N) {
			total++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				numbers[cnt] = weights[i];
				findRoutine(cnt+1, amount+weights[i]-K, isSelected, numbers);
				isSelected[i] = false;
			}
		}
	}
}
