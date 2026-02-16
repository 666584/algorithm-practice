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
        combination(0, 1);
    }

    //조합
    public static void combination(int depth, int startVal){
        if(depth == M){
            for(int i=0;i<M;i++) System.out.print(number[i] + " ");
            System.out.println();
            return;
        }
        for(int i=startVal;i<=N;i++){
            number[depth] = i;
            combination(depth+1, i);
        }
    }
}
