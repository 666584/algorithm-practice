"""
T
H: height, W: width
Matrix
N: instruction
Instruction string
"""
car_directions = ["^", "v", "<",">"]
T = int(input())
for i in range(T):
    H, W = map(int, input().split())
    matrix = []
    for _ in range(H):
        matrix.append(list(a for a in input().strip()))
    N = int(input())
    instructions = list(x for x in input().strip())
    # find location of car
    curr_i = 0
    curr_j = 0 
    curr_direction = 0
    for x in range(H):
        for y in range(W):
            if matrix[x][y] in car_directions:
                curr_i = x
                curr_j = y
                curr_direction = car_directions.index(matrix[x][y])
    for inst in instructions:
        if inst == "U":
            next_i = curr_i - 1
            next_j = curr_j
            if next_i >= 0:
                if matrix[next_i][next_j] == ".":
                    matrix[curr_i][curr_j]  = "."
                    curr_i = next_i
                    curr_j = next_j
            matrix[curr_i][curr_j] = "^"
            curr_direction = 0
        elif inst == "D":
            next_i = curr_i + 1
            next_j = curr_j
            if next_i < H:
                if matrix[next_i][next_j] == ".":
                    matrix[curr_i][curr_j]  = "."
                    curr_i = next_i
                    curr_j = next_j
            matrix[curr_i][curr_j] = "v"
            curr_direction = 1
        elif inst == "L":
            next_i = curr_i
            next_j = curr_j - 1
            if next_j >= 0: 
                if matrix[next_i][next_j] == ".":
                    matrix[curr_i][curr_j]  = "."
                    curr_i = next_i
                    curr_j = next_j
            matrix[curr_i][curr_j] = "<"
            curr_direction = 2
        elif inst == "R":
            next_i = curr_i
            next_j = curr_j + 1
            if next_j < W:
                if matrix[next_i][next_j] == ".":
                    matrix[curr_i][curr_j] = "."
                    curr_i = next_i
                    curr_j = next_j
            matrix[curr_i][curr_j] = ">"
            curr_direction = 3
        elif inst == "S":
            if curr_direction == 0:
                s = curr_i - 1
                while s >= 0:
                    if matrix[s][curr_j] == "*":
                        matrix[s][curr_j] = "."
                        break
                    elif matrix[s][curr_j] == "#":
                        break
                    s -= 1
            elif curr_direction == 1:
                for s in range(curr_i + 1, H):
                    if matrix[s][curr_j] == "*":
                        matrix[s][curr_j] = "."
                        break
                    elif matrix[s][curr_j] == "#":
                        break
            elif curr_direction == 2:
                s = curr_j - 1
                while s >= 0:
                    if matrix[curr_i][s] == "*":
                        matrix[curr_i][s] = "."
                        break
                    elif matrix[curr_i][s] == "#":
                        break
                    s -= 1
            elif curr_direction == 3:
                for s in range(curr_j + 1, W):
                    if matrix[curr_i][s] == "*":
                        matrix[curr_i][s] = "."
                        break
                    elif matrix[curr_i][s] == "#":
                        break
    print(f"#{i+1}", end=" ")
    for res in matrix:
        print("".join(map(str, res)))