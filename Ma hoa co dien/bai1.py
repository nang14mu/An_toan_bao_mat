'''
MẬT MÃ CAESAR
Input: M = NOROSEWITHOUTATH
Key: K = 8
Tìm Output: C =
'''
import ceasar
M = "NOROSEWITHOUTATH"
K = 8
c = ceasar.Ceasar(K)

print(c.__encrypt__(M))
