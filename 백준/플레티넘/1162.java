import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* start는 1, end = N.
* 도로 포장 개수는 1-20개
* 포장을 하면 간선의 가중치는 0이 된다.
* 최소 비용 출력.
* */
public class BJ_1162_도로포장 {
    static int N, M, K; //정점, 간선, 포장할 수 있는 도로 수
    static long[][] dist;
    static ArrayList<Node>[] graph;
    static class Node implements Comparable<Node>{
        int n;
        long cost;
        int usedK;

        Node(int n, long cost, int usedK){
            this.n = n;
            this.cost = cost;
            this.usedK = usedK;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];

        dist = new long[N+1][K+1];
        for(int i = 1; i < N+1; i++){
            Arrays.fill(dist[i], Long.MAX_VALUE);
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, cost, 0));
            graph[b].add(new Node(a, cost, 0));
        }
        Dijkstra();
    }

    public static void Dijkstra(){
        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1,0,0));
        dist[1][0] = 0;
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            int node = curr.n;
            long cost = curr.cost;
            int usedK = curr.usedK;
            //System.out.println(node+" "+cost+" "+usedK);
            if (cost != dist[node][usedK]) continue;
            if(node == N){
                System.out.println(cost);
                return;
            }
            for(Node next: graph[node]){
                int i = next.n;
                long nCost = next.cost;
                if (dist[i][usedK] > cost + nCost) {
                    dist[i][usedK] = cost + nCost;
                    //System.out.println(dist[i][usedK]+"dist");
                    queue.offer(new Node(i, dist[i][usedK], usedK));
                }
                if (usedK < K) {
                    //System.out.println(usedK+"k");
                    if (dist[i][usedK + 1] > cost) {
                        dist[i][usedK + 1] = cost;
                        queue.offer(new Node(i, dist[i][usedK + 1], usedK + 1));
                    }
                }

            }
        }
    }
}
