T = int(input())
for tc in range(T):
    N = int(input())
    matrix = []
    for j in range(N):
        matrix.append(list(map(str, input().strip())))
    def is_continuous(matrix):
        # 가로
        for x in range(N):
            continuous = 0
            for y in range(N):
                if matrix[x][y] != 'o':
                    continuous = 0
                else:
                    continuous += 1
                    if continuous >= 5:
                           return True
        # 세로
        for x in range(N):
            continuous = 0
            for y in range(N):
                if matrix[y][x] != 'o':
                    continuous = 0
                else:
                    continuous += 1
                    if continuous >= 5:
                        return True
        # 대각선1
        start_x = 0
        while start_x+ 5 <= N:
            start_y = 0
            while start_y+5 <= N:
                x = start_x
                y = start_y
                continuous = 0
                while x < N and y < N:
                    if matrix[x][y] != 'o':
                        continuous = 0
                    else:
                        continuous += 1
                        if continuous >= 5:
                            return True
                    x += 1
                    y += 1
                start_y += 1
            start_x += 1
        # 대각선2
        start_x = 0
        while start_x+ 5 <= N:
            start_y = N-1
            while start_y - 5 >= -1: 
                x = start_x
                y = start_y
                continuous = 0
                while x < N and y >= 0:
                    if matrix[x][y] != 'o':
                        continuous = 0
                    else:
                        continuous += 1
                        if continuous >= 5:
                            return True
                    x += 1
                    y -= 1
                start_y -= 1
            start_x += 1
        return False
    if is_continuous(matrix):
        print(f"#{tc+1} YES")
    else:
        print(f"#{tc+1} NO")