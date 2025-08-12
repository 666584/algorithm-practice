"""
EKO/ 이분 탐색
작성일: 2025-08-03
메모리: 207176 KB
시간: 740 ms
모든 합 >= m
"""
import sys
n, m = map(int, sys.stdin.readline().strip().split())
heights = list(map(int, sys.stdin.readline().strip().split()))

heights.sort(reverse=True)
first_left = heights[0]
first_right = 0

def binary_search(left, right):
    mid = int((left+right) / 2)
    print(mid)
    mid_score = 0
    if left == 1:
        print("result")
        print(left)
        return left
    for height in heights:
        if height <= mid:
            break
        mid_score += height - mid
    print("mid_score")
    print(mid_score)

    if m < mid_score:
        right = mid
        print("right")
        print(left, right)
        return binary_search(left, right)
    elif m > mid_score:
        left = mid - 1
        print("left")
        print(left, right)
        return binary_search(left, right)
    elif m == mid_score:
        print(mid)
        return mid

print(binary_search(first_left, first_right))



"""
5-0
2: 4 1-0
0: 8 0-0


3-0
1: 4 3-2
2: 2 1-2


20 17 15 10
20 15 10 17
20 : 안됨.
19 : 1
18 : 2
17 : 3
16 : 5
15 : 7
14 : 10
13 : 13
12 : 16
11 : 19
10 : 22

4 42 40 26 46
46: 안됨
42: 안됨
40: 안됨
26: 됨.
4: 됨.
26-40 사이임.
23: 62
35: 23 됨.
36: 여기. 
37: 17 안됨.
38: 14 안됨.
41: 6 안됨.
46: 0

23: 62
35: 23 42 - 36
41: 6  40 - 36
38: 14 37 - 36
36: 20


4: 4 3-0
2: 8 1-0
1: 10 0-0

"""