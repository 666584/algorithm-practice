import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 짝수날에 물을 주면 2가 자라고 홀수날에 물을 주면 1이 자란다. 
 * 하루에는 한 나무에만 물을 줄 수 있다.
 * 물을 안 주는 것도 가능하다. 
 * 모든 나무의 키가 가장 큰 나무의 키와 같아지게 하기 위한 최소 날짜 수.
 * */
public class SWEA_14510_나무높이_조유림 {
	static int T, N, maxHeight; //N: 나무 개수
	static int trees[]; //나무 높이 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			trees = new int[N];
			for(int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(trees);
			maxHeight = trees[N-1];
			int day = getDays();
			System.out.println("#"+ t + " "+day);
		}
	}
	public static int getDays() {
		int cur = N-2;
		int day = 0;
		boolean[] isMaxHeight = new boolean[N-1];
		while(cur >= 0) {
			int addWater = 0;
			if(day%2 != 0) {
				addWater = 2;
			}else addWater = 1;
			boolean isWatered = false;
			for(int i = N-2; i >= 0; i--) {
				if(!isMaxHeight[i]) {
					if(trees[i] == maxHeight) {
						isMaxHeight[i] = true;
						isWatered = true;
						cur--;
						break;
					}
					else if(trees[i]+addWater == maxHeight) {
						isMaxHeight[i] = true;
						isWatered = true;
						cur--;
						day++;
						break;
					}
					else if(trees[i]+2 < maxHeight) {
						isWatered = true;
						day++;
						trees[i] += addWater;
						break;
					}
				}
			}
			if(!isWatered) {
				day++;
			}
		}
		return day;
		
	}
	
	
}
