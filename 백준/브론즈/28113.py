"""
n: 숭실대입구역까지 걸리는 시간
a: 숭실대별관에서 버스가 출발하는 시간
b: 숭실대입구역에서 지하철이 출발하는 시간

작성일: 2025-07-14
메모리: 108384 KB
시간: 	92 ms
"""
n, a, b = map(int, input().split())

if b >= n:
    subway = b
if b < n:
    print("Bus")
elif a < b:
    print("Bus")
elif a > b:
    print("Subway")
elif a == b:
    print("Anything")