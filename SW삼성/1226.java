import java.util.Scanner;

public class SWEA_1226_¹Ì·Î1_Á¶À¯¸² {
	static Scanner sc = new Scanner(System.in);
	static int[] direction_x = {-1, 1, 0, 0};
	static int[] direction_y = {0, 0, -1, 1};
	
	public static int[][] input(){	
		int[][] matrix = new int[16][16];
		for(int i = 0; i < 16; i++) {
			String line = sc.next();
			for(int j = 0; j < 16; j++) {
				matrix[i][j] = line.charAt(j) -'0';
			}
		}
		
		return matrix;
	}
	
	public static int[] findStart(int[][] matrix) {
		int[] start = new int[2];
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				if(matrix[i][j] == 2) {
					start[0] = i;
					start[1] = j;
					return start;
				}
			}
		}
		return start;
	}
	
	public static int isReachable(int[][] matrix, int[] currPoint, int N) {
		
		for(int i = 0; i < 4; i++) {
			int nextX = direction_x[i] + currPoint[0];
			int nextY = direction_y[i] + currPoint[1];
			if(nextX < N && nextX >= 0 && nextY < N && nextY >=0) {
				int[] nextPoint = {nextX, nextY};
				if (matrix[nextX][nextY] == 0) {
					matrix[nextX][nextY] = 1;
					if (isReachable(matrix, nextPoint, N) == 1) {
						return 1;
					}
				}
				else if(matrix[nextX][nextY] == 3) {
					return 1;
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		for(int x = 0; x < 10; x++) {
			int testcase = sc.nextInt();
			int[][] matrix = input();
			int[] startPoint = findStart(matrix);
			int result = isReachable(matrix, startPoint, 16);
			System.out.println("#"+testcase+" "+ result);
		}
	}
}
