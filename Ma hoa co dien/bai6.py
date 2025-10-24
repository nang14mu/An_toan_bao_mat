'''
MẬT MÃ HOÁN VỊ
Input: M = ABADBEGINNINGMAKES
Key: K = 9
Output: C = ?
'''

import railFence

M = "ABADBEGINNINGMAKES"
K = 9

r = railFence.railFence(K)

print(f"C = {r.__encrypt__(M)}")