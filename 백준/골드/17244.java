import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 비트 마스킹을 활용한 bfs
* 모든 물건을 가져 갔으며 도착점에 도착했을 때 bfs를 종료 한다.
* visite[x][y][mask] : x,y 좌표를 mask 상태로 도착한 적이 있는지.
*   중복 도착이 가능하기 때문.
* */
public class BJ_17244_아맞다우산 {
    static int[][] map; //벽: -1, S: -2, X:1~1+우산개수, E: -3, 비어있는 곳: 0
    static int N, M; //가로, 세로 길이.
    static int[] start, end; //시작점, 끝점
    static int totalU; //우산 개수
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int totalMask;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        totalU = 1;
        for(int i = 0; i < M; i++){
            String line = br.readLine();
            for(int j = 0; j < N; j++){
                char p = line.charAt(j);
                switch(p){
                    case '#':
                        map[i][j] = -1;
                        break;
                    case '.':
                        map[i][j] = 0;
                        break;
                    case 'X':
                        map[i][j] = totalU++;
                        break;
                    case 'S':
                        map[i][j] = -2;
                        start = new int[]{i, j};
                        break;
                    case 'E':
                        map[i][j] = -3;
                        end = new int[]{i, j};
                        break;
                }
            }
        }

        /*for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }*/
        totalU--;
        bfs();
    }

    public static void bfs(){
        Queue<int[]> queue = new ArrayDeque<>();
        int[] s = new int[] {start[0], start[1], 0, 0};
        queue.add(s);
        boolean[][][] visited = new boolean[M][N][(1<<totalU)]; //마스크의 개수는 2^우산개수.

        visited[start[0]][start[1]][0] = true;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int mask = curr[2];
            int len = curr[3];


            for(int i = 0; i < 4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(visited[nx][ny][mask]) continue;
                if(map[nx][ny] == -1) continue;
                if(map[nx][ny] > 0){
                    visited[nx][ny][mask] = true;
                    queue.add(new int[]{nx,ny,mask | (1<<(map[nx][ny]-1)), len+1});
                    continue;
                }
                if(map[nx][ny] == 0 || map[nx][ny] == -2){
                    visited[nx][ny][mask] = true;
                    queue.add(new int[]{nx,ny,mask, len+1});
                    continue;
                }
                if(map[nx][ny] == -3 && (mask == ((1<<totalU)-1))){
                    System.out.println(len+1);
                    return;
                }
            }
        }
    }
}
