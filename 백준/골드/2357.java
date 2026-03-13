import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2357_최솟값과최댓값 {
	// 세그먼트 트리 
	static long[] treeMax, treeMin;
	static int N, M;
	static long[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new long[N+1];
		treeMax = new long[4*N];
		treeMin = new long[4*N];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		Arrays.fill(treeMin, Integer.MAX_VALUE);
		build(1, 1, N);
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long min = searchMin(1, 1,  N, a, b);
			long max = searchMax(1, 1,  N, a, b);
			System.out.println(min+ " "+max);
		}
	}
	
	static void build(int node, int start, int end) {
		if(start == end) {
			treeMin[node] = arr[start];
			treeMax[node] = arr[start];
			return;
		}
		
		int mid = (start+end)/2;
		build(node*2, start, mid);
		build(node*2+1, mid+1, end);
		
		treeMin[node] = Math.min(treeMin[node*2], treeMin[node*2+1]);
		treeMax[node] = Math.max(treeMax[node*2], treeMax[node*2+1]);
	}
	
	static long searchMax(int node, int start, int end, int left, int right) {
		if(right < start || left > end) {
			return 0;
		}
		
		// 완전 포함
		if(left <= start && right >= end) {
			return treeMax[node];
		}
		
		// 부분 포함
		int mid = (start+end)/2;
		long leftResult = searchMax(node*2, start,mid,left,right);
		long rightResult = searchMax(node*2+1,mid+1, end, left, right);
		return Math.max(leftResult, rightResult);
	}
	
	static long searchMin(int node, int start, int end, int left, int right) {
		if(right < start || left > end) {
			return Integer.MAX_VALUE;
		}
		
		// 완전 포함
		if(left <= start && right >= end) {
			return treeMin[node];
		}
		
		// 부분 포함
		int mid = (start+end)/2;
		long leftResult = searchMin(node*2, start,mid,left,right);
		long rightResult = searchMin(node*2+1,mid+1, end, left, right);
		return Math.min(leftResult, rightResult);
	}
}
