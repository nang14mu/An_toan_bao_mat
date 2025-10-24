'''
MẬT MÃ MA TRẬN KHÓA PLAYFAIR
Input: M = THETRUTHWILLOU
Key: K = THEDIEIS
Tìm Output: C =
'''
import playfair

M = "THETRUTHWILLOU"
K = "THEDIEIS"

p = playfair.Playfair(K)

print(f"C = {p.__encrypt__(M)}")