import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* dp는 금액 x를 만드는 방법의 수
* */
public class BJ_2293_동전1 {
    static int N, K;
    static int[] dp, coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[K+1];
        dp[0] = 1;
        setDp();
        System.out.println(dp[K]);
    }

    public static void setDp(){
        for(int i = 0; i < N; i++){
            for(int j = coins[i]; j < K+1; j++){
                dp[j] += dp[j-coins[i]];
            }
        }
    }
}
