# reducer.py
import sys
from collections import defaultdict

current_key = None
A = defaultdict(float)
B = defaultdict(float)

def emit(key):
    result = 0
    for k in A:
        result += A[k] * B.get(k, 0)
    print(f"{key}\t{result}")

for line in sys.stdin:
    key, value = line.strip().split('\t')
    tag, k, val = value.split(',')
    k = int(k)
    val = float(val)

    if key != current_key and current_key is not None:
        emit(current_key)
        A.clear()
        B.clear()

    current_key = key

    if tag == 'A':
        A[k] = val
    else:
        B[k] = val

if current_key:
    emit(current_key)
