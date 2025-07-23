"""
1로 만들기
작성일: 2025-07-22
메모리: 108384 KB
시간: 80 ms
"""
import sys
input = sys.stdin.readline
n = int(input())
count = 0
"""while n != 1:
    if n % 3 == 0:
        n = n / 3
        count += 1
    elif n % 2 == 0:
        n = n / 2
        count += 1
    else:
        n -= 1
        count += 1
    print(n)
print(count)"""

def get_count(n):
    print("n")
    print(n)
    if n == 1:
        return count
    if n - 1 > 1:
        print(1)
        return get_count(n-1)
    if n % 3 == 0:
        print(3)
        return get_count(n/3)
    if n % 2 == 0:
        print(2)
        return get_count(n/2)
get_count(n)
"""
10-1 = 9
9/3 = 3
3/3 = 1 
or 
10/2 = 5
5 - 1 = 4
4/2 = 2
2/2 = 1 
"""