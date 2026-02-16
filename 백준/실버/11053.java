import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * dp[i]: 현재 자릿수 까지의 가장 긴 수열 갯수
 * */
public class Main {
    static int[] array;
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        array = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N];
        setDp();
        System.out.println(getMax(N));
    }

    public static int getMax(int j){
        int max = dp[j-1];
        for(int i = j-1; i >= 0; i--){
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public static void setDp(){
        for(int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
    }

}
