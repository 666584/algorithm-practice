T = int(input())
for i in range(T):
    a, b = map(int, input().split())
    total = a + b
    if total % 24 == 0:
        result = 0
    elif total > 24: 
        n = total // 24
        result = total - 24*n
    else:
        result = total
    print(f"#{i+1} {result}")