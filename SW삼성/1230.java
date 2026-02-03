import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class SWEA_1230_암호문3_조유림 {
	private static BufferedReader br;
	
	public static void processCommand(LinkedList<Integer> list, StringTokenizer st) {
		
		char commandChar = st.nextToken().charAt(0);
		
		switch(commandChar) {
		case 'I':
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			ListIterator<Integer> insertIt = list.listIterator(x);
			for(int i = 0; i < y; i++) {
				insertIt.add(Integer.parseInt(st.nextToken()));
			}
			
			break;
		
		case 'D':
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			ListIterator<Integer> deleteIt = list.listIterator(x);
			for(int i = 0; i < y; i++) {
				// 다음값이 있는지 확인하는 것이 좋다.
				if (deleteIt.hasNext()) {
					deleteIt.next();
					deleteIt.remove();
				}
			}
			
			break;
		
		case 'A':
			y = Integer.parseInt(st.nextToken());
			
			ListIterator<Integer> appendIt = list.listIterator(list.size());
			for(int i = 0; i < y; i++) {
				appendIt.add(Integer.parseInt(st.nextToken()));
			}	
			
			break;
		}
	}
	
	public static void printResult(LinkedList<Integer> list, int testcase) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("#").append(testcase);

		ListIterator<Integer> it = list.listIterator();
		for(int i = 0; i < 10; i++) {
			sb.append(" ").append(it.next());
		}
		
		System.out.println(sb);
	}
		
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		// 토큰을 다 쓰면 NoSuchElementException이 터질 수 있다.
		StringTokenizer st = null;
		
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			LinkedList<Integer> list = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			for(int x = 0; x < M; x++) {
				processCommand(list, st);
			}
			
			printResult(list, i+1);
		}
	}
}
