import random 
import gerenate_prime 
import findPrimitiveRoot


class private_key():
    def __init__(self, x = None, p = None, a = None):
        self.x = x
        self.p = p
        self.a = a

class public_key():
    def __init__(self, p = None, a = None, y = None):
        self.p = p
        self.a = a
        self.y = y


#sinh khóa
def generate_key(iNumbits = 256):
    p = gerenate_prime.generate_prime(iNumbits)
    a = findPrimitiveRoot.find_primitive_root(p)
    x = random.randint(1,p - 2)
    y = pow(a, x, p)
    publicKey = public_key(p,a,y)
    privateKey = private_key(x,p,a)
    return {'publicKey':publicKey, 'privateKey':privateKey}

#thông điệp M
def encrypt(M, publicKey, k = None):
    k = random.randint(1, publicKey.p - 2)
    K = pow(publicKey.y, k, publicKey.p)
    c1 = pow(publicKey.a, k, publicKey.p)
    c2 = (K * M) % publicKey.p
    return {'c1':c1, 'c2':c2}

def decrypt(c1, c2, privateKey):
    K = pow(c1, privateKey.x, privateKey.p)
    M = (c2 * pow(K, -1, privateKey.p)) % privateKey.p
    return M


if __name__ == "__main__":
    p = 6827
    a = 5
    xA = 307
    y = pow(a, xA, p)
    
    pubKey = public_key(p, a, y)
    priveKey = private_key(xA, p, a)

    encry = encrypt(474, pubKey, 919)
    decry = decrypt(encry['c1'], encry['c2'], priveKey)

    print(f"a. PU(q, a, Ya) = {pubKey.p, pubKey.a, pubKey.y}")
    print(f"b. (C1, C2) = {encry['c1'], encry['c2']}")
    print(f"d. M = {decry}")




