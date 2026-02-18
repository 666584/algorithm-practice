import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1516_게임개발 {
    //시작: 1시 51분
    // 끝: 2시
    // 모든 건물을 짓는 것이 가능한 입력만 주어진다. 사이클 있는지 확인 안해도 될 듯.
    static int N;
    static int[] indegrees, dp, costs;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        indegrees = new int[N+1];
        dp = new int[N+1];
        costs = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            costs[i] = cost;
            dp[i] = cost;
            int cnt = 0;
            while(true){
                int before = Integer.parseInt(st.nextToken());
                if(before == -1) break;
                graph[before].add(i);
                cnt++;
            }
            indegrees[i] = cnt;
        }
        
        topology();
        for(int i = 1; i <= N; i++){
            System.out.println(dp[i]);
        }
    }

    public static void topology(){
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i <= N; i++){
            if(indegrees[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int curr = queue.poll();
            for(int next: graph[curr]){
                indegrees[next]--;
                dp[next] = Math.max(dp[next], dp[curr]+costs[next]);
                if(indegrees[next] == 0){
                    queue.add(next);
                }
            }
        }
    }
}
