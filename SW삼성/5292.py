T = int(input())
for i in range(T):
    n1, n2 = map(int, input().split())
    set1 = set(map(int, input().split()))
    set2 = set(map(int, input().split()))
    result = len(set1& set2)
    print(f"#{i+1} {result}")
# set1 ^ set2 -> set1 와 set2가 겹치지 않는 것.