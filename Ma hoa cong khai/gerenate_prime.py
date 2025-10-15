import random
import Rabin_Miller as rm

#tạo sô nguyên tố
def generate_prime(iNumbits = 256):
    while True:
        p = random.randint(2**(iNumbits-1), 2**iNumbits-1)
        while p % 2 == 0:
            p = random.randint(2**(iNumbits-1), 2**iNumbits-1)

        while not rm.Rabin_Miller(p):
            p = random.randint(2**(iNumbits-1), 2**iNumbits-1)
            while p % 2 == 0:
                p = random.randint(2**(iNumbits-1), 2**iNumbits-1)

        p = p * 2 + 1
        if rm.Rabin_Miller(p):
            return p