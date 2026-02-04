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
public class SWEA_6808_규영이와_인영이의_카드게임_조유림 {
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
		boolean[] used = new boolean[19];
		for (int card : guyoung) used[card] = true;

		List<Integer> yinyoung = new ArrayList<>();
		for (int i = 1; i <= 18; i++) {
		    if (!used[i]) yinyoung.add(i);
		}
		
		return yinyoung;
	}
	
	/*
	 * 가지치기
	 * 남은 카드로 인영이 얻을 수 있는 최대 점수 보다 규영 점수가 크다면 규영이 승리.
	 * 남은 카드로 규영이 얻을 수 있는 최대 점수 보다 인영 점수가 크다면 인영 승리. 
	 * */
	
	/*
	 * 완전 탐색(순열)
	 * 인영 카드 순열 생성 및 결과 계산
	 * */
	// 완전탐색(순열)
	public static void permute(
			int depth, 
			boolean[] isSelected, 
			List<Integer> yinyoung, 
			List<Integer> guyoung,
			int gScore,
			int yScore) {
		
		if (depth == CARD_COUNT) {
	        if (gScore > yScore) winCount++;
	        else if (gScore < yScore) loseCount++;
	        return;
	    }

	    for (int i = 0; i < CARD_COUNT; i++) {
	        if (isSelected[i]) continue;
	        
	        isSelected[i] = true;
	        
	        int gCard = guyoung.get(depth);
	        int yCard = yinyoung.get(i);
	        int sum = gCard + yCard;

	        if (gCard > yCard) {
	            permute(depth + 1, isSelected, yinyoung, guyoung,
	                    gScore + sum, yScore);
	        } else {
	            permute(depth + 1, isSelected, yinyoung, guyoung,
	                    gScore, yScore + sum);
	        }
	        
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
			
			permute(0, isSelected, yinyoung, guyoung, 0, 0);
			System.out.println("#"+t+" "+winCount+" "+loseCount);
		}
	}
}
