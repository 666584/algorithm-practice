import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16953_AB {
    static long A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        long result = bfs();
        System.out.println(result);
    }

    public static long bfs(){
        Queue<long[]> queue = new ArrayDeque<>();
        queue.offer(new long[]{A,1});
        while(!queue.isEmpty()){
            long[] c = queue.poll();
            long curr = c[0];
            if((curr*10)+1 < B){
                queue.offer(new long[] {(curr*10)+1, c[1]+1});
            }
            if(curr*2 < B){
                queue.offer(new long[] {curr*2, c[1]+1});
            }
            if((curr*10)+1 == B || curr*2 == B) {
                return c[1]+1;
            }
        }
        return -1;
    }
}
