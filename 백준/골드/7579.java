import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    최소 비용으로 M 이상의 메모리를 갖아야 한다.
    dp[M]
    뒤에서부터 시작해야 한다. (중복 불가)
 */
public class BJ_7579_앱 {
    static int N, M; // 앱 개수, 최소 필요 메모리
    static int[] dp;
    static int[] memories;
    static int[] costs;
    static int totalCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memories = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){
            memories[i] = Integer.parseInt(st.nextToken());
        }
        costs = new int[N+1];
        totalCost = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){
            costs[i] = Integer.parseInt(st.nextToken());
            totalCost += costs[i];
        }
        dp = new int[totalCost+1];
        setDp();
        //System.out.println(Arrays.toString(dp));
        for(int i = 0; i < totalCost+1; i++){
            if(dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }

    public static void setDp(){
        for(int i = 1; i < N+1; i++){
            for(int j = totalCost; j >= costs[i]; j--){
                dp[j] = Math.max(dp[j], dp[j-costs[i]]+memories[i]);
            }
        }
    }
}
