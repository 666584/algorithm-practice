import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_4485_녹색옷입은애가젤다지 {
    static int N;
    static int[][] matrix;
    static int start;
    static int end;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] dist;
    static int min;

    public static class Node implements Comparable<Node>{
        int x;
        int y;
        int cost;

        Node(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = 1;
        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;
            matrix = new int[N][N];
            start = 0;
            end = N-1;

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dist = new int[N][N];
            for(int i = 0; i < N; i++){
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            min = Integer.MAX_VALUE;
            int result = dijkstra();
            System.out.println("Problem "+tc+": "+result);
            tc++;
        }
    }

    public static int dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0][0] = 0;
        pq.add(new Node(0, 0, matrix[0][0]));
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            int cost =  curr.cost;
            int x = curr.x;
            int y = curr.y;
            if(x == end && y == end ){
                return cost;
            }
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                int nextCost = cost + matrix[nx][ny];
                if(nextCost > min) continue;
                if(dist[nx][ny] > nextCost){
                    dist[nx][ny] = nextCost;
                    pq.offer(new Node(nx, ny, nextCost));
                }
            }
        }
        return -1;
    }
}
