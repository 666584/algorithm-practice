import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 0은 빈칸, 6은 벽, 1-5는 cctv 번호
* cctv는 벽을 통과할 수 없다.
cctv 끼리는 통과 가능.
사각지대의 최소 길 이를 구한다.
 */
public class BJ_15683_감시 {
    static int N, M;
    static int[][] map;
    static int totalC, totalNotViewed, NotViewed;
    static int[][] cctvs;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int min;
    static int[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        totalC = 0;
        cctvs = new int[8+1][3];
        totalNotViewed = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int curr = Integer.parseInt(st.nextToken());
                map[i][j] = curr;
                if(curr > 0 && curr < 6){
                    cctvs[totalC][0] = i;
                    cctvs[totalC][1] = j;
                    cctvs[totalC][2] = curr;
                    totalC++;
                }
                else if (curr == 0){
                    totalNotViewed++;
                }
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        //System.out.println(totalNotViewed);
        NotViewed = totalNotViewed;
        number = new int[totalC];
        min = Integer.MAX_VALUE;
        permute(0, 0);

        //getMin(new int[]{0, 1, 2, 3, 4, 5, 6});
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println(totalNotViewed);
        System.out.println(min);
    }

    public static void cctv5(int[] cctv, int num){
        int x = cctv[0];
        int y = cctv[1];
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = num;
                    totalNotViewed--;
                }
                nx += dx[i];
                ny += dy[i];
            }
        }
    }

    public static void cctv4(int[] cctv){
        cctv5(cctv, 12);
        int x = cctv[0];
        int y = cctv[1];
        // 위 원상 복구 필요
        int minViewedUP = totalNotViewed;
        int nx = x + dx[0];
        int ny = y + dy[0];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] == 12) {
                minViewedUP++;
            }
            nx += dx[0];
            ny += dy[0];
        }
        // 아래 원상 복구 필요
        int minViewedDOWN = totalNotViewed;
        nx = x + dx[1];
        ny = y + dy[1];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] == 0 || map[nx][ny] == 12) {
                minViewedDOWN++;
            }
            nx += dx[1];
            ny += dy[1];
        }

        // 좌
        int minViewedLeft = totalNotViewed;
        nx = x + dx[2];
        ny = y + dy[2];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] == 12) {
                minViewedLeft++;
            }
            nx += dx[2];
            ny += dy[2];
        }
        // 우
        int minViewedRight = totalNotViewed;
        nx = x + dx[3];
        ny = y + dy[3];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] == 12) {
                minViewedRight++;
            }
            nx += dx[3];
            ny += dy[3];
        }

        int[] array = {minViewedUP, minViewedDOWN, minViewedLeft, minViewedRight};
        Arrays.sort(array);
        int j = -1;
        if(array[0] == minViewedUP) j = 0;
        if(array[0] == minViewedDOWN) j = 1;
        if(array[0] == minViewedLeft) j = 2;
        if(array[0] == minViewedRight) j = 3;

        nx = x + dx[j];
        ny = y + dy[j];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] == 12) {
                map[nx][ny] = 0;
                totalNotViewed++;
            }
            nx += dx[j];
            ny += dy[j];
        }
        for(int i = 0; i < 4; i++){
            nx = x + dx[i];
            ny = y + dy[i];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] <= 0) {
                    map[nx][ny] = 12;
                }
                nx += dx[i];
                ny += dy[i];
            }
        }
    }
    public static void cctv3(int[] cctv){
        int x = cctv[0];
        int y = cctv[1];
        int minCountUR = totalNotViewed;
        int minCountUL = totalNotViewed;
        int minCountDR = totalNotViewed;
        int minCountDL = totalNotViewed;
        int nx = x + dx[0];
        int ny = y + dy[0];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] == 0) {
                map[nx][ny] = -1;
                minCountUR--;
                minCountUL--;
            }
            nx += dx[0];
            ny += dy[0];
        }
        nx = x + dx[2];
        ny = y + dy[2];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] == 0) {
                map[nx][ny] = -2;
                minCountUR--;
            }
            nx += dx[2];
            ny += dy[2];
        }
        nx = x + dx[3];
        ny = y + dy[3];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] == 0 || map[nx][ny] == -2) {
                map[nx][ny] = -3;
                minCountUL--;
            }
            nx += dx[3];
            ny += dy[3];
        }
        nx = x + dx[1];
        ny = y + dy[1];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] <= 0) {
                map[nx][ny] = -4;
                minCountDR--;
                minCountDL--;
            }
            nx += dx[1];
            ny += dy[1];
        }
        nx = x + dx[2];
        ny = y + dy[2];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] <= 0 && map[nx][ny] != -4) {
                map[nx][ny] = -5;
                minCountDR--;
            }
            nx += dx[2];
            ny += dy[2];
        }
        nx = x + dx[3];
        ny = y + dy[3];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] <= 0 && map[nx][ny] != -4) {
                map[nx][ny] = -6;
                minCountDL--;
            }
            nx += dx[3];
            ny += dy[3];
        }

        for(int i = 0; i < 4; i++){
            nx = x + dx[i];
            ny = y + dy[i];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] <= 0) {
                    map[nx][ny] = 0;
                }
                nx += dx[i];
                ny += dy[i];
            }
        }

        int[] compareArray = {minCountUR, minCountUL, minCountDR, minCountDL};
        Arrays.sort(compareArray);
        if(compareArray[0] == minCountUR){
            nx = x + dx[0];
            ny = y + dy[0];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = 11;
                    totalNotViewed--;
                }
                nx += dx[0];
                ny += dy[0];
            }
            nx = x + dx[2];
            ny = y + dy[2];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = 11;
                    totalNotViewed--;
                }
                nx += dx[2];
                ny += dy[2];
            }
        }
        else if(compareArray[0] == minCountUL){
            nx = x + dx[0];
            ny = y + dy[0];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = 11;
                    totalNotViewed--;
                }
                nx += dx[0];
                ny += dy[0];
            }
            nx = x + dx[3];
            ny = y + dy[3];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = 11;
                    totalNotViewed--;
                }
                nx += dx[3];
                ny += dy[3];
            }
        }
        else if(compareArray[0] == minCountDR){
            nx = x + dx[1];
            ny = y + dy[1];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = 11;
                    totalNotViewed--;
                }
                nx += dx[1];
                ny += dy[1];
            }
            nx = x + dx[2];
            ny = y + dy[2];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = 11;
                    totalNotViewed--;
                }
                nx += dx[2];
                ny += dy[2];
            }
        }
        else if(compareArray[0] == minCountDL){
            nx = x + dx[1];
            ny = y + dy[1];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = 11;
                    totalNotViewed--;
                }
                nx += dx[1];
                ny += dy[1];
            }
            nx = x + dx[3];
            ny = y + dy[3];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = 11;
                    totalNotViewed--;
                }
                nx += dx[3];
                ny += dy[3];
            }
        }
    }

    public static void cctv2(int[] cctv){
        int x = cctv[0];
        int y = cctv[1];
        int minCountV = totalNotViewed;
        for(int i = 0; i < 2; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = -1;
                    minCountV--;
                }
                nx += dx[i];
                ny += dy[i];
            }
        }
        int minCountH = totalNotViewed;
        for(int i = 2; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] <= 0) {
                    map[nx][ny] = -2;
                    minCountH--;
                }
                nx += dx[i];
                ny += dy[i];
            }
        }
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == -1 || map[nx][ny] == -2) {
                    map[nx][ny] = 0;
                }
                nx += dx[i];
                ny += dy[i];
            }
        }
        System.out.println(minCountH + " "+minCountV);
        if(minCountH < minCountV){
            for(int i = 2; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                    if(map[nx][ny] == 0) {
                        map[nx][ny] = 10;
                        totalNotViewed--;
                    }
                    nx += dx[i];
                    ny += dy[i];
                }
            }
        }else{
            for(int i = 0; i < 2; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                    if(map[nx][ny] == 0) {
                        map[nx][ny] = 10;
                        totalNotViewed--;
                    }
                    nx += dx[i];
                    ny += dy[i];
                }
            }
        }
    }

    public static void cctv1(int[] cctv){
        int x = cctv[0];
        int y = cctv[1];
        int minCount = totalNotViewed;
        int minIdx = -1;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            int minNotViewed = totalNotViewed;
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == 0) {
                    map[nx][ny] = -1;
                    minNotViewed--;
                }
                nx += dx[i];
                ny += dy[i];
            }
            if(minNotViewed <= minCount){
                minIdx = i;
                minCount = minNotViewed;
            }
            nx -= dx[i];
            ny -= dy[i];
            while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
                if(map[nx][ny] == -1) {
                    map[nx][ny] = 0;
                }
                nx -= dx[i];
                ny -= dy[i];
            }
        }

        int nx = x + dx[minIdx];
        int ny = y + dy[minIdx];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && (map[nx][ny] != 6)){
            if(map[nx][ny] == 0) {
                map[nx][ny] = 9;
                totalNotViewed--;
            }
            nx += dx[minIdx];
            ny += dy[minIdx];
        }
    }

    public static void getMin(int[] array){
        for(int num: array){
            if(cctvs[num][2] == 5) cctv5(cctvs[num], 13);
            if(cctvs[num][2] == 4) cctv4(cctvs[num]);
            if(cctvs[num][2] == 3) cctv3(cctvs[num]);
            if(cctvs[num][2] == 2) cctv2(cctvs[num]);
            if(cctvs[num][2] == 1) cctv1(cctvs[num]);
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] >= 9){
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void permute(int cnt, int mask){
        if(cnt == totalC){
            System.out.println(Arrays.toString(number));
            getMin(number);
            System.out.println(totalNotViewed);
            min = Math.min(min, totalNotViewed);
            totalNotViewed = NotViewed;
            return;
        }
        for(int i = 0; i < totalC; i++){
            if((mask & (1<<i)) != 0) continue;
            number[cnt] = i;
            permute(cnt+1, mask | (1<<i));
        }
    }
}
