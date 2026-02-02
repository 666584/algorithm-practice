import java.util.Scanner;
import java.util.LinkedList;

public class SWEA_1230_암호문3_조유림 {
	static Scanner sc = new Scanner(System.in);

	public static void insert(int x, int y, LinkedList<Integer> s, LinkedList<Integer> list) {
		list.addAll(x, s);
	}
	
	public static void delete(int x, int y, LinkedList<Integer> list) {
		for(int i = x; i < y; i++) {
			list.remove(x);
		}
	}
	
	public static void addMore(int y, LinkedList<Integer> s, LinkedList<Integer> list) {
		list.addAll(list.size(), s);
	}
	
	public static void operate(LinkedList<Integer> list) {
		char commandChar = sc.next().charAt(0);
		switch(commandChar) {
		case 'I':
			int x = sc.nextInt();
			int y = sc.nextInt();
			LinkedList<Integer> s = new LinkedList<>();
			for(int i = 0; i < y; i++) {
				s.add(sc.nextInt());
			}
			insert(x, y, s, list);
			return;
		case 'D':
			x = sc.nextInt();
			y = sc.nextInt();
			delete(x, y, list);
			return;
		case 'A':
			y = sc.nextInt();
			s = new LinkedList<>();
			for(int i = 0; i < y; i++) {
				s.add(sc.nextInt());
			}
			addMore(y, s, list);
			return;
		}
	}
		
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			int N = sc.nextInt();
			LinkedList<Integer> list = new LinkedList<>();
			for(int j = 0; j < N; j++) {
				list.add(sc.nextInt());
			}
			int M = sc.nextInt();
			for(int x = 0; x < M; x++) {
				operate(list);
			}
			System.out.print("#"+(i+1));
			for(int y = 0; y < 10; y++) {
				System.out.print(" "+list.get(y));
			}
			System.out.println();
		}
	}
}
