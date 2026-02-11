import java.util.Arrays;
import java.util.Scanner;
/*
 * 왼쪽, 오른쪽으로 산 N개의 높이를 측정한다.
 * 왼쪽부터 i =0
 * 같은 높이의 산은 없다.
 * i-j구간이 우뚝 선 산인지 확인.
 * i-j
 * i와 i+1 사이는 우뚝 선 산이 될 수 없다. 
 * 우뚝 선산이 될 수 있는 구간의 개수 구하기.
 * peak 이 있고 peak을 중심으로 왼쪽 오름차순, 오른쪽 내림차순 정렬되어 있는가? 
 * */
public class Solution {
    static int T, N; // N: 산 개수
    static int[] mountains; // 배열은 1부터 시작하여 N+1개로 초기화 한다.
    static int[] left, right;
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
            left = new int[N+1];
            right = new int[N+1];
            dpLeft();
            dpRight();
            dpFindHighest();
            //findHighest();
            System.out.println("#"+t+" "+total);
        }
        sc.close();
    }
     
    //dp
    //left: i에서 끝나는 연속 오름차순 길이.
    public static void dpLeft() {
        left[1] = 0;
        for(int i = 2; i <= N; i++) {
            if(mountains[i-1] < mountains[i]) {
                left[i] = left[i-1]+1;
            }else left[i] = 0;
        }
    }
     
    // right: i에서 연속 내림차순 길이 저장
    public static void dpRight() {
        right[N] = 0;
        for(int i = N-1; i > 1; i--) {
            if(mountains[i] > mountains[i+1]) {
                right[i] = right[i+1]+1;
            }else right[i] = 0;
        }
    }
     
    //dp findHighest
    public static void dpFindHighest() {
        for(int m = 2; m < N; m++) {
            if(mountains[m-1] < mountains[m] && mountains[m] > mountains[m+1]) {
                total+= (left[m]*right[m]);
            }
        }
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
