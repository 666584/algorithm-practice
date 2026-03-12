import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10868_최솟값_조유림 {
	static int N;
	static int M;
	static long[] arr;
	static long[] tree;
	public static void main(String[] args) throws IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  StringTokenizer st = new StringTokenizer(br.readLine());
		  N = Integer.parseInt(st.nextToken());
		  M = Integer.parseInt(st.nextToken());
		  arr = new long[N+1];
		  
		  tree = new long[4*N];
		  for(int i = 1; i <= N; i++) {
			  arr[i] = Long.parseLong(br.readLine());
		  }
		  
		  build(1,1,N);
		  
		  for(int i = 0; i < M ; i++) {
			  st = new StringTokenizer(br.readLine());
			  int a = Integer.parseInt(st.nextToken());
			  int b = Integer.parseInt(st.nextToken());
			  long result = query(1, 1, N, a,b);
			  System.out.println(result);
		  }
	}
	
	public static void build(int node, int start, int end) {
		if(start == end) {
			tree[node] = arr[start];
			return;
		}
		
		int mid = (start+end)/2;
		build(node*2, start, mid);
		build(node*2+1, mid+1, end);
		tree[node] = Math.min(tree[node*2],tree[node * 2 + 1]);
	}
	
	public static long query(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return Long.MAX_VALUE;
		}
		
		if(left <= start && right >= end) {
			return tree[node];
		}
		int mid = (start + end)/2;
		
		long leftResult = query(node * 2, start, mid, left, right);
		long rightResult = query(node * 2 + 1, mid+1, end, left, right);
		return Math.min(leftResult, rightResult);
	}
}
