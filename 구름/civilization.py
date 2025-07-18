import sys
from collections import deque
n, k = map(int, sys.stdin.readline().strip().split())
originals = deque()
for _ in range(k):
    x, y = map(int, sys.stdin.readline().strip().split())
    originals.append((x,y))
directions = [(0, 1), (1, 0), (-1, 0), (0, -1)]
def bfs():
    queue = deque(originals)
    depth = [[-1]*(n+1) for _ in range(n+1)]
    for ox, oy in originals:
        depth[ox][oy] = 0
    
    while queue:
        node = queue.popleft()
        x, y = node
        print(queue)
        print(node, depth[x][y])
        for dx, dy in directions:
            nx = x + dx
            ny = y + dy
            if nx > 0 and ny > 0 and nx < n + 1 and ny < n + 1:
                if depth[nx][ny] == -1:
                    queue.append((nx, ny))
                    depth[nx][ny] = depth[x][y] + 1
bfs()