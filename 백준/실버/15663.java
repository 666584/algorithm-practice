import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 수열
// 수열은 중복이 가능하다.
// 예) 1 2 != 2 1
public class BJ_15654_N과M5 {
    static int N, M;
    static int[] number;
    static boolean[] isSelected;
    static int[] array;
    static boolean[][] isVisited;
    static int num;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        number = new int[M];
        isSelected = new boolean[N+1];
        isVisited = new boolean[10001][M];
        array = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            array[i] = (Integer.parseInt(st.nextToken()));
        }
        // 1. 정렬
        Arrays.sort(array);
        num = 0;
        permute(0);
    }

    public static void permute(int cnt){
        if(cnt == M){
            for(int i = 0; i < M; i++){
                System.out.print(number[i] + " ");
                isVisited[number[i]][i] = true;
            }
            System.out.println();
            return;
        }

        int prev = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            if(array[i] == prev) continue;
            if(!isSelected[i]){
                isSelected[i] = true;
                number[cnt] = array[i];
                prev = array[i];
                permute(cnt+1);
                isSelected[i] = false;
            }
        }
    }
}
