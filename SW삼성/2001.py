# D2
T = int(input())
for tc in range(T):
    N, M = map(int, input().split())
    matrix = []
    for _ in range(N):
        matrix.append(list(map(int, input().split())))
        max_n = 0
    for i in range(N-M+1):
        for j in range(N-M+1):
            sum_n = 0
            for  x in range(M):
                for y in range(M):
                    sum_n += matrix[i + x][j + y]
                    if max_n <= sum_n:
                        max_n = sum_n
    print(f"#{tc+1} {max_n}")