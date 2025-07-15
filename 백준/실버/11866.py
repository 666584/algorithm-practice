"""
작성일: 2025-07-15
메모리: 110624 KB
시간: 	124 ms
"""
import sys
from collections import deque

n, k = map(int, sys.stdin.readline().split())
queue = deque(range(1, n+1))
result = []

while queue: 
    # rotate(n): 시계방향 회전
    # roatate(-n): 반시계방향 회전 
    queue.rotate(-k + 1)
    # 맨 왼쪽 값을 돌려주고 리스트에서 삭제한다.
    result.append(queue.popleft())

# join: 리스트를 문자열로 합친다.
# map: 리스트를 특정 타입으로 바꾼다.
print("<" + ", ".join(map(str, result)) + ">")