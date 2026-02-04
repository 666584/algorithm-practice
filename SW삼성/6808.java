import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	final static int SIZE = 9;
	static BufferedReader br;
	static int win;
	static int lose;
	public static List<Integer> input(StringTokenizer st) {
		
		List<Integer> guyoung = new ArrayList<>();
		for(int i = 0; i < SIZE; i++) {
			guyoung.add(Integer.parseInt(st.nextToken())); 
		}
		
		return guyoung;
	}
	
	/*
	 * 인영이 가진 카드 구하기
	 * 규영이 카드 9장을 뺀 1~18 사이의 9개 숫자.
	 * */
	public static List<Integer> getYinyoung(List<Integer> guyoung) {
		List<Integer> yinyoung = new ArrayList<>();
		for(int i = 1; i < 18+1; i++) {
			if(!guyoung.contains(i)) yinyoung.add(i);
		}
		
		return yinyoung;
	}
	
	/*
	 * 규영이가 지면 -1, 비기면 0, 이기면 1을 반환한다.
	 * */
	public static int isGuyoungWin(int[] cards, List<Integer> guyoung) {
		int yTotal = 0;
		int gTotal = 0;
		for(int i = 0; i < 9; i++) {
			int score = guyoung.get(i)+cards[i];
			if(guyoung.get(i) > cards[i]) {
				gTotal += score;
			} else if(guyoung.get(i) < cards[i]) {
				yTotal += score;
			}
		}
		
		if(yTotal < gTotal) return 1;
		else if (yTotal > gTotal) return -1;
		return 0;
	}
	
	// 완전탐색(순열)
	public static void getWinAndLost(int cnt, boolean[] isSelected, int[] cards, List<Integer> yinyoung, List<Integer> guyoung) {
		if (cnt == 9) {
	        /*for (int card : cards) {
	            System.out.print(card + " ");
	        }*/
	        int result = isGuyoungWin(cards, guyoung);
	        //System.out.println(result);
	        if(result == 1) win++;
	        else if(result == -1) lose++;
	        return;
	    }

	    for (int i = 0; i < 9; i++) {
	        if (isSelected[i]) continue;	        
	        cards[cnt] = yinyoung.get(i);
	        isSelected[i] = true;
	        getWinAndLost(cnt + 1, isSelected, cards, yinyoung, guyoung);	        
	        isSelected[i] = false;
	    }
	    
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N+1; i++) {
			win = 0;
			lose = 0;
			st = new StringTokenizer(br.readLine());
			List<Integer> guyoung = input(st);
			List<Integer> yinyoung = getYinyoung(guyoung);
			boolean[] isSelected = new boolean[9];
			int[] cards = new int[9];
			getWinAndLost(0, isSelected, cards, yinyoung, guyoung);
			System.out.println("#"+i+" "+win+" "+lose);
		}
	}
}
