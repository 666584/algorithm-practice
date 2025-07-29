"""
작성일: 2025-07-29
메모리: 108384 KB
시간: 	88 ms
"""
import sys
questions = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"]
team_list = [[8, 10], [1, 3, 9, 10], [1, 3, 9, 10], [3, 8, 9, 10], [1, 3, 8, 9, 10], [1, 3, 8, 9, 10], [1, 3, 8, 9, 10], [1, 3, 8, 9, 10], [1, 3, 8, 9, 10], [3, 4, 8, 9, 10]]
n = int(sys.stdin.readline())
print(13-len(team_list[n-1]))
for i in range(13):
    if i not in team_list[n-1]:
        print(questions[i], end=" ")