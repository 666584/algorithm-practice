for i in range(10):
    matrix = []
    t = int(input())
    for j in range(100):
        matrix.append(list(map(int, input().split())))
    max_num  = 0
    for col in matrix:
        sum_num = sum(col)
        if sum_num >= max_num:
            max_num = sum_num
    for z in range(100):
        sum_num = 0
        for x in range(100):
            sum_num += matrix[x][z]
        if sum_num >= max_num:
            max_num = sum_num
    sum_num = 0
    for z in range(100):
        sum_num += matrix[z][z]
    if sum_num >= max_num:
        max_num = sum_num
    sum_num = 0
    for z in range(100):
        sum_num += matrix[z][99-z]
    if sum_num >= max_num:
        max_num = sum_num 
    print(f"#{t} {max_num}")
    