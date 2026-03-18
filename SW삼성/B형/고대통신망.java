package 고대통신망;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

class UserSolution {
	int cap;
	int N; //최대 도시 수는 변하지 않는다. 5000
	int K;
	HashSet<Integer> activeSet;
	ArrayList<Edge>[] edges;//sCity로 검색한다. 
	boolean isDirty;
	long[] distance;
	int[] bestMax;
	int[] parentEdge;
	HashMap<Integer, Edge> edgeMap;


	
	public class Edge{
		int end;
		int dist;
		int mId;
		int start;
		Edge(int start, int end, int dist, int mId){
			this.start = start;
			this.end = end;
			this.dist = dist;
			this.mId = mId;
		}
	}
	
	public class Search implements Comparable<Search>{
		int start;
		long dist;
		int maxDist;
		Search(int start, long dist, int maxDist){
			this.start = start;
			this.dist = dist;
			this.maxDist = maxDist;
		}
		@Override
		public int compareTo(Search o) {
			return Long.compare(this.dist, o.dist);
		}
		
	}
	public void init(int N, int mCapital, int K, int mId[], int sCity[], int eCity[], int mDistance[]) {
		cap = mCapital;
		this.N = N;
		this.K = K;
		activeSet = new HashSet<>();
		edges = new ArrayList[N];
		edgeMap = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
		for(int i = 0; i < K; i++) {
			activeSet.add(mId[i]);
			Edge e = new Edge(sCity[i], eCity[i], mDistance[i], mId[i]);
            edges[sCity[i]].add(e);
            edgeMap.put(mId[i], e);
        }
		fullDijkstra();
		isDirty = false;
		return;
	}

	public void add(int mId, int sCity, int eCity, int mDistance) {
		Edge e = new Edge(sCity, eCity, mDistance, mId);
		edges[sCity].add(e);
		edgeMap.put(mId, e);
		activeSet.add(mId);
		if(distance[sCity] == Long.MAX_VALUE) return;
		
		long nd = distance[sCity] + mDistance;
	    int nm = Math.max(bestMax[sCity], mDistance);

	    if (nd < distance[eCity] || (nd == distance[eCity] && nm < bestMax[eCity])) {
	        distance[eCity] = nd;
	        bestMax[eCity] = nm;
	        parentEdge[eCity] = mId;
	        partialDijkstra(eCity);
	    }
		return;
	}

	public void remove(int mId) {
		activeSet.remove(mId);
		Edge e = edgeMap.get(mId);
        if (e == null) return;
		if (parentEdge[e.end] == mId) {
	        isDirty = true;
	    }
		return;
	}
	
	private void partialDijkstra(int start) {
		PriorityQueue<Search> pq = new PriorityQueue<>();
		pq.add(new Search(start, distance[start], bestMax[start]));
		while(!pq.isEmpty()) {
			Search curr = pq.poll();
			if(distance[curr.start] < curr.dist) continue;
			if (curr.dist == distance[curr.start] && curr.maxDist > bestMax[curr.start]) continue;
			for(Edge next: edges[curr.start]) {
				if(!activeSet.contains(next.mId)) continue;
				long newDist = curr.dist + next.dist;
                int newMax = Math.max(curr.maxDist, next.dist);
				if (newDist < distance[next.end] ||
		                   (newDist == distance[next.end] && newMax < bestMax[next.end])) {
					distance[next.end] = newDist;
					bestMax[next.end] = newMax;
					parentEdge[next.end] = next.mId;
					pq.add(new Search(next.end, newDist, newMax));
				}
			}
		}
	}
	
	private void fullDijkstra() {
		distance = new long[N];
		parentEdge = new int[N];
		Arrays.fill(parentEdge, -1);
		Arrays.fill(distance, Long.MAX_VALUE);
		PriorityQueue<Search> pq = new PriorityQueue<>();
		pq.add(new Search(cap, 0, 0));
		bestMax = new int[N];
		Arrays.fill(bestMax, Integer.MAX_VALUE);
		distance[cap] = 0;
		bestMax[cap] = 0;
		
		while(!pq.isEmpty()) {
			Search curr = pq.poll();
			if(distance[curr.start] < curr.dist) continue;
			if (curr.dist == distance[curr.start] && curr.maxDist > bestMax[curr.start]) continue;
			for(Edge next: edges[curr.start]) {
				if(!activeSet.contains(next.mId)) continue;
				long newDist = curr.dist + next.dist;
                int newMax = Math.max(curr.maxDist, next.dist);
				if (newDist < distance[next.end] ||
		                   (newDist == distance[next.end] && newMax < bestMax[next.end])) {
					distance[next.end] = newDist;
					bestMax[next.end] = newMax;
					parentEdge[next.end] = next.mId;
					pq.add(new Search(next.end, newDist, newMax));
				}
			}
		}
	}

	public int calculate(int mCity) {
		if(isDirty) {
			fullDijkstra();
			isDirty = false;
		}
		if(bestMax[mCity] == Integer.MAX_VALUE) return -1;
		return bestMax[mCity];
	}
}
