t = int(input())

def result_to_num(result):
    res = 0
    z = len(result) - 1
    for number in result:
        res += (number * (10**z))
        z -= 1
    return res

def second_max_result(curr_list):
    new_list = curr_list[:]
    new_list[-1], new_list[-2] = new_list[-2], new_list[-1]
    return new_list
def new_max_finder(changed_i, changed_j, curr_list, n_filp, results, n, max_num, solved):
    # 중복일 때를 고려해봐야 된다. 
    if n_filp == 0:
        return curr_list, False
    
    if max_num == result_to_num(curr_list):
        if n_filp % 2 == 0:
            return curr_list, True
        else:
            return second_max_result(curr_list), True
 
    for i in range (0, len(curr_list) - 1):
        for j in range(i+1, len(curr_list)):
            if not changed_i and not changed_j: 
                curr_list[i], curr_list[j] = curr_list[j], curr_list[i]
                fin_list, stop = new_max_finder(i,j, curr_list, n_filp-1, results, n, max_num, solved)
                if stop:
                    return fin_list,True
                results.append(fin_list[:])
                curr_list[i], curr_list[j] = curr_list[j], curr_list[i]
            elif i != changed_i or j != changed_j: 
                curr_list[i], curr_list[j] = curr_list[j], curr_list[i]
                fin_list, stop = new_max_finder(i,j, curr_list, n_filp-1, results, n, max_num, solved)
                if stop:
                    return fin_list,True
                results.append(fin_list[:])
                curr_list[i], curr_list[j] = curr_list[j], curr_list[i]
    max_num = 0
    max_list = []
    for result in results:
        res = result_to_num(result)
        if res >= max_num:
            max_num = res
            max_list = result   
    return max_list, False

results = []
for _ in range(t):
    num, n = map(int, input().strip().split())
    num = str(num)
    num_list = []
    for a in num:
        num_list.append(int(a))
    max_list = []
    max_num = result_to_num(sorted(num_list, reverse=True))
    result, _ = new_max_finder(None, None, num_list, n, max_list, n, max_num, False)
    results.append(result)

for i in range(t):
    print(f"#{i+1} {result_to_num(results[i])}")