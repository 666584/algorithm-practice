/*
    시작 위치가 정해져 있다.
    N*N의 정사각형 미로, M개의 흩어진 열쇠의 위치.
    모든 열쇠를 찾으면서 로볼이 움직이는 횟수의 합을 최소로하는 프로그램 ㅣ작성.
    상하좌우 사방 탐색.
    로봇 수를 마음대로 가질 수 이싸.
    로봇이 움직이는 횟수의 합은 분열된 로봇 각각이 움직인 횟수의 총합.
    열쇠가 있는 곳들과 로봇이 출발하는 위치에 로봇이 복제 할 수 있따.

    1. 우선 각 위치에서 다른 위치로 가는 가장 짧은 거리 즉, 가중치를 구해야 한다.
    1-1. 가중치를 구하기 위해선 bfs를 사용한다.
    2. 가중치를 구했으면 그냥 mst 말고 무조건 시작 지점(가장 짧은 거리) 부터 시작하는 mst를 하면 된다.
    끝!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
* 입력:
* N, M 미로 크기, 열쇠 개수(정점수-1)
* 1은 미로의 벽, 0은 지나다닐 수 있는 길.
* S는 로봇이 출발하는 위치, K는 열쇠의 위치
* S는 1개, K는 M개
* 모든 테두리는 벽이다.
* */
public class BJ_1944_복제로봇_조유림 {
    static int N, M; //미로 크기, 열쇠 개수
    static int[][] distances; //간선의 정보를 저장한다. (시작점의 인덱스는 간선개수이다.)
    static int[][] map; // 로봇이 출발하는 위치는 2로, K는 3으로 표시한다.
    static int startX, startY; // 시작점 좌표
    static int[][] keys; //열쇠 좌표
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0,};
    static int totalEdges; //distances의 개수, 총 간선 개수
    static int[] parent;
    static int totalCost; //로봇이 움직인 총 횟수.
    static int edgeIndex; // 간선의 인덱스 업데이트를 위함.

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
                    map[i][j] = 3;
                    cnt++;
                } else map[i][j] = line.charAt(j) - '0';
            }
        }

        int[] start = {startX, startY};


        totalEdges = 0;
        for (int i = M; i > 0; i--) {
            totalEdges += i;
        }
        distances = new int[totalEdges][3];
        edgeIndex = 0;
        for(int i = 0; i < M; i++){
            int cost = bfs(start, keys[i]);
            distances[edgeIndex][0] = M;
            distances[edgeIndex][1] = i;
            distances[edgeIndex][2] = cost;
            edgeIndex++;
        }
        findDistance();
        Arrays.sort(distances, (x, y) -> Integer.compare(x[2], y[2]));
        totalCost = 0;
        parent = new int[M+1];
        for(int i = 0; i < M+1; i++){
            parent[i] = i;
        }
        mst();
    }

    public static void findDistance() {
        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                int cost = bfs(keys[i], keys[j]);
                distances[edgeIndex][0] = i;
                distances[edgeIndex][1] = j;
                distances[edgeIndex][2] = cost;
                edgeIndex++;
            }
        }
    }

    public static int bfs(int[] start, int[] end) {
        Deque<int[]> queue = new ArrayDeque<>();
        int[] startPoint = {start[0], start[1], 0};
        queue.add(startPoint);
        boolean[][] visited = new boolean[N][N];

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];
            int cost = curr[2];
            if (cx == end[0] && cy == end[1]) {
                return cost;
            }
            if (visited[cx][cy]) continue;
            visited[curr[0]][curr[1]] = true;
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (map[nx][ny] == 0 || map[nx][ny] == 3) {
                        int[] next = {nx, ny, cost + 1};
                        queue.addLast(next);
                    }
                }
            }
        }
        return 0;
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
            int a = distances[i][0];
            int b = distances[i][1];
            int cost = distances[i][2];
            if(find(a) != find(b)){
                union(a, b);
                totalCost += cost;
            }
        }
    }
}
