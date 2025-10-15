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
def encrypt(M, publicKey):
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
    key = generate_key()
    pub = key['publicKey']
    priv = key['privateKey']

    M = 333
    encry = encrypt(333, pub)
    decry = decrypt(encry['c1'], encry['c2'], priv)

    print(encry)
    print(decry)


