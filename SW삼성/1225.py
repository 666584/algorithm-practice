for _ in range(10):
    i = int(input())
    num_list = []
    num_list = list(map(int, input().split()))
    j = 1
    while True:
        after_list = num_list[1:]
        new = num_list[0] - j
        num_list = after_list[:]
        if new <= 0:
            new = 0
            num_list.append(new)
            break
        else:
            num_list.append(new)
            j += 1
            if j > 5:
                j = 1
    print(f"#{i}", end= " ")
    for x in range(7):
        print(num_list[x], end=" ")
    print(num_list[7])