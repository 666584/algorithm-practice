import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7793_오나의여신님_조유림 {
    static int T, N, M;
    static char[][] map;
    static int sx, sy;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            Queue<int[]> devilQ = new ArrayDeque<>();
            Queue<int[]> suyeonQ = new ArrayDeque<>();
            boolean[][] visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);

                    if (map[i][j] == 'S') {
                        sx = i;
                        sy = j;
                        suyeonQ.offer(new int[]{i, j});
                        visited[i][j] = true;
                    } else if (map[i][j] == '*') {
                        devilQ.offer(new int[]{i, j});
                    }
                }
            }

            int answer = bfs(devilQ, suyeonQ, visited);

            if (answer == -1) {
                System.out.println("#" + t + " GAME OVER");
            } else {
                System.out.println("#" + t + " " + answer);
            }
        }
    }

    public static int bfs(Queue<int[]> devilQ, Queue<int[]> suyeonQ, boolean[][] visited) {
        int time = 0;

        while (!suyeonQ.isEmpty()) {
            time++;

            // 1. 악마 확산
            int devilSize = devilQ.size();
            for (int s = 0; s < devilSize; s++) {
                int[] curr = devilQ.poll();
                int cx = curr[0];
                int cy = curr[1];

                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                    // 빈칸(.) 또는 수연 위치(S)로만 확산 가능
                    // 여신(D), 돌(X)은 못 감
                    if (map[nx][ny] == '.' || map[nx][ny] == 'S') {
                        map[nx][ny] = '*';
                        devilQ.offer(new int[]{nx, ny});
                    }
                }
            }

            // 2. 수연 이동
            int suyeonSize = suyeonQ.size();
            for (int s = 0; s < suyeonSize; s++) {
                int[] curr = suyeonQ.poll();
                int cx = curr[0];
                int cy = curr[1];

                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (visited[nx][ny]) continue;

                    // 여신에게 도착
                    if (map[nx][ny] == 'D') {
                        return time;
                    }

                    // 빈칸으로만 이동 가능
                    if (map[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        suyeonQ.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return -1;
    }
}
