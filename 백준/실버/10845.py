"""
작성일: 2025-07-15
메모리: 112836 KB
시간: 	200 ms
"""
import sys
from collections import deque
queue = deque()
n = int(sys.stdin.readline())
for _ in range(n):
    command = sys.stdin.readline().strip()
    if command == "pop":
        print(queue.popleft() if queue else -1)
    elif command == "size":
        print(len(queue))
    elif command == "empty":
        print(1 if not queue else 0)
    elif command == "front":
        print(queue[0] if queue else -1)
    elif command == "back":
        print(queue[-1] if queue else -1)
    else:
        c, num = command.split()
        queue.append(num)