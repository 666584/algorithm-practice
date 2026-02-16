import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15650_N과M2 {
    static int N, M;
    static int[] number;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        number = new int[M];
        combination(0, 1);
    }
    // 조합(중복이 허용되지 않는다.)
    public static void combination(int cnt, int start){
        if(cnt == M) {
            for(int i = 0; i < M; i++){
                System.out.print(number[i]+" ");
            }
            System.out.println();
            return;
        }
        for(int i = start; i <= N; i++){
            number[cnt] = i;
            combination(cnt+1, i+1);
        }
    }
}
