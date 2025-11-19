T = int(input())

for t in range(1, T + 1):
    N = int(input())
    matrix = [list(map(int, input().strip())) for _ in range(N)]

    total = 0
    center = N // 2

    width = 1
    start_col = center

    row = 0
    while width <= N:
        total += sum(matrix[row][start_col : start_col + width])
        width += 2
        start_col -= 1
        row += 1

    # 아래쪽 삼각형
    width = N - 2
    start_col = 1

    while width > 0:
        total += sum(matrix[row][start_col : start_col + width])
        width -= 2
        start_col += 1
        row += 1

    print(f"#{t} {total}")