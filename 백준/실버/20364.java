import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 반례 1. 자기 자신 여부 확인 필요!
public class Main {
    private static int[] commands;
    private static boolean[] isSelected;
    private static int N, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        commands = new int[Q];
        for(int c = 0; c < Q; c++){
            commands[c] = Integer.parseInt(br.readLine());
        }
        isSelected = new boolean[N+1];
        processCommand();
    }

    public static void processCommand(){
        for(int i = 0; i < Q; i++) {
            int curr = commands[i];
            int occupied = 0;
            while (curr > 0) {
                if (isSelected[curr]) {
                    occupied = curr;
                }
                curr = curr / 2;
            }
            if (occupied == 0) isSelected[commands[i]] = true;
            System.out.println(occupied);
        }
    }
}
