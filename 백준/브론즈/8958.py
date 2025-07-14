"""
작성일: 2025-07-14
메모리: 109544 KB
시간: 96 ms
"""
import sys
n = int(sys.stdin.readline())
for _ in range(n):
    answers = sys.stdin.readline().strip()
    total_score = 0
    curr_score = 0
    prev_answer = ""
    for answer in answers:
        if answer == "O":
            if not prev_answer or prev_answer != "O":
                prev_answer = answer
                curr_score = 1
            else:
                curr_score += 1
            total_score += curr_score
        else:
            curr_score = 0
    print(total_score)
