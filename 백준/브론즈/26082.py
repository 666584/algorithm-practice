"""
가격 대비 성능 = 성능/가격
a = 경쟁사의 가격
b = 경쟁사 성능
c = 가격

경쟁사 가격 대비 성능 = b/a
가격 대비 성능 = 경쟁사 가격 대비 성능 * 3

작성일: 2025-07-14
메모리: 108384 KB
시간: 	88 ms
"""
a, b, c = map(int, input().split())
price_a = b/a
price_b = price_a * 3
result = int(price_b * c)
print(result)