import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 2행 N열
* 변을 공유한다.
* 사방의 스티커를 쓰지 못한다.
* 변을 공유하면 동시에 떼지 못한다.
* dp[행][위,아래, 안떼기] = 최댓값
* */
public class BJ_9465_스티커 {
    static int T;
    static int N;
    static int[][] stickers;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            stickers = new int[2][N];
            dp = new int[N][3];
            for(int i = 0; i < 2; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][0] = stickers[0][0];
            dp[0][1] = stickers[1][0];
            dp[0][2] = 0;
            setDp();
            int result = Math.max(dp[N-1][0], dp[N-1][1]);
            result = Math.max(dp[N-1][2], result);
            System.out.println(result);
        }
    }

    public static void setDp(){
        for(int i = 1; i < N; i++){
            dp[i][0] = stickers[0][i] + Math.max(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = stickers[1][i] + Math.max(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][0]);
        }
    }
}
