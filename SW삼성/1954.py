T = int(input())
for tc in range(T):
    N = int(input())
    matrix = [[0] * N for x in range(N)]
    # 0: 좌 1: 하 2: 우 3: 상
    direction = 0
    x = 0
    y = 0
    j = 1
    while j <= N * N:
        # 1, 2, 3
        if direction == 0:
            if 0 <= y < N:
                if matrix[x][y] == 0:
                    matrix[x][y] = j
                    j += 1
                    y += 1
                else:
                    direction = 1
                    x += 1
                    y -= 1
            else:
                direction = 1
                x += 1
                y -= 1

        elif direction == 1:
            if 0 <= x < N:
                if matrix[x][y] == 0:
                    matrix[x][y] = j
                    j += 1
                    x += 1
                else:
                    direction = 2
                    y -= 1
                    x -= 1
            else:
                direction = 2
                x -= 1
                y -= 1
        elif direction == 2:
            print(x, y)
            if N > y >= 0:
                if matrix[x][y] == 0:
                    matrix[x][y] = j
                    j += 1
                    y -= 1
                else:
                    direction = 3
                    y += 1
                    x -= 1
            else:
                direction = 3
                y += 1
                x -= 1
        elif direction == 3:
            if 0 <= x < N:
                if matrix[x][y] == 0:
                    matrix[x][y] = j
                    j += 1
                    x -= 1
                else:
                    direction = 0
                    x += 1
                    y += 1
            else:
                direction = 0
                x += 1
                y += 1
    print(f"#{tc + 1}")
    for result in matrix:
        print(" ".join(map(str, result)))
