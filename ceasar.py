class Ceasar:
    """Nguyên tắc của mật mã Caesar là văn bản mã được tạo ra bằng cách thay thế mỗi chữ cái 
       trong văn bản với một chữ cái cách nó một đoạn cho trước trong bảng chữ cái.
        
        Công thức: 
            c = (p + k) mod 26 

                trong đó:   + p là STT chữ cái trong bản rõ
                            + c là STT chữ cái trong bản mã
                            + k là khóa
                  
       """
    


    def __init__(self, shift = 13):
        self.shift = shift % 26
    
    def __encrypt__(self, plainText):
        cipherText = ""

        for i in plainText:
            if i.isalpha():
                base = ord('A') if i.isupper() else ord('a')

                letter = chr((ord(i) - base + self.shift) %26 + base)
                cipherText += letter
            else:
                cipherText += i
        
        return cipherText
    
    def __decrypt__(self, cipherText):
        plainText = ""

        for i in cipherText:
            if i.isalpha():
                base = ord('A') if i.isupper() else ord('a')

                letter = chr((ord(i) - base - self.shift) %26 + base)
                plainText += letter
            else:
                plainText += i
        
        return plainText
    
if __name__ == "__main__":
    c = Ceasar(3)
    cipher = c.__encrypt__("Nang")
    print("Ciphertext:", cipher)
    print("Decpertext:", c.__decrypt__(cipher))