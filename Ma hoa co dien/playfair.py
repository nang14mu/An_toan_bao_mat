alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"
class Playfair:

    def __init__(self, key = "ABCDEFGHIKLMNOPQRSTUVWXYZ"):
        self.key = [k.upper() for k in key]

        new_key = ""
        for ch in key:
            if ch not in new_key:
                new_key += ch

        for ch in alphabet:
            if ch not in new_key:
                new_key += ch

        self.key = new_key


    #mã hóa từng cặp
    def __encrypt_pair__(self, a, b):
        aRow, aCol = int(self.key.index(a)/5), self.key.index(a) % 5
        bRow, bCol = int(self.key.index(b)/5), self.key.index(b) % 5

        if aRow == bRow:
            return self.key[aRow * 5 + (aCol + 1)%5] + self.key[bRow * 5 + (bCol + 1)%5]
        if aCol == bCol:
            return self.key[((aRow + 1) % 5) * 5 + aCol] + self.key[((bRow + 1) % 5) * 5 + bCol]
        else:
            return self.key[aRow * 5 + bCol] + self.key[bRow * 5 + aCol]


    #giải mã từng cặp
    def __decrypt_pair(self, a, b):
        aRow, aCol = int(self.key.index(a)/5), self.key.index(a) % 5
        bRow, bCol = int(self.key.index(b)/5), self.key.index(b) % 5

        if aRow == bRow:
            return self.key[aRow * 5 + (aCol - 1) %5] + self.key[bRow * 5 + (bCol - 1) %5]
        if aCol == bCol:
            return self.key[((aRow - 1) % 5) * 5 + aCol] + self.key[((bRow - 1) % 5) * 5 + bCol]
        else:
            return self.key[aRow * 5 + bCol] + self.key[bRow * 5 + aCol]



    def __encrypt__(self, plainText):

        #chuẩn hóa plainText
        new_plaintext = ""
        for i in range(len(plainText)-1):
            if plainText[i] == plainText[i+1]:
                new_plaintext += plainText[i] + "X"
            else:
                new_plaintext += plainText[i]
        new_plaintext += plainText[-1]

        if len(new_plaintext) %2 != 0:
            new_plaintext += "X"
        plainText = new_plaintext
 
        cipherText = ""
        for i in range(0, len(plainText),2):
            cipherText += self.__encrypt_pair__(plainText[i], plainText[i+1])

        return cipherText
    


    def __decrypt__(self, cipherText):
        plainText = ""
        for i in range(0, len(cipherText),2):
            plainText += self.__decrypt_pair(cipherText[i], cipherText[i+1])
        
        return plainText



if __name__ == "__main__":
    p = Playfair("THEDIEIS")
    print(p.__encrypt__("THETRUTHWILLOU"))
    print(p.__decrypt__(p.__encrypt__("THETRUTHWILLOU")))







    