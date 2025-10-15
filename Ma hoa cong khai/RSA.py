import random
import Rabin_Miller as rm
import gerenate_prime as gp

class private_key():
    def __init__(self,d = None, p = None, q = None, N = None):
        self.d = d
        self.p = p
        self.q = q
        self.N = N

class public_key():
    def __init__(self, e = None, N = None):
        self.e = e
        self.N = N


def gcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a

#tìm nghịch đảo module theo Eucid mở rộng
def find_modular_inverse(a, m):
    m0, x0, x1 = m, 0, 1
    while a > 1:
        q = a // m
        a, m = m, a % m
        x0, x1 = x1 - q * x0, x0
    if x1 < 0:
        x1 += m0
    return x1


def gerenate_Key(iNumbits = 64):
    p = gp.generate_prime()
    q = gp.generate_prime()
    N = p * q
    phi = (q-1) * (p-1) #do p và q là 2 số nguyên tố 

    e = random.randint(2, phi - 1)
    while gcd(e, phi) != 1:
        e = random.randint(2, phi - 1)

    d = find_modular_inverse(e, phi)

    publicKey = public_key(e, N)
    privateKey = private_key(d, p, q, N)

    return {'publicKey': publicKey, 'privateKey': privateKey}

def encrypt(M, publicKey):
    return pow(M, publicKey.e, publicKey.N)

def decrypt(C, privateKey):
    return pow(C, privateKey.d, privateKey.N)
    

if __name__ == "__main__":
    key = gerenate_Key()
    pub = key['publicKey']
    priv = key['privateKey']

    encry = encrypt(333, pub)
    decry = decrypt(encry, priv)

    print(encry)
    print(decry)

