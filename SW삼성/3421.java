import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M; // N: 재료 개수, M: 맞지 않는 쌍 개수 
	static Set<Pair> gradients;
	static int totalBurgers;
	
	static class Pair {
	    private final int first;
	    private final int second;

	    public Pair(int a, int b) {
	        if (a < b) {
	            this.first = a;
	            this.second = b;
	        } else {
	            this.first = b;
	            this.second = a;
	        }
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Pair)) return false;
	        Pair pair = (Pair) o;
	        return pair.first == this.first &&
	               pair.second == this.second;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(first, second);
	    }

	    @Override
	    public String toString() {
	        return "(" + first + ", " + second + ")";
	    }
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			gradients = new HashSet<>();
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				gradients.add(new Pair(a,b));
			}
			
			totalBurgers = 0;
			getTotalBurgers(new boolean[N+1], 1);
			System.out.println("#"+t+" "+totalBurgers);
			
		}
	}
	
	public static void getTotalBurgers(boolean[] isSelected, int cnt) {
		if(cnt == N+1) {
			for(Pair p: gradients) {
				if(isSelected[p.first] && isSelected[p.second]) {
					return;
				}
			}
			totalBurgers++;
			return;
		}
		isSelected[cnt] = false;
		getTotalBurgers(isSelected, cnt+1);
		isSelected[cnt] = true;
		getTotalBurgers(isSelected, cnt+1);
	}
}
