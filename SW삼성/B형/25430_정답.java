import java.util.*;
 
class UserSolution {
 
    static final int MAX_HEAP = 100000;
    static int n, heapSize;
    static int[] cDist, bDist, heapCost, heapId;
    static char[] heapType;
    static List<Street>[] graph;
 
    static class Street{
        int id, cost;
 
        public Street(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }
 
    static class Node {
        int id, cost;
        char type;
    }
    static Node node = new Node();
 
    // Min. Heap 데이터 입력 메서드
    static void insert(int id, int cost, char type) {
        if(heapSize >= MAX_HEAP - 1) return;
 
        int idx = ++heapSize;
        heapId[idx] = id;
        heapCost[idx] = cost;
        heapType[idx] = type;
 
        while(idx > 1) {
            int parentIdx = idx/2;
            if(heapCost[parentIdx] <= heapCost[idx]) break;
 
            swap(parentIdx, idx);
            idx = parentIdx;
        }
    }
 
    // Min. Heap 데이터 제거 메서드
    static boolean poll() {
        if(heapSize == 0) return false;
 
        node.id = heapId[1];
        node.cost = heapCost[1];
        node.type = heapType[1];
 
        heapId[1] = heapId[heapSize];
        heapCost[1] = heapCost[heapSize];
        heapType[1] = heapType[heapSize];
        heapSize--;
 
        int idx = 1;
        while(idx*2 <= heapSize) {
            int child = idx*2;
            if(child+1 <= heapSize && heapCost[child] > heapCost[child+1]) child++;
            if(heapCost[idx] <= heapCost[child]) break;
 
            swap(idx, child);
            idx = child;
        }
        return true;
    }
 
    // Min. Heap 위치 변경 메서드. 정렬 상태 유지를 위해 부모 노드와 선택된 자식 노드의 위치 변경
    static void swap(int p, int c) {
        int tempId = heapId[p];
        heapId[p] = heapId[c];
        heapId[c] = tempId;
 
        int tempCost = heapCost[p];
        heapCost[p] = heapCost[c];
        heapCost[c] = tempCost;
 
        char tempType = heapType[p];
        heapType[p] = heapType[c];
        heapType[c] = tempType;
    }
 
    public void init(int N, int K, int[] sBuilding, int[] eBuilding, int[] mDistance) {
        n = N;
        cDist = new int[N];
        bDist = new int[N];
 
        heapId = new int[MAX_HEAP];
        heapCost = new int[MAX_HEAP];
        heapType = new char[MAX_HEAP];
        graph = new ArrayList[N];
 
        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }
 
        for(int i=0; i<K; i++) {
            add(sBuilding[i], eBuilding[i], mDistance[i]);
        }
    }
 
    public void add(int sBuilding, int eBuilding, int mDistance) {
        graph[sBuilding].add(new Street(eBuilding, mDistance));
        graph[eBuilding].add(new Street(sBuilding, mDistance));
    }
 
    public int calculate(int M, int[] mCoffee, int P, int[] mBakery, int R) {
        int minDist = 2 * R + 1;
        Arrays.fill(cDist, R+1);
        Arrays.fill(bDist, R+1);
        heapSize = 0;
 
        for(int i=0; i<M; i++) {
            int idx = mCoffee[i];
            cDist[idx] = 0;
 
            insert(idx, 0, 'c');
        }
 
        for(int i=0; i<P; i++) {
            int idx = mBakery[i];
            bDist[idx] = 0;
             
            insert(idx, 0, 'b');
        }
 
        while(poll()) {
            int cur = node.id;
            int cost = node.cost;
            char type = node.type;
 
            if(cost >= minDist || cost > R) break;
 
            // 이미 더 짧은 거리로 방문한 곳은 패스
            if((type == 'c' && cost > cDist[cur]) || (type == 'b' && cost > bDist[cur])) continue;
 
            List<Street> nextBuildings = graph[cur];
            for(int i=0; i<nextBuildings.size(); i++) {
                Street next = nextBuildings.get(i);
 
                int nextBuilding = next.id;
                int newCost = cost + next.cost;
                if(newCost > R || newCost >= minDist) continue;
 
                if(type == 'c') {
                    // 이미 더 짧은 이동 경로로 도착한 주택이라면
                    if(newCost >= cDist[nextBuilding]) continue;
                    cDist[nextBuilding] = newCost;
                    if(bDist[nextBuilding] > 0 && bDist[nextBuilding] <= R) {
                        minDist = Math.min(minDist, newCost + bDist[nextBuilding]);
                    }
                    insert(nextBuilding, newCost, 'c');
                } else {
                    if(newCost >= bDist[nextBuilding]) continue;
                    bDist[nextBuilding] = newCost;
                    if(cDist[nextBuilding] > 0 && cDist[nextBuilding] <= R) {
                        minDist = Math.min(minDist, newCost + cDist[nextBuilding]);
                    }
                    insert(nextBuilding, newCost, 'b');
                }
            }
        }
 
        return minDist <= 2 * R ? minDist : -1;
    }
}
