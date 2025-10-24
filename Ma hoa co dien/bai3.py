'''
MẬT MÃ VIGENERE - AUTOKEY
Input: M = MONEYMAKESTHE
Key: K = YOUREON
Tìm Output: C =
'''

import autoKey

M = "MONEYMAKESTHE"
K = "YOUREON"

a = autoKey.autoKey(K)

print(f"C = {a.__encrypt__(M)}")