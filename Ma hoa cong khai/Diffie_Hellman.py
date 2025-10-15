import random 
import gerenate_prime
import findPrimitiveRoot

class A():
    def __init__(self, q, anpha):
        self.q = q
        self.anpha = anpha
        self.xA = random.randint(1, q - 1)
        self.xA = 97
        self.yA = pow(self.anpha, self.xA, self.q)
        self.K = None

    def compute_K(self, yB):
        self.K = pow(yB, self.xA, self.q)

    def getyA(self):
        return self.yA
    
    def getK(self):
        return self.K

class B():
    def __init__(self, q, anpha):
        self.q = q
        self.anpha = anpha
        self.xB = random.randint(1, q - 1)
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
    key = generateKey()
    prime = key['prime']
    primitiveRoot = key['primitiveRoot']

    alice = A(prime, primitiveRoot)
    bob = B(prime, primitiveRoot)

    alice.compute_K(bob.getyB())
    bob.compute_K(alice.getyA())

    print(alice.getK())
    print(bob.getK())






