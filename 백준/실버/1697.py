"""
NOT FINISHED
n = start point
k = goal
walking: x-1 or x+1
Teleporting: 2*x
"""
5 - 4, 6, 10
4 - 3, 7, 8
import sys
from collections import deque

def bfs(start, goal):
    queue = deque([start])
    visited = [start]
    time = 0
    while queue:
        number = queue.popleft()
        print(number)
        if number == goal:
            return time
        moves = [number - 1, number + 1, number * 2]
        for move in moves:
            if move >= 0 and move <= 100000 and move not in visited:
                time += 1
                queue.append(move)
                visited.append(move)
    
print(bfs(5, 17))
        