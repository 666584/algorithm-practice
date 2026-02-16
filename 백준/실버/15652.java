import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15652_N과M4 {
    static int N, M;
    static int[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        number = new int[M+1];
        combination(1);
    }

    //조합
    public static void combination(int start){
        if(start == M+1) {
            for(int i = 1; i <= M; i++){
                System.out.print(number[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i = 1; i <= N; i++){
            if(start > 0 && number[start-1] > i) continue;
            number[start] = i;
            combination(start+1);
            //System.out.println(start);
        }
    }
}
