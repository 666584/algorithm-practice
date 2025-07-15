"""
해시를 사용한 집합과 맵
작성일: 2025-07-15
메모리: 254780 KB
시간: 	628 ms
"""
import sys
from collections import Counter
n = int(sys.stdin.readline())
card_list = list(map(int, sys.stdin.readline().split()))
m_count = int(sys.stdin.readline())
m_list = list(map(int, sys.stdin.readline().split()))
count = Counter(card_list)

for m in m_list:
    # 공백 한줄로 출력
    # 키가 없을 경우 0을 출력
    print(count.get(m, 0), end= " ")