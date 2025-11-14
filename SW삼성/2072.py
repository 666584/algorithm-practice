import sys
t = int(sys.stdin.readline())

def sum_odds(nums: list):
    result = 0
    
    for num in nums:
        if num % 2 != 0:
            result += num
    
    return result

results = []
for i in range(t):
    nums = list(map(int, sys.stdin.readline().strip().split()))
    result = sum_odds(nums)
    results.append(result)

i = 1
for result in results:
    print(f"#{i} {result}")
    i += 1