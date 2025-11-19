"""
3 -> 1 
5 -> 2 
7 -> 3


i = n // 2 next row -> i -= 1

x = 1
x += 2 until x = n 

if x = n then 
x -= 2 until x = 1
"""
T = int(input())
for i in range(T):
    N = int(input())
    matrix = []
    for j in range(N):
        num = input().strip()
        num_list = [int(n) for n in num] 
        matrix.append(num_list)
    num_in_row = 1
    start_num = N // 2
    total = 0
    row_num = 0
    while num_in_row <= N:
        for z in range(start_num, start_num + num_in_row):
            total += matrix[row_num][z]
        num_in_row += 2
        row_num += 1
        start_num -= 1
    num_in_row = N - 2
    start_num = 1
    while num_in_row > 0:
        for z in range(start_num, start_num + num_in_row):
            total += matrix[row_num][z]
        num_in_row -= 2
        row_num += 1
        start_num += 1
    print(f"#{ i + 1} {total}")