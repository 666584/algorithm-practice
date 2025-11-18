T = int(input())
for i in range(T):
    N, M, L = map(int, input().split())
    num_list = list(map(int, input().split()))
    for j in range(M):
        new_i, new_num = map(int, input().split())
        after_list = num_list[new_i:]
        num_list = num_list[:new_i]
        num_list.append(new_num)
        for num in after_list:
            num_list.append(num)
    print(f"#{i + 1} {num_list[L]}")