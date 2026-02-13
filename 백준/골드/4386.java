import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_4386_별자리만들기 {
	static int N; //별자리 개수
	static double[][] freckles; // 별자리의 위치
	static int[] parents;
	static double total;
	static double[][] distances; // 별자리 간의 거리
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		freckles = new double[N+1][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			freckles[i][0] = Double.parseDouble(st.nextToken());
			freckles[i][1] = Double.parseDouble(st.nextToken());
		}
		
		cnt = 0;
		for(int i = N-1; i > 0; i--) {
			cnt += i;
		}
		distances = new double[cnt][3];
		getDistances();
		Arrays.sort(distances, (d1, d2) ->Double.compare(d1[2], d2[2]));
		total = 0;
		parents = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			parents[i] = i;
		}
		
		mst();
		System.out.println(String.format("%.2f",total));
	}
	
	public static void getDistances() {
		int c = 0;
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				double ax = freckles[i][0];
				double ay = freckles[i][1];
				double bx = freckles[j][0];
				double by = freckles[j][1];
				double distance = Math.sqrt(Math.pow(Math.abs(ax-bx), 2)+Math.pow(Math.abs(ay-by), 2));
				distances[c][0] = i+1;
				distances[c][1] = j+1;
				distances[c][2] = distance;
				c++;
			}
		}
	}
	
	public static int find(int x) {
		if(parents[x] != x) {
			parents[x] = find(parents[x]);
		}
		return parents[x];
	}

	public static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA != pB) {
			parents[pB] = pA;
		}
	}
	
	public static void mst() {
		for(int i = 0; i < distances.length; i++) {
			int a = (int) distances[i][0];
			int b = (int) distances[i][1];
			double distance = distances[i][2];
			if(find(a) != find(b)) {
				union(a,b);
				total += distance;
			}
		}
	}
}
