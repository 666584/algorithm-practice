"""
작성일: 2025-07-15
메모리: 109544 KB
시간: 	100 ms
"""

import sys
n = int(sys.stdin.readline())
for _ in range(n):
    stack = []
    case = sys.stdin.readline().strip()
    vaild = 1
    for i in case:
        if i == "(":
            stack.append("(")
        elif i == ")":
            if stack:
                stack.pop()
            else:
                vaild = 0
                break
    if stack:
        vaild = 0
    print("YES" if vaild == 1 else "NO")