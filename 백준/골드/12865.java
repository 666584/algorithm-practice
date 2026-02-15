import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
*  i번째 물건까지 고려했을 때 무게가 W일때 얻을 수 이는 최대 가치
*  dp[i][w]
*
*  안 넣을 때:
*  dp[i-1][w]
*  넣을 때:
*  dp[i-1][w-weight[i]] + value[i]
*  i-1 : 이전 행을 본다. (예: 3번을 넣을 차례 라면 2번을 마지막 무게까지 고려한 dp를 본다.)
*
* 1차원 배열:
* dp[w] 로 계속해서 이전행을 업데이트한다. 무게를 뒤에서부터 돌려서 같은 물건 두번 사용을 금지한다.
* */
public class BJ_12865_평범한배낭 {
    static int N, K;//물품 수, 최대 무게
    static int[][] items;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        items = new int[N+1][2];
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[i][0] = w;
            items[i][1] = v;
        }
        dp = new int[K+1];
        setDp();
        System.out.println(dp[K]);
    }

    public static void setDp(){
        for(int i = 1; i < N+1; i++){
            for(int j = K; j >= items[i][0]; j--){
                dp[j] = Math.max(dp[j], dp[j-items[i][0]] + items[i][1]);
            }
        }
    }
}
