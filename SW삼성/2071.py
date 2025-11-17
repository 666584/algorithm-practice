T = int(input())
for i in range(T):
    num_list = list(map(int, input().strip().split()))
    total = 0
    for num in num_list:
        total += num
    result = total / 10
    print(f"#{i + 1} {result:.0f}")