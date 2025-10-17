alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

class autoKey:

    def __init__(self, key):
        self.key = key

    def __encrypt__(self, plainText):
        key = self.key
        cipherText = "" 

        plainText = plainText.upper()
        plainText ="".join(plainText.split())
        key = key + plainText

        for i,ch in enumerate(plainText):
            key_char = key[i]
            new_char = alphabet[(alphabet.index(key_char) + alphabet.index(ch)) % 26]
            cipherText += new_char

        return cipherText

    
    def __decrypt__(self, cipherText):
        key = self.key
        plainText = ""

        for i,ch in enumerate(cipherText):
            key_char = key[i]
            generate_char = alphabet[(alphabet.index(ch) - alphabet.index(key_char)) % 26]
            plainText += generate_char
            key += generate_char
        
        return plainText



if __name__ == "__main__":
    a = autoKey("YOUREON")
    print(a.__encrypt__("MONEYMAKESTHE"))
    print(a.__decrypt__(a.__encrypt__("MONEYMAKESTHE")))
