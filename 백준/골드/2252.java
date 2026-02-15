import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2252_줄세우기 {
    static int N, M;
    static int[] numParents;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numParents = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++){
             graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            numParents[b] += 1;
            graph[a].add(b);
        }
        topologySort();
    }

    public static void topologySort(){
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i < N+1; i++){
            if(numParents[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int curr = queue.poll();
            System.out.print(curr+" ");
            for(int next: graph[curr]){
                numParents[next] -= 1;
                if(numParents[next] == 0){
                    queue.add(next);
                }
            }
        }
    }
}
