"""
ISBN 
10의 배수, 13 digit
9780921418 : 처음 10자리수
마지막 3자리를 input으로 받고 1-3 sum을 출력.
작성일: 2025-07-24
메모리: 108384 KB
시간: 	92 ms
"""
import sys
input = sys.stdin.readline
sum = 9 + 7*3 + 8 + 9 + 2*3 + 1 + 4*3 + 1 + 8*3
for i in range(3):
    n = int(input())
    if i == 1:
        sum += n*3
    else:
        sum += n
print(f"The 1-3-sum is {sum}")
