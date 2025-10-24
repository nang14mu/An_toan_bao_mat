'''
MẬT MÃ VIGENERE - LẶP KHÓA
Input: M = MONEYMAKESTHE
Key: K = YOUREON
Tìm Output: C =
'''

import vigenere

M = "MONEYMAKESTHE"
K = "YOUREON"

v = vigenere.vigenere(K)

print(f" C = {v.__encrypt__(M)}")