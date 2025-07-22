"""
피보나치 함수: dp
작성일: 2025-07-22
메모리: 108384 KB
시간: 80 ms
"""
import sys
input = sys.stdin.readline

# 최대 40까지이므로 41칸 생성
zero_count = [0] * 41
one_count = [0] * 41

# 초기값 설정
zero_count[0] = 1
one_count[1] = 1

# DP로 각 N에 대해 0과 1의 출력 횟수 미리 계산
for i in range(2, 41):
    zero_count[i] = zero_count[i - 1] + zero_count[i - 2]
    one_count[i] = one_count[i - 1] + one_count[i - 2]

T = int(input())
for _ in range(T):
    N = int(input())
    print(zero_count[N], one_count[N])
