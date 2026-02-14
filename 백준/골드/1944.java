import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] distances;
    static int[][] map;
    static int startX, startY;
    static int[][] keys;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0,};
    static int totalEdges;
    static int[] parent;
    static int totalCost;
    static int[][] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        keys = new int[M][2];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                if (line.charAt(j) == 'S') {
                    startX = i;
                    startY = j;
                    map[i][j] = 2;
                } else if (line.charAt(j) == 'K') {
                    keys[cnt][0] = i;
                    keys[cnt][1] = j;
                    map[i][j] = 3+cnt;
                    cnt++;
                } else map[i][j] = line.charAt(j) - '0';
            }
        }

        int[] start = {startX, startY};

        distances = new int[M+1][M+1];
        for (int i=0;i<M+1;i++) Arrays.fill(distances[i], -1);
        bfs(start, M);
        findDistance();
        totalCost = 0;
        parent = new int[M+1];
        totalEdges = 0;
        for(int i = 0; i < M+1; i++){
            parent[i] = i;
            totalEdges += i;
        }

        edges = new int[totalEdges][3];
        int a = 0;
        for(int i = 0; i < M+1; i++){
            for(int j = 0; j < M+1; j++){
                if(distances[i][j] != -1 && i < j){
                    edges[a][0] = i;
                    edges[a][1] = j;
                    edges[a][2] = distances[i][j];
                    a++;
                }
            }
        }
        if(a != totalEdges){
            System.out.println(-1);
        }else{
            Arrays.sort(edges, (x,y)->Integer.compare(x[2], y[2]));
            mst();
            System.out.println(totalCost);
        }
    }

    public static void findDistance() {
        for (int i = 0; i < M; i++) {
            bfs(keys[i], i);
        }
    }

    public static void bfs(int[] start, int startIndex) {
        Deque<int[]> queue = new ArrayDeque<>();
        int[] startPoint = {start[0], start[1], 0};
        queue.add(startPoint);
        boolean[][] visited = new boolean[N][N];

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];
            int cost = curr[2];
            if(map[cx][cy] >= 3){
                distances[startIndex][map[cx][cy]-3] = cost;
            }
            if (map[cx][cy] == 2){
                distances[startIndex][M] = cost;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny]) {
                        if (map[nx][ny] != 1) {
                            int[] next = {nx, ny, cost + 1};
                            visited[nx][ny] = true;
                            queue.addLast(next);
                        }
                    }
                }
            }
        }
    }
    public static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int a, int b){
        int pA = find(a);
        int pB = find(b);

        if(pA != pB){
            parent[pB] = pA;
        }
    }
    public static void mst(){
        for(int i = 0; i < totalEdges; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            int cost = edges[i][2];
            if(find(a) != find(b)){
                union(a, b);
                totalCost += cost;
            }
        }
    }
}
