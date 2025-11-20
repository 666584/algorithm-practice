def is_p(word):
    length = len(word)
    repeat = length // 2
    for a in range(repeat):
        if word[a] != word[-a-1]:
            return False
    return True
i = 0
for tc in range(10):
    num  = int(input())
    matrix = []
    for j in range(8):
        matrix.append(list(s for s in input()))
    total_num = 0
    # 가로
    for i in range(8):
        for m in range(8):
            for j in range(2, 9):
                word = []
                word = matrix[i][m:j]
                if is_p(word):
                    length = len(word)
                    if length == num:
                        total_num += 1
    # 세로
    for x in range(8):
        for v in range(8):
            for y in range(2, 9):
                word = []
                word = [matrix[c][x] for c in range(v,y)]
                if is_p(word):
                    length = len(word)
                    if length == num:
                        total_num += 1
    print(f"#{tc + 1} {total_num}")
    #print(is_p("CCCCABAACCAABACCCC"))
    #print(is_p('CC'))