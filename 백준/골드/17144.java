
/*
인접한 네방향 확산
공기청정기가 있거나 칸이 없으면 확산되지 않음.
확산되는 양은 a/5 int
그 자리에는 확산되고 남은 양이 저장.
위쪽 공기청정기는 반시계 방향 순환, 아래쪽은 시계 방향
일반 바람: 미세먼지 방향 대로 1칸이동.
공기청정기는 미세먼지 없음. 모두 정화됨.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17144_미세먼지안녕 {
    static int R, C, T;//세로, 가로, 시간
    static int[] startWind1;//공기청정기 윗부분
    static int[] startWind2;
    static int freshWind1;
    static int freshWind2;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        boolean isSecond = false;
        for(int i = 0; i < R; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1 && !isSecond){
                    freshWind1 = i;
                    isSecond = true;
                }else if(map[i][j] == -1 && isSecond){
                    freshWind2 = i;
                }
            }
        }
        /*for(int i = 0; i < R; i ++){
            for(int j = 0; j < C; j ++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }*/
        operate();
        int total = 0;
        for(int i = 0; i < R; i ++){
            for(int j = 0; j < C; j ++){
                System.out.print(map[i][j]);
                if(map[i][j] == -1) continue;
                total += map[i][j];
            }
            System.out.println();
        }
        System.out.println(total);
    }

    static void operate(){
        for(int t = 0; t < T; t++){
            int[][] addMatrix = new int[R][C];
            for(int i = 0; i < R; i ++){
                for(int j = 0; j < C; j ++){
                    int dust = map[i][j];
                    if(dust == -1) continue;
                    int spread = dust / 5;
                    int totalSpread = dust;
                    for(int a = 0; a < 4; a++){
                        int nx = i + dx[a];
                        int ny = j + dy[a];
                        if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                        if(map[nx][ny] == -1) continue;
                        addMatrix[nx][ny] += spread;
                        totalSpread -= spread;
                    }
                    map[i][j] = totalSpread;
                }
            }
            //System.out.println("here");
            for(int i = 0; i < R; i ++){
                for(int j = 0; j < C; j ++){
                    int dust = map[i][j];
                    if(dust == -1) continue;
                    map[i][j] += addMatrix[i][j];
                }
            }
            for(int i = 0; i < R; i ++){
                for(int j = 0; j < C; j ++){
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            updateFreshWind();
        }
    }

    static void updateFreshWind(){

        int curr = map[freshWind1][1];
        map[freshWind1][1] = 0;
        for(int j = 2; j < C; j ++) {
            int temp = map[freshWind1][j];
            map[freshWind1][j] = curr;
            curr = temp;
        }
        for(int i = freshWind1-1; i>= 0; i--){
            int temp = map[i][C-1];
            map[i][C-1] = curr;
            curr = temp;
        }

        for(int j = C-2; j >= 0; j--){
            int temp = map[0][j];
            map[0][j] = curr;
            curr = temp;
        }


        for(int i = 1; i < freshWind1; i++){
            int temp = map[i][0];
            map[i][0] = curr;
            curr = temp;
        }

        curr = map[freshWind2][1];
        map[freshWind2][1] = 0;

        for(int j = 2; j < C; j ++) {
            int temp = map[freshWind2][j];
            map[freshWind2][j] = curr;
            curr = temp;
        }

        for(int i = freshWind2+1; i < R; i++){
            int temp = map[i][C-1];
            map[i][C-1] = curr;
            curr = temp;
        }

        for(int j = C-2; j >= 0; j--){
            int temp = map[R-1][j];
            map[R-1][j] = curr;
            curr = temp;
        }

        for(int i = R-2; i > freshWind2; i--){
            int temp = map[i][0];
            map[i][0] = curr;
            curr = temp;
        }
    }
}
