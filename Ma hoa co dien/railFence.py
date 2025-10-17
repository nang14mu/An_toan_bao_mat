class railFence:
    def __init__(self, key):
        self.key = key

    def __encrypt__(self, plainText):
        fence =   ['' for _ in range(self.key)]
        rail = 0
        direction = 1

        for char in plainText:
            fence[rail] += char
            rail +=direction
            if rail == 0 or rail == self.key - 1:
                direction *= -1
        
        return ''.join(fence)
    

    def __decrypt__(self, cipherText):
        fence = [['' for _ in range(len(cipherText))] for _ in range(self.key)]
        rail = 0
        direction = 1

        for i in range(len(cipherText)):
            fence[rail][i] = '*'
            rail += direction
            if rail == 0 or rail == self.key - 1:
                direction *= -1

        index = 0
        for i in range(self.key):
            for j in range(len(cipherText)) :
                if fence[i][j] == "*" and index < len(cipherText):
                    fence[i][j] = cipherText[index]
                    index+=1

        plainText = ""
        rail = 0
        direction = 1
        for i in range(len(cipherText)):
            plainText += fence[rail][i]
            rail +=direction
            if rail == 0 or rail == self.key - 1:
                direction *= -1   

        return plainText    



if __name__ == "__main__":
    r = railFence(9)
    print(r.__encrypt__("ABADBEGINNINGMAKES"))
    print(r.__decrypt__(r.__encrypt__("ABADBEGINNINGMAKES")))