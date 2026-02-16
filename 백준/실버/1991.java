import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1991_트리순회 {
    static int N;
    static Node[] tree;
    static boolean[] isVisited;
    static class Node{
        int left;
        int right;

        Node(int left, int right){
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //System.out.println(('C'-'A'));
        N = Integer.parseInt(st.nextToken());
        tree = new Node[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int node = st.nextToken().charAt(0) - 'A';
            int left = st.nextToken().charAt(0) - 'A';
            int right = st.nextToken().charAt(0) - 'A';
            if (right < 0) right = -1;
            if(left < 0) left = -1;
            tree[node] = new Node(left, right);
        }
        isVisited = new boolean[N];
        traverse(0);
        System.out.println();
        isVisited = new boolean[N];
        traverse2(0);
        System.out.println();
        isVisited = new boolean[N];
        traverse1(0);
        System.out.println();
    }

    static void traverse(int n){
        Node node = tree[n];
        System.out.print((char)(n+'A'));
        isVisited[n] = true;
        if(node.left != -1 && !isVisited[node.left]) {
            traverse(node.left);
        }
        if(node.right != -1 && !isVisited[node.right]) {
            traverse(node.right);
        }
    }

    static void traverse1(int n){
        Node node = tree[n];
        isVisited[n] = true;
        if(node.left != -1 && !isVisited[node.left]) {
            traverse1(node.left);
        }
        if(node.right != -1 && !isVisited[node.right]) {
            traverse1(node.right);
        }
        System.out.print((char)(n+'A'));
    }

    public static void traverse2(int n){
        Node node = tree[n];
        isVisited[n] = true;
        if(node.left != -1 && !isVisited[node.left]) {
            traverse2(node.left);
        }
        System.out.print((char)(n+'A'));
        if(node.right != -1 && !isVisited[node.right]) {
            traverse2(node.right);
        }

    }
}
