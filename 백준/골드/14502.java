import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 10시33분
* 바이러스는 상하좌우로 퍼진다.
* 벽, 빈칸으로 되어 있다.
* 정사각형
* 꼭 3개를 새로 새워야 한다.
* 0 빈칸, 1벽, 2바이러스
* 벽을 먼저 세우고 그다음 동시 바이러스하고 0의 개수를 구한다.
* */
public class BJ_14502_연구소 {
    static int N, M;
    static int[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int max, cnt;
    static int[][] viruses;
    static int[][] empties;
    static int emptyCnt;
    static int[] pick = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        viruses = new int[11][2];
        cnt = 0;
        max = 0;
        empties = new int[N*M][2];
        emptyCnt = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    viruses[cnt][0] = i;
                    viruses[cnt][1] = j;
                    cnt++;
                }
                if(map[i][j] == 0) {
                    empties[emptyCnt][0] = i;
                    empties[emptyCnt][1] = j;
                    emptyCnt++;
                }
            }
        }
        combination(0, 0);
        System.out.println(max);
    }

    public static void combination(int cnt, int startIndex){
        if(cnt == 3){
            int x1 = empties[pick[0]][0], y1 = empties[pick[0]][1];
            int x2 = empties[pick[1]][0], y2 = empties[pick[1]][1];
            int x3 = empties[pick[2]][0], y3 = empties[pick[2]][1];

            // 벽 세우기
            map[x1][y1] = 1;
            map[x2][y2] = 1;
            map[x3][y3] = 1;

            // 바이러스 BFS 돌리고 안전영역 갱신
            findMin();   // 너가 만들거나 findMin에서 BFS 부분만 떼서 만들면 됨

            // 원복
            map[x1][y1] = 0;
            map[x2][y2] = 0;
            map[x3][y3] = 0;
            return;
        }
        for(int i = startIndex; i < emptyCnt; i++){
            pick[cnt] = i;
            combination(cnt + 1, i+1);
        }
    }

    public static void findMin(){

        Queue<int[]> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[N][M];
        int count = 0;
        for(int i = 0; i < cnt; i++){
            queue.add(new int[]{viruses[i][0], viruses[i][1]});
            visited[viruses[i][0]][viruses[i][1]] = true;
            //System.out.println(viruses[i][0]+ " "+ viruses[i][1]);
        }

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(map[nx][ny] == 1 || visited[nx][ny]) continue;
                if(map[nx][ny] != 0) continue;
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
                //System.out.println(nx+" "+ny);
                count++;
            }
        }
        int safe = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]) safe++;
            }
        }
        max = Math.max(max,safe);
    }
}
