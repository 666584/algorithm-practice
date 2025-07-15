"""
set은 무작위로 저장한다.
dict은 key에 대해 value를 하나만 저장할 수 있다.
마지막으로 저장된 값이 dict에 들어간다.
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