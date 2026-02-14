import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
* A,B 간선에 연결된 정점
* C: 가중치
* X와 Y 가 팀장 일때 비용 최소?
* X와 Y는 서로 떨어진 간선이다.
* 이중에서 서로 연결된 가장 긴 간선을 자른다.
* 간선 간에 어떻게 연결되어 있는 지를 알아야 한다.
* 만약 x간선과는 연결 되어있지만 y 간선과는 연결이 되어 있지 않다면
* 그 간선은 잘라서는 안된다.
* a,b의 간선 수를 모두 찾아서 간선수가 2이상인경우 즉, a,b 제외 간선수가 1이상.
* 인경우에는 간선을 잘라도 된다.
* 만약 a나 b가 x, y 중하나라면 나머지 정점의 간선수가 반드시 2이상일때 자를 수 있다.
* */
public class BJ_23034_조별과제멈춰 {
    static int N, M, totalCost; //정점수, 간선 수
    static int[][] distances; // 간선 정보
    static int[] parents;
    static int[] neighbors;
    static int[][] costMst;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        distances = new int[M][3];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            distances[i][0] = a;
            distances[i][1] = b;
            distances[i][2] = cost;
        }

        Arrays.sort(distances, (x, y)->Integer.compare(x[2], y[2]));
        parents = new int[N+1];
        for(int i = 1; i < N+1; i++){
            parents[i] = i;
        }

        costMst = new int[M][3];
        totalCost = 0;
        neighbors = new int[N+1];

        mst();
        //System.out.println("neighbors: "+Arrays.toString(neighbors));
        Arrays.sort(costMst, (x, y)->Integer.compare(x[2], y[2]));
        st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken());
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int total = totalCost;
            for (int j = M - 1; j >= 0; j--) {
                int a = costMst[j][0];
                int b = costMst[j][1];
                if(a == 0 && b == 0) break;
                if ((a == X && neighbors[b] > 1)
                        || (b == X && neighbors[a] > 1)
                        || (a == Y && neighbors[b] > 1)
                        || (b == Y && neighbors[a] > 1)
                        || (neighbors[a] > 1 && neighbors[b] > 1)
                        || (a == X && b == Y)
                        || (a == Y && b == X)) {
                    total -= costMst[j][2];
                    break;
                }
            }
            System.out.println(total);
            //System.out.println(totalCost);
        }
    }

    public static int find(int x){
        if(parents[x] != x){
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    public static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa != pb){
            parents[pb] = pa;
        }
    }

    public static void mst(){
        int c = 0;

        for(int i = 0; i < M; i++){
            int a = distances[i][0];
            int b = distances[i][1];
            int cost = distances[i][2];
            if(find(a) != find(b)){
                union(a,b);
                totalCost += cost;
                costMst[c][0] = a;
                costMst[c][1] = b;
                costMst[c][2] = cost;
                c++;
                neighbors[a] ++;
                neighbors[b] ++;
            }
        }
    }
}
