alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

def encrypt(plainText, key):
    plainText = plainText.upper()
    
    cipherText = ""
    
    for char in plainText:
        if char in alphabet:
            pos = alphabet.index(char)
            cipherText += key[pos]
        else:
            cipherText += char
    
    return cipherText


def decrypt(cipherText, key):
    cipherText = cipherText.upper()
    plainText = ""
    
    for char in cipherText:
        if char in key:
            pos = key.index(char)
            plainText += alphabet[pos]
        else:
            plainText += char
    
    return plainText

if __name__ == "__main__":
    M = "AWOMANGIVESANDFO"
    K = "THLEYNPSXADWKFUBOGMQVJRCIZ"
    cipherText = encrypt(M, K)
    plainText = decrypt(cipherText, K)

    print(cipherText)
    print(plainText)