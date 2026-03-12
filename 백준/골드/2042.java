import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2042_구간합 {
	static int N, M, K;
	static long[] tree, arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		tree = new long[4*N];
		arr = new long[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		build(1, 1, N);
		for(int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if(a == 1) {
				int index = Integer.parseInt(st.nextToken());
				long num = Long.parseLong(st.nextToken());
				update(1, 1, N, index, num);
			}else {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				long result = query(1,1,N,start,end);
				System.out.println(result);
			}
		}
	}
	public static void build(int node, int start, int end) {
		if(start == end) {
			tree[node] = arr[start];
			return;
		}
		int mid = (start + end)/2;
		build(node * 2, start, mid);
		build(node * 2 + 1, mid + 1, end);
		
		tree[node] = tree[node*2] + tree[node * 2 + 1];
		
	}
	public static void update(int node, int start, int end, int index, long value) {
		if(index < start || index > end) return;
		
		if(start == end) {
			arr[index] = value;
			tree[node] = value;
			return;
		}
		
		int mid = (start + end)/2;
		update(node*2, start, mid, index, value);
		update(node*2+1, mid+1, end, index, value);
		
		tree[node] = tree[node*2]+tree[node*2+1];
		
	}
	public static long query(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
		    return 0;
		}
		
		if(left <= start && right >= end) {
			return tree[node];
		}
		int mid = (start + end)/2;
		
		long leftResult = query(node * 2, start, mid, left, right);
		long rightResult = query(node * 2 + 1, mid+1, end, left, right);
		return leftResult + rightResult;
	}
}
