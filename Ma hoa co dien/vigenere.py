
alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

class vigenere:

    def __init__(self, key):
        self.key = key

    def __encrypt__(self, plainText):
        cipherText = ""

        plainText = plainText.upper()
        for i,ch in enumerate(plainText):
            key_char = self.key[i % len(self.key)]
            new_char = alphabet[(alphabet.index(key_char) + alphabet.index(ch)) % 26]
            cipherText += new_char

        return cipherText

    
    def __decrypt__(self, cipherText):
        plainText = ""

        cipherText = cipherText.upper()
        for i, ch in enumerate(cipherText):
            key_char = self.key[i % len(self.key)]
            generate_char = alphabet[(alphabet.index(ch) - alphabet.index(key_char)) % 26]
            plainText += generate_char

        return plainText
    


if __name__ == "__main__":
    v = vigenere("ABCD")
    print(v.__encrypt__("NANGvannang"))
    print(v.__decrypt__(v.__encrypt__("NANGvannang")))

    