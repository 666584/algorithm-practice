"""
작성일: 2025-07-16
메모리: 117512 KB
시간: 168 ms
"""
import sys
n = int(sys.stdin.readline())
# set은 같은 값을 하나만 저장한다.
words = set()
for _ in range(n):
    words.add(sys.stdin.readline().strip())
print("\n".join(sorted(words,key= lambda x: (len(x),x))))