import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1249_보급로 {
    static int N;
    static int[][] map;
    static int[] end;
    static int[][] costs;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0,0,-1,1};
    static class Node implements Comparable<Node>{
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost){
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }
            end = new int[2];
            end[0] = N - 1;
            end[1] = N - 1;
            costs = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    costs[i][j] = Integer.MAX_VALUE;
                }
            }
            visited = new boolean[N][N];
            int result = dijkstra();
            System.out.println("#"+t+ " "+result);
        }
    }

    public static int dijkstra(){
        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(0,0,map[0][0]));
        costs[0][0] = 0;
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            int x = curr.x;
            int y = curr.y;
            if(visited[x][y]) continue;
            visited[x][y] = true;
            if(x == N-1 && y == N-1){
                return costs[x][y];
            }
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if(costs[nx][ny] > costs[x][y]+map[nx][ny]){
                    costs[nx][ny] = costs[x][y]+map[nx][ny];
                    queue.offer(new Node(nx,ny, costs[nx][ny]));
                }
            }
        }
        return -1;
    }
}
