import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 시간 복잡도: O(9!)
 * 9! = 3265920
 * */
public class Main {
	private static final int CARD_COUNT = 9;
    private static BufferedReader br;

    private static int winCount;
    private static int loseCount;
    
    /*
     * 규영 카드 입력
     * */
	public static List<Integer> input(StringTokenizer st) {
		
		List<Integer> guyoung = new ArrayList<>();
		for(int i = 0; i < CARD_COUNT; i++) {
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
		for(int i = 1; i <= 18; i++) {
			if(!guyoung.contains(i)) yinyoung.add(i);
		}
		
		return yinyoung;
	}
	
	/*
	 * 경기 결과 판정
	 * 규영이가 지면 -1, 비기면 0, 이기면 1을 반환한다.
	 * */
	public static int compareScore(int[] yinyoungOrder, List<Integer> guyoung) {
		int guyoungScore = 0;
		int yinyoungScore = 0;

		for(int i = 0; i < CARD_COUNT; i++) {
			int sum = guyoung.get(i)+yinyoungOrder[i];
			if(guyoung.get(i) > yinyoungOrder[i]) {
				guyoungScore += sum;
			} else if(guyoung.get(i) < yinyoungOrder[i]) {
				yinyoungScore += sum;
			}
		}
		
		if (guyoungScore > yinyoungScore) return 1;
        if (guyoungScore < yinyoungScore) return -1;
		return 0;
	}
	
	/*
	 * 완전 탐색(순열)
	 * 인영 카드 순열 생성 및 결과 계산
	 * */
	// 완전탐색(순열)
	public static void permute(
			int depth, 
			boolean[] isSelected, 
			int[] yinyoungOrder, 
			List<Integer> yinyoung, 
			List<Integer> guyoung) {
		
		if (depth == CARD_COUNT) {
	        int result = compareScore(yinyoungOrder, guyoung);
	        if(result == 1) winCount++;
	        else if(result == -1) loseCount++;
	        return;
	    }

	    for (int i = 0; i < CARD_COUNT; i++) {
	        if (isSelected[i]) continue;
	        
	        yinyoungOrder[depth] = yinyoung.get(i);
	        isSelected[i] = true;
	        permute(depth + 1, isSelected, yinyoungOrder, yinyoung, guyoung);	        
	        isSelected[i] = false;
	    }
	    
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= testCase; t++) {
			winCount = 0;
			loseCount = 0;
			
			st = new StringTokenizer(br.readLine());
			List<Integer> guyoung = input(st);
			List<Integer> yinyoung = getYinyoung(guyoung);
			
			boolean[] isSelected = new boolean[CARD_COUNT];
			int[] cards = new int[CARD_COUNT];
			
			permute(0, isSelected, cards, yinyoung, guyoung);
			System.out.println("#"+t+" "+winCount+" "+loseCount);
		}
	}
}
