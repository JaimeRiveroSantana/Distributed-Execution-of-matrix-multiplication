# mapper.py
import sys

N = 2

for line in sys.stdin:
    matrix, i, j, value = line.strip().split(',')
    i, j = int(i), int(j)
    value = float(value)

    if matrix == 'A':
        for col in range(N):
            print(f"{i},{col}\tA,{j},{value}")
    else:  # matrix == 'B'
        for row in range(N):
            print(f"{row},{j}\tB,{i},{value}")
