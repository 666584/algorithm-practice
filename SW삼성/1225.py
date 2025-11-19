for _ in range(10):
    tc = int(input())
    nums = list(map(int, input().split()))

    minus = 1
    while True:
        value = nums.pop(0) - minus

        if value <= 0:
            nums.append(0)
            break

        nums.append(value)
        minus = minus % 5 + 1   # 1 → 2 → 3 → 4 → 5 → 1 순환

    print(f"#{tc}", *nums)