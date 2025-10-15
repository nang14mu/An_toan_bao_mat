class columnarTransposition:
    def __init__(self, key):
        self.key = key

    def __sort__(self, word):
        t1 = [(word[i],i) for i in range(len(word))]
        t2 = [(k[1],i) for i,k in enumerate(sorted(t1))]
        return [q[1] for q in sorted(t2)]
    
    def __unsort__(self, word):
        t1 = [(word[i],i) for i in range(len(word))]
        return [q[1] for q in sorted(t1)]
    
    def __encrypt__(self, plainText):
        cipherText = ""
        ind = self.__sort__(self.key)
        for i in range(len(ind)):
            cipherText += plainText[ind.index(i)::len(self.key)]

        return cipherText
    
    def __decrypt__(self, cipherText):
        string = self.remove_punctuation(string)        
        ret = ['_']*len(string)
        L,M = len(string),len(self.keyword)
        ind = self.unsortind(self.keyword)
        upto = 0
        for i in range(len(self.keyword)):
            thiscollen = (int)(L/M)
            if ind[i]< L%M: thiscollen += 1
            ret[ind[i]::M] = string[upto:upto+thiscollen]
            upto += thiscollen
        return ''.join(ret)    

if __name__ == '__main__': 
    print('use "import pycipher" to access functions')
    c = columnarTransposition("4312567")
    print(c.__encrypt__("attackpostpopeduntiltwoamxyz"))
    if "TTNAAPTMTSUOAODWCOIXKPLYPETZ" == "ttnaaptmtsuoaodwcoixkplypetz".upper():
        print("true")

  