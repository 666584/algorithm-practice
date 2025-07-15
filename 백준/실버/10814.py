"""
작성일: 2025-07-15
메모리: 133640 KB
시간: 256 ms
"""
import sys
n = int(sys.stdin.readline())
users = []
for _ in range(n):
    age, name = sys.stdin.readline().strip().split()
    users.append((age, name))
new_list = sorted(users, key= lambda x: int(x[0]))
for age, user in new_list:
    print(age, user)