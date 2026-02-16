import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15666_N과M12 {
    static int N, M;
    static int[] number;
    static int[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        number = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        //System.out.println(Arrays.toString(number));
        Arrays.sort(number);
        //System.out.println(Arrays.toString(number));
        arr = new int[M];
        combination(0);
    }

    // 중복 조합
    public static void combination(int depth){
        if(depth == M) {
            for(int i = 0; i < M; i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
            return;
        }
        int prev = 0;
        for(int i = 0; i < N; i++){
            if(prev == number[i]) continue;
            if(depth > 0 && (arr[depth-1] > number[i])) continue;
            prev = number[i];
            arr[depth] = number[i];
            combination(depth+1);
        }
    }
}
