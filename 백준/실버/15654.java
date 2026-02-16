import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 수열
// 수열은 중복이 가능하다.
// 예) 1 2 != 2 1
public class BJ_15654_N과M5 {
    static int N, M;
    static int[] number;
    static boolean[] isSelected;
    static int[] array;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        number = new int[M];
        isSelected = new boolean[N+1];
        array = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);
        permute(0);
    }

    public static void permute(int cnt){
        if(cnt == M){
            for(int i = 0; i < M; i++){
                System.out.print(number[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < N; i++){
            if(!isSelected[i]){
                isSelected[i] = true;
                number[cnt] = array[i];
                permute(cnt+1);
                isSelected[i] = false;
            }
        }
    }
}
