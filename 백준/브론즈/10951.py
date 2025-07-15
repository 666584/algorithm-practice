"""
끝이 정해지지 않은 입력

작성일: 2025-07-15
메모리: 108384 KB
시간: 	88 ms
"""
import sys
for line in sys.stdin:
    n1, n2 = map(int, line.strip().split())
    print(int(n1 + n2))