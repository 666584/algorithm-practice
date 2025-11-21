T = int(input())
for i in range(T):
    N = int(input())
    matrix = [[0] *N for x in range(N)]
    print(matrix)
    # 0: 좌 1: 하 2: 상 3: 우
    direction = 0
    x = 0
    y = 0
    for j in range(1, N*N + 1):
       # 1, 2, 3
    	if direction == 0:
            matrix[x][y] = j
            if j == N:
                direction == 1
            else:
                y += 1
        elif direction == 1:
            print(x,y)
            break
            
    print(matrix)
    