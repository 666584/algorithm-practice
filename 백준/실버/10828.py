"""
작성일: 2025-07-15
메모리: 112872 KB
시간: 	136 ms
"""
import sys
from collections import deque
stack = deque()
n = int(sys.stdin.readline())
for _ in range(n):
    command = sys.stdin.readline().strip()
    if command == "pop":
        print(stack.popleft() if stack else -1)
    elif command == "size":
        print(len(stack))
    elif command == "empty":
        print(1 if not stack else 0)
    elif command == "top":
        print(stack[0] if stack else -1)
    else:
        c, num = command.split()
        stack.appendleft(num)