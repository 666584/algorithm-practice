package SWEA_26281;

import java.util.*;

class UserSolution {
    int N, M;
    int[][] board;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    ArrayList<String> grades = new ArrayList<>();
    HashMap<String, Integer> rankMap = new HashMap<>();

    void init(int N, int M, String[][] mGrade) {
        this.N = N;
        this.M = M;

        grades.clear();
        rankMap.clear();
        buildGrades(new StringBuilder(), M);

        for (int i = 0; i < grades.size(); i++) {
            rankMap.put(grades.get(i), i);
        }

        board = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                board[r][c] = rankMap.get(mGrade[r][c]);
            }
        }
    }

    void buildGrades(StringBuilder sb, int maxLen) {
        if (sb.length() > 0) {
            grades.add(sb.toString());
        }
        if (sb.length() == maxLen) return;

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            sb.append(ch);
            buildGrades(sb, maxLen);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    void change(int mRow, int mCol, int mDir, int mLength, String mGrade) {
        int rank = rankMap.get(mGrade);

        if (mDir == 0) {
            for (int i = 0; i < mLength; i++) {
                board[mRow + i][mCol] = rank;
            }
        } else {
            for (int i = 0; i < mLength; i++) {
                board[mRow][mCol + i] = rank;
            }
        }
    }

    boolean canGo(int passRank, int L, int sRow, int sCol, int eRow, int eCol) {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sRow, sCol});
        dist[sRow][sCol] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if (dist[r][c] > L) continue;
            if (r == eRow && c == eCol) return true;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (dist[nr][nc] != -1) continue;

                // 출발칸은 제외지만, "이동해서 들어가는 칸"은 검사해야 함
                if (board[nr][nc] < passRank) continue;

                dist[nr][nc] = dist[r][c] + 1;
                if (dist[nr][nc] <= L) {
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return false;
    }

    String calculate(int L, int sRow, int sCol, int eRow, int eCol) {
        int lo = 0, hi = grades.size() - 1;
        int ans = 0;

        while (lo <= hi) {
            int mid = (lo + hi) >> 1;

            if (canGo(mid, L, sRow, sCol, eRow, eCol)) {
                ans = mid;
                lo = mid + 1;   // 더 낮은 보안등급(사전순 뒤)도 가능한지
            } else {
                hi = mid - 1;
            }
        }

        return grades.get(ans);
    }
}
