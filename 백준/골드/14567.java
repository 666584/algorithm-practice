import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_선수과목 {
    static int[] 진입차수;
    static int N, M; //정점수, 간선수
    static ArrayList<Integer>[] graph;
    static int[] semesters;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        진입차수 = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            진입차수[b] += 1;
            graph[a].add(b);
        }
        //System.out.println(Arrays.toString(진입차수));
        semesters = new int[N+1];
        topologySort();
        for(int i = 1; i < N+1; i++){
            System.out.print(semesters[i]+" ");
        }
    }

    public static void topologySort(){
        Queue<int[]> queue = new ArrayDeque<>();
        boolean isStarted = false;
        for(int i = 1; i < N+1; i++){
            if(진입차수[i] == 0){
                queue.add(new int[]{i,1});
                isStarted = true;
            }
        }
        if(!isStarted){
            Arrays.fill(semesters,1);
            return;
        }

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int subject = curr[0];
            int sem = curr[1];
            //System.out.println(subject+" "+sem);
            semesters[subject] = sem;
            for(int next: graph[subject]){
                진입차수[next] -=1;
                if(진입차수[next] == 0){
                    queue.add(new int[]{next, sem+1});
                }
            }
        }
    }
}
