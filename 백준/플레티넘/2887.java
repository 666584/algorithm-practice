import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;

    static class Planet {
        int id, x, y, z;
        Planet(int id, int x, int y, int z){
            this.id=id; this.x=x; this.y=y; this.z=z;
        }
    }

    static class Edge {
        int a, b, w;
        Edge(int a, int b, int w){
            this.a=a; this.b=b; this.w=w;
        }
    }

    static int find(int x){
        if(parent[x]==x) return x;
        return parent[x]=find(parent[x]);
    }

    static boolean union(int a, int b){
        int pa=find(a), pb=find(b);
        if(pa==pb) return false;
        parent[pb]=pa;
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        Planet[] planets = new Planet[N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int z=Integer.parseInt(st.nextToken());
            planets[i]=new Planet(i,x,y,z);
        }

        ArrayList<Edge> edges = new ArrayList<>(3*(N-1));

        // x 정렬 후 이웃 연결
        Arrays.sort(planets, Comparator.comparingInt(p -> p.x));
        for(int i=0;i<N-1;i++){
            Planet p1=planets[i], p2=planets[i+1];
            edges.add(new Edge(p1.id, p2.id, Math.abs(p1.x - p2.x)));
        }

        // y 정렬
        Arrays.sort(planets, Comparator.comparingInt(p -> p.y));
        for(int i=0;i<N-1;i++){
            Planet p1=planets[i], p2=planets[i+1];
            edges.add(new Edge(p1.id, p2.id, Math.abs(p1.y - p2.y)));
        }

        // z 정렬
        Arrays.sort(planets, Comparator.comparingInt(p -> p.z));
        for(int i=0;i<N-1;i++){
            Planet p1=planets[i], p2=planets[i+1];
            edges.add(new Edge(p1.id, p2.id, Math.abs(p1.z - p2.z)));
        }

        // 간선 정렬 (Kruskal)
        edges.sort(Comparator.comparingInt(e -> e.w));

        parent = new int[N];
        for(int i=0;i<N;i++) parent[i]=i;

        long total = 0;
        int used = 0;
        for(Edge e: edges){
            if(union(e.a, e.b)){
                total += e.w;
                used++;
                if(used == N-1) break;
            }
        }

        System.out.println(total);
    }
}
