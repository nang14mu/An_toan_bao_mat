import random 
import gerenate_prime
import findPrimitiveRoot

class A():
    def __init__(self, q = 6947, anpha = 5):
        self.q = q
        self.anpha = anpha
#        self.xA = random.randint(1, q - 1)
        self.xA = 395
        self.yA = pow(self.anpha, self.xA, self.q)
        self.K = None

    def compute_K(self, yB):
        self.K = pow(yB, self.xA, self.q)

    def getyA(self):
        return self.yA
    
    def getK(self):
        return self.K

class B():
    def __init__(self, q = 6947, anpha = 5):
        self.q = q
        self.anpha = anpha
#        self.xB = random.randint(1, q - 1)
        self.xB = 338
        self.yB = pow(self.anpha, self.xB, self.q)
        self.K = None

    def compute_K(self, yA):
        self.K = pow(yA, self.xB, self.q)
    
    def getyB(self):
        return self.yB
    
    def getK(self):
        return self.K
    
def generateKey(iNumBits = 256):
    prime = gerenate_prime.generate_prime(iNumBits)
    primitiveRoot = findPrimitiveRoot.find_primitive_root(prime)
    return {'prime': prime, 'primitiveRoot': primitiveRoot}

if __name__ == "__main__":
    alice = A()
    bob = B()

    alice.compute_K(bob.getyB())
    bob.compute_K(alice.getyA())

    print(f"yA = {alice.getyA()}")
    print(f"yB = {bob.getyB()}")

    print(f"Kab = {alice.getK()}")
    print(f"Kba = {bob.getK()}")






