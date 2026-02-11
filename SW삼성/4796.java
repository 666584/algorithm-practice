import java.util.Scanner;
 
public class Solution {
    static int T, N; // N: 산 개수
    static int[] mountains; // 배열은 1부터 시작하여 N+1개로 초기화 한다.
    static int[] dp;
    static int total;
     
    public static void main(String[] args) {
 
 
        Scanner sc = new Scanner(System.in);
 
        T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            mountains = new int[N + 1];
 
            for (int m = 1; m <= N; m++) {
                mountains[m] = sc.nextInt();
            }
            total = 0;
            findHighest();
            System.out.println("#"+t+" "+total);
        }
        sc.close();
    }
     
    // peak에서 감소 구간, 증가 구간  카운트 후 cont 곱하기.
    public static void findHighest() {
        for(int m = 2; m < N; m++) {
            if(mountains[m-1] < mountains[m] && mountains[m] > mountains[m+1]) {
                int left = 0;
                for(int j = m-1; j >= 1; j--) {
                    if(mountains[j] < mountains[j+1]) left++;
                    else break;
                }
                int right = 0;
                for(int k = m+1; k <= N; k++) {
                    if(mountains[k] < mountains[k-1]) right++;
                    else break;
                }
                 
                total+= (left*right);
            }
        }
         
    }
 
}
