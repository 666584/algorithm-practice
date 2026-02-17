import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1247_최적경로 {
    static int[][] nodes;
    static int T, N;
    static int[][] distances;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            nodes = new int[N+2][2];
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            nodes[0][0] = sx;
            nodes[0][1] = sy;
            nodes[1][0] = ex;
            nodes[1][1] = ey;

            for(int i = 2; i < N+2; i++){
                nodes[i][0] = Integer.parseInt(st.nextToken());
                nodes[i][1] = Integer.parseInt(st.nextToken());
            }
            distances = new int[N+2][N+2];
            getDistance();
            min = Integer.MAX_VALUE;
            permute(0,0, 0, 0);
            System.out.println("#"+t+" "+min);
        }
    }

    public static void getDistance(){
        for(int i = 0; i < N+2; i++){
            for(int j = i+1; j < N+2; j++){
                int distance = Math.abs(nodes[i][0] - nodes[j][0]) + Math.abs(nodes[i][1] - nodes[j][1]);
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }
    }

    public static void permute(int cnt, int mask, int total, int before){
        if(cnt == N){
            total += distances[before][1];
            min = Math.min(total,min);
            return;
        }

        for(int i = 2; i < N+2; i++){
            if((mask & (1<<i)) != 0) continue;
            if(total > min) continue;
            permute(cnt+1, mask | (1<<i), total+distances[before][i], i);
        }
    }
}
