import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2294_동전2 {
    static int N, K;
    static int[] dp;
    static ArrayList<Integer> coins;
    static int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int coin = Integer.parseInt(st.nextToken());
            if(coin <= K){
                coins.add(coin);
            }
        }

        dp = new int[K+1];

        Arrays.fill(dp, INF);
        dp[0] = 0;
        setDp();
        if(dp[K] == INF) System.out.println(-1);
        else System.out.println(dp[K]);
    }

    public static void setDp(){
        for(int coin: coins){
            for(int j = coin; j < K+1; j++){
                if (dp[j - coin] == INF) continue;
                dp[j] = Math.min(dp[j], dp[j-coin]+1);
            }
        }
    }
}
