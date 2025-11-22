T = int(input())
for i in range(T):
    num_list = list(map(int, input().strip().split()))
    result = sum(num_list) / 10
    print(f"#{i + 1} {result:.0f}")