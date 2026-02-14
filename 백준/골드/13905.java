import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 1. 내림차순 정렬을 한다.
* 2. MST
* 2-1. 간선 정보 저장.
* 3. DFS로 경로 탐색.
* 3-1. 최소 간선 가중치 출력.
* */
public class BJ_13905_세부 {
    static int N, M;
    static int start, end;
    static int[][] info; //간선 정보
    static int[] parent;
    static ArrayList<Node>[] graph;
    public static class Node{
        int x;
        int cost;
        Node(int x, int cost){
            this.x = x;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        info = new int[M][3];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
            info[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(info, (x,y) -> Integer.compare(x[2], y[2]));
        parent = new int[N+1];
        for(int i = 1; i < N+1; i++){
            parent[i] = i;
        }

        graph = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }
        mst();
        int result = dfs();
        System.out.println(result);
    }

    public static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa != pb){
            parent[pb] = pa;
        }
    }

    public static void mst(){
        for(int i = M-1; i >= 0; i--){
            int a = info[i][0];
            int b = info[i][1];
            int cost = info[i][2];
            if(find(a) != find(b)) {
                union(a,b);
                graph[a].add(new Node(b, cost));
                graph[b].add(new Node(a, cost));
            }
        }
    }

    public static int dfs(){
        Queue<Node> queue = new ArrayDeque<>();
        Node node = new Node(start, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N+1];
        queue.offer(node);
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            int x = curr.x;
            int cost = curr.cost;
            if (x == end) return cost;
            visited[x] = true;
            for(Node next: graph[x]){
                if(!visited[next.x]){
                    queue.offer(new Node(next.x, Math.min(cost,next.cost)));
                }
            }
        }
        return 0;
    }
}
