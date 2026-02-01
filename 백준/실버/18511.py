import java.util.Scanner;

public class Main {
    public static int findMax(int number,int cnt ,int[] list, int result, int maxNum){
        if (result * 10 > number) return result;

        for(int j = 0; j < cnt; j++) {
            int curr =  findMax(number, cnt, list, result * 10 + list[j], maxNum);
            if (curr > maxNum) maxNum = curr;
        }
        return maxNum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int cnt = sc.nextInt();
        int[] list = new int[cnt];
        for(int i = 0; i < cnt; i++){
            list[i] = sc.nextInt();
        }
        int result = findMax(number, cnt, list, 0, 0);
        System.out.println(result);
    }
}
