import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	//군집 리스트 
	static ArrayList<Association> associations;
	static int M;
	static int[][] map; // 군집 개수 total
	static int[][] mapMax; // 군집 최고 값
	static int N, T;
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,-1,1};

	
	public static class Association{
		int total;
		int r,c;
		int direction;
		int no; // 군집 번호
		
		Association (int no, int total, int r, int c, int direction){
			this.no = no;
			this.total = total;
			this.r = r;
			this.c = c;
			this.direction = direction;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//= new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			associations = new ArrayList<>();
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				associations.add(new Association(i,size, r, c, direction));
			}
			//System.out.println("size"+associations.size());
			simulate();
			int totalCnt = 0;
			//System.out.println("size"+associations.size());
			for(int j = 0; j < associations.size(); j++) {
				Association association = associations.get(j);
				totalCnt += association.total;
			}
			System.out.println("#"+t+" "+totalCnt);
		}
	}
	
	// map에 현재 군집 저장하기 
	// 이동할 때 map에 반영하면서 가장 큰 군집만 저장하기. 2개 이상인지를 확인하기.
	// 병합하기
	public static void simulate() {
		for(int i = 1; i <= M; i++) {
			map = new int[N][N];
			mapMax = new int[N][N];
			for(int j = associations.size() - 1; j >= 0; j--) {
				Association association = associations.get(j);
				int r = association.r + dx[association.direction];
				int c = association.c + dy[association.direction];
				if(r == 0 || r == N-1 || c == 0 || c == N-1) {
					association.total /= 2;
					//System.out.println(association.total);
					if(association.total == 0) {
						associations.remove(j);
						continue;
					}
					switch(association.direction) {
					case 1:{
						association.direction = 2;
						break;
					}
					case 2:{
						association.direction = 1;
						break;
					}
					case 3:{
						association.direction = 4;
						break;
					}
					case 4:{
						association.direction = 3;
						break;
					}
					}
				}
				association.r = r;
				association.c = c;
				if(map[r][c] == 0) {
					map[r][c] = association.total;
					mapMax[r][c] = association.total;
				}
				else {
					map[r][c] += association.total;
					mapMax[r][c] = Math.max(mapMax[r][c], association.total);
				}
			}
			for(int j = associations.size() - 1; j >= 0; j--) {
			    Association association = associations.get(j);
			    int r = association.r;
			    int c = association.c;

			    if(mapMax[r][c] != association.total) {
			        associations.remove(j);
			    }else {
			    	association.total = map[r][c];
			    }
			}
		}
	}
}
