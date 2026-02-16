import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11660_구간합구하기5 {
    static int N, M;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N+1][N+1];
        dp = new int[N+1][N+1];
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        setDp();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            getSum(x1, y1, x2, y2);
        }
    }

    public static void setDp(){
        for(int i = 1; i < N+1; i++){
            dp[i][1] = matrix[i][1];
            for(int j = 2; j < N+1; j++){
                dp[i][j] = dp[i][j-1] + matrix[i][j];
            }
        }
    }

    public static void getSum(int x1, int y1, int x2, int y2){
        int sum = 0;
        for(int i = x1; i <= x2; i++){
            if(y1-1 >= 0) sum += (dp[i][y2]-dp[i][y1-1]);
            else sum += (dp[i][y2]);
        }
        System.out.println(sum);
    }
}
