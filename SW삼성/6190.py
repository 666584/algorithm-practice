T = int(input())
for x in range(T):
    N = int(input())
    nums = list(map(int, input().split()))
    max_num = 0
    for i in range(0, N-1):
        for j in range(i+1, N):
            mul = str(nums[i] * nums[j])
            curr_n = mul[0]
            is_right = True
            for n in mul:
                if n < curr_n:
                    is_right = False
                    break
                curr_n = n
            if is_right:
                if max_num <= int(mul):
                    max_num = int(mul)
    if max_num == 0:
        print(f"#{x + 1} -1")
    else:
    	print(f"#{x + 1} {max_num}")
                
                    
            
            
    