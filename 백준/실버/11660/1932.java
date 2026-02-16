import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int totalN = 0;
        for(int i = 1; i < N+1; i++) {
            totalN += i;
        }
        dp = new int[totalN+1];
        int currN = 1;
        int max = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < i+1; j++){
                int curr = Integer.parseInt(st.nextToken());
                if(currN == 1) {
                    dp[currN] = curr;
                    if(i == N-1){
                        max = Math.max(max, dp[currN]);
                    }
                    currN++;
                    continue;
                }
                if(j != 0 && j != i){
                    // 두개의 max를 구하기.
                    dp[currN] = Math.max(dp[currN-i], dp[currN-i-1]) + curr;
                }else if(j == 0) dp[currN] = curr + dp[currN-i];
                else dp[currN] = curr + dp[currN-i-1];
                if(i == N-1){
                    max = Math.max(max, dp[currN]);
                }
                currN++;
            }
        }

        System.out.println(max);
    }
}
