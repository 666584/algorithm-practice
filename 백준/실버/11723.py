"""
집합
작성일: 2025-07-22
메모리: 128400 KB
시간: 2180 ms
"""
import sys
s = set()
num = int(sys.stdin.readline())
for _ in range(num):
    command = list(sys.stdin.readline().strip().split())
    if command[0] == "add":
        s.add(int(command[1]))
    if command[0] == "remove":
        if int(command[1]) in s:
            s.remove(int(command[1]))
    if command[0] == "check":
        print(1 if int(command[1]) in s else 0)
    if command[0] == "toggle":
        if int(command[1]) in s:
            s.remove(int(command[1]))
        else:
            s.add(int(command[1]))
    if command[0] == "all":
        s.clear()
        s = set(i for i in range(1,21))
    if command[0] == "empty":
        s.clear()