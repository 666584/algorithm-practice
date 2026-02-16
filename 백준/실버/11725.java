import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_11725_트리의부모찾기 {
    public static int N;
    public static ArrayList<Integer>[] graph;
    public static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        parents = new int[N+1];

        bfs();
        for(int i = 2; i < N+1; i++){
            System.out.println(parents[i]);
        }
    }

    public static void bfs(){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, 1});
        boolean[] isVisited = new boolean[N+1];
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int node = curr[0];
            int parent = curr[1];
            if(isVisited[node]) continue;
            isVisited[node] = true;
            parents[node] = parent;
            for(int next: graph[node]){
                queue.offer(new int[]{next, node});
            }
        }
    }
}
