import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 1일 이용권
 * 2. 한달 이용권: 1일 
 * 3. 3달 이용권: 해를 넘어가면 적용되지 않는다.
 * 4. 1년 이용권: 1월 1일 
 * 가장 적은 비용으로 수영장을 이용할 수 있는 방법의 비용
 * */
public class Main {
	private static int min; // 최소 비용
	private static int T, D, M, M3, Y; // 하루, 한달, 3달, 1년 이용요금.
	private static int[] plans;
	private static int months; //계획이 하루라도 세워진 달 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			M3 = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			plans = new int[12];
			months = 0;
			int p = 0;
			for(int i = 0; i < 12; i++) {
				int day = Integer.parseInt(st.nextToken());
				if(day != 0) {
					months++;
					plans[p++] = day; 
				}
			}
			min = Y;
			dfs(0, 0);
			System.out.println("#"+t+" "+min);
		}	
	}
	
	public static void dfs(int price, int cM) {
		if(price >= min) return;
		if(cM == months) {
			min = Math.min(min, price);
			return;
		}
		
		dfs(price+(D*plans[cM]), cM+1);//1일 이용권
		dfs(price+M, cM+1);//1달 이용권
		if(cM+2 < months) dfs(price+M3, cM+3);//3달 이용권
		else if(cM+1 == months) dfs(price+M3, months);
	}
}
