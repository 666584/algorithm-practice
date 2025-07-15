"""
작성일: 2025-07-15
메모리: 110960 KB
시간: 120 ms
"""
import sys
n, m = map(int, sys.stdin.readline().split())
color_list = []
for _ in range(n):
    color_list.append(sys.stdin.readline().strip())

def find_min_num_color():
    min_num_color = float('inf') 
    for i in range(0, n - 7):
        for j in range(0, m - 7):
            w_num_color = find_num_color_W(i, j)
            b_num_color = find_num_color_B(i, j)
            new = min(w_num_color, b_num_color)
            if min_num_color > new:
                min_num_color = new
    print(min_num_color)

# 시작이 W일때
def find_num_color_W(r, c):
    num_color = 0
    for i in range(8):
        for j in range(8):
            # 홀수의 경우
            if i%2 != 0:
                if j%2 != 0:
                    if color_list[i+r][j+c] != "W":
                        num_color += 1
                elif color_list[i+r][j+c] != "B":
                    num_color += 1
            else:
                if j%2 != 0:
                    if color_list[i+r][j+c] != "B":
                        num_color += 1
                elif color_list[i+r][j+c] != "W":
                    num_color += 1
    return num_color

# 시작이 B일때
def find_num_color_B(r, c):
    num_color = 0
    for i in range(8):
        for j in range(8):
            if i%2 != 0:
                if j%2 != 0:
                    if color_list[i+r][j+c] != "B":
                        num_color += 1
                elif color_list[i+r][j+c] != "W":
                    num_color += 1
            else:
                if j%2 != 0:
                    if color_list[i+r][j+c] != "W":
                        num_color += 1
                elif color_list[i+r][j+c] != "B":
                    num_color += 1
    return num_color

find_min_num_color()