import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int T, H, W, N;
    private static char[][] map;
    private static String commands;
    private static int x, y;
    private static int direction = -1;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static final char[] directions = {'^', 'v', '<', '>'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            direction = -1;
            for(int i = 0; i < H; i++){
                map[i] = br.readLine().toCharArray();
            }
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            commands = br.readLine();
            findCar();

            for(int c = 0; c < N; c++){
                processCommand(commands.charAt(c));
            }
            System.out.print("#"+t+" ");
            for(int z = 0; z < H; z++){
                System.out.println(map[z]);
            }
        }
    }

    public static void findCar(){
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                char curr = map[i][j];
                if(curr == '^') direction = 0;
                if(curr == 'v') direction = 1;
                if(curr == '<') direction = 2;
                if(curr == '>') direction = 3;
                if(direction != -1){
                    x = i;
                    y = j;
                    return;
                }
            }
        }
    }

    public static void processCommand(char command){
        if(command == 'S'){
            shoot();
            return;
        }
        if(command == 'U') direction = 0;
        if(command == 'D') direction = 1;
        if(command == 'L') direction = 2;
        if(command == 'R') direction = 3;
        map[x][y] = directions[direction];
        move();
    }

    public static void shoot(){
        int nx = x + dx[direction];
        int ny = y + dy[direction];
        while(nx < H && nx >= 0 && ny < W && ny >= 0){
            if(map[nx][ny] == '*'){
                map[nx][ny] = '.';
                return;
            }
            if(map[nx][ny] == '#') return;
            nx += dx[direction];
            ny += dy[direction];
        }
    }
    public static void move(){
        int nx = x + dx[direction];
        int ny = y + dy[direction];
        if (nx < H && nx >= 0 && ny < W && ny >= 0){
            if(map[nx][ny] == '.') {
                map[x][y] = '.';
                x = nx;
                y = ny;
                map[x][y] = directions[direction];
            }
        }
    }
}
