import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N = 100;
    static int[][] map;
    static int x, y;
    static int[] dx = {0, 0, -1};
    static int[] dy = {1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for(int tc = 1; tc <= 10; tc++) {
            int t = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            findEnd();
            boolean[][] visited = new boolean[N][N];
            int result = bfs(visited);
            System.out.println("#"+t+" "+result);
        }
    }

    public static void findEnd() {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (map[i][j] == 2) {
                    x = i;
                    y = j;
                    return;
                }
            }
        }
    }

    public static int bfs(boolean[][] visited) {
        Queue<Integer[]> queue = new LinkedList<Integer[]>();
        Integer[] start = {x, y};
        queue.offer(start);

        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Integer[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];
            boolean isMoved = false;
            for (int i = 0; i < 3; i++) {
                int tx = cx + dx[i];
                int ty = cy + dy[i];
                if(i == 2 && isMoved) break;
                else if(tx < N && ty < N && tx >= 0 && ty >= 0) {
                    if (!visited[tx][ty] && map[tx][ty] == 1) {
                        if (tx == 0) return ty;
                        visited[tx][ty] = true;
                        Integer[] next = {tx, ty};
                        queue.offer(next);
                        isMoved = true;
                    }
                }
            }
        }

        return -1;
    }
}
