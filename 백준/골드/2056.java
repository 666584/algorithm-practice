import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2056_작업 {
    static int N;
    static int[] indegrees;
    static ArrayList<Integer>[] graph;
    static int[] costs;
    static int done;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        indegrees = new int[N+1];
        graph = new ArrayList[N+1];
        costs = new int[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            costs[i] = Integer.parseInt(st.nextToken());
            int indegree = Integer.parseInt(st.nextToken());
            indegrees[i] = indegree;
            for(int j = 0; j < indegree; j++){
                int a = Integer.parseInt(st.nextToken());
                graph[a].add(i);
            }
        }

        done = 0;
        topology();
    }
    public static void topology(){
        Queue<Integer> queue = new ArrayDeque<>();
        int[] dp = new int[N+1];
        for(int i = 1; i <= N; i++){
            dp[i] = costs[i];
            if(indegrees[i] == 0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int num = queue.poll();
            for(int next: graph[num]){
                dp[next] = Math.max(dp[num] + costs[next], dp[next]);
                indegrees[next]--;
                if(indegrees[next] == 0){
                    queue.offer(next);
                }
            }
        }

        int ans = 0;
        for(int i=1;i<=N;i++) ans = Math.max(ans, dp[i]);
        System.out.println(ans);
    }
}
