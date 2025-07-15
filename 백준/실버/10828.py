"""
작성일: 2025-07-15
메모리: 112872 KB
시간: 	136 ms
"""
import sys
stack = []
n = int(sys.stdin.readline())
for _ in range(n):
    command = sys.stdin.readline().strip()
    if command == "pop":
        print(stack.pop() if stack else -1)
    elif command == "size":
        print(len(stack))
    elif command == "empty":
        print(1 if not stack else 0)
    elif command == "top":
        print(stack[-1] if stack else -1)
    else:
        c, num = command.split()
        stack.append(num)