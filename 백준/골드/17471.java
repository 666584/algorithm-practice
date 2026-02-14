import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 구역: 1-N번
* 구역 2개 나누기. (조나누기랑 비슷함.)
* 1. 순열 사용 부분 집합.
* 2. bfs를 활용한 연결성 검증.
* 3. 한쪽의 합을 구하면 나머지는 total-한쪽하면 된다.
* */
public class BJ_17471_게리맨더링 {
    static int N;
    static ArrayList<Integer>[] graph;
    static int[] weights;
    static int totalWeight;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        weights = new int[N+1];
        st = new StringTokenizer(br.readLine());
        totalWeight = 0;
        for(int i = 1; i < N+1; i++){
            int weight = Integer.parseInt(st.nextToken());
            weights[i] = weight;
            totalWeight += weight;
        }
        graph = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            for(int j = 0; j < C; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        min = Integer.MAX_VALUE;
        permute(1, 1);
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }
    public static void permute(int i, int number){
        if(i == N+1){
            if(number == 0 || number == (1<<(N+1))-1) return;
            int totalA = 0;
            for(int j = 1; j < N+1; j++) {
                if ((number & (1 << j)) != 0) {
                    //System.out.print(j);
                    totalA += weights[j];
                }
            }
            //System.out.println();

            int diff = Math.abs(totalA - (totalWeight - totalA));

            if(diff > min) return;
            else {
                boolean cA = bfs(number);
                boolean cB = bfs(number ^ ((1<<N+1)-1));
                //System.out.println(diff+"diff" + cA + cB);
                if(cA && cB) {
                    //System.out.println("here!");
                    min = diff;
                }
            }
            return;
        }

        permute(i+1,  number | (1<<i)); //선택
        permute(i+1,  number); //미선택

    }

    /*
    * 시작점은 그룹에 속한 아무 노드 하나
    * 그 그룹에 속한 노드만 이동 가능.
    * 방문 체크는 bfs 내부 전용으로 한다.
    * 방문한 노드 수와 그룹의 노드 수가 같다면 연결 된 것이다.
    * */
    static boolean bfs(int mask){
        int start = -1;
        int cnt = 0;
        boolean isConnected = false;
        for(int j = 1; j < N+1; j++) {
            if ((mask & (1 << j)) != 0) {
                if(start == -1) start = j;
                else cnt++;
            }
        }
        cnt++;
        if(start == -1) return false;
        boolean[] isVisited = new boolean[N+1];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int curr = queue.poll();
            isVisited[curr] = true;
            for(int next: graph[curr]){
                if(((mask & (1 << next)) != 0) && !isVisited[next]) queue.add(next);
            }
        }
        int connected = 0;
        for(int i = 1; i < N+1; i++){
            if(isVisited[i] && ((mask & (1 << i)) != 0)) connected++;
        }
        if(connected == cnt) isConnected = true;
        return isConnected;
    }
}
