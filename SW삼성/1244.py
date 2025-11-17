t = int(input())
def find_max_num(num_list, visited):
    max_num = 0
    max_i = 0
    for i in range(len(num_list)):
        x = num_list[i]
        if i not in visited:
            if x >= max_num:
                max_num = x
                max_i = i
    return max_num, max_i

def find_max_resut(num, N):
    num = str(num)
    num_list = []
    visited = []
    for a in num:
        num_list.append(int(a))
    for _ in range(N):
        max_n, max_i = find_max_num(num_list, visited)
        for i in range(len(num_list)):
            if i != max_i and i not in visited:
                prev = num_list[i]
                num_list[i] = max_n
                num_list[max_i] = prev
                visited = []
                visited.append(i)
                visited.append(max_i)
                break
    return num_list

results = []
for _ in range(t):
    num, n = map(int, input().strip().split())
    results.append(find_max_resut(num, n))

z = 0
for result in results:
    res = 0
    i = len(result) - 1
    for number in result:
        res += (number * (10**i))
        i -= 1
    print(f"#{z+1} {res}")
    z += 1