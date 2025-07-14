"""
스텍 구현 함수
input 시간을 줄이기 위해 sys를 쓴다.
작성일: 2025-07-14
메모리: 181148 KB
시간: 	444 ms
"""
import sys
num_line = int(sys.stdin.readline().strip())
stack_list = []
  
for _ in range(num_line):
    command = sys.stdin.readline().strip()
    if len(command) != 1:
        parts = command.split()
        stack_list.append(int(parts[1]))
    elif command == '2':
        if stack_list:
            print(stack_list[-1])
            stack_list.pop()
        else:
            print("-1")
    elif command == '3':
        print(len(stack_list))
    elif command == '4':
        if not stack_list:
            print("1")
        else: 
            print("0")
    elif command == '5':
        if stack_list:
            num = stack_list[-1]
            print(num)
        else:
            print("-1")
