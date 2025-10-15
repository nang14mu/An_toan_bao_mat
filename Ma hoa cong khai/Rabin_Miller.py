import random

#Kiểm tra số nguyên tố bằng thuật toán Rabin_Miller
#tính a^k mod n
def binaryPower(a, k, n):
    a = a % n
    res = 1
    while(k):
        if(k&1):
            res = (res * a) % n
        a = (a * a) % n
        k = k//2
    
    return res

def test(a,n,k,m):
    mod = binaryPower(a,m,n)
    if mod == 1 or mod == n-1:
        return True
    for l in range(k-1):
        mod = (mod * mod) % n
        if mod == n-1:
            return True
    return False

def Rabin_Miller(n):
    if n == 2 or n==3 or n == 5 or n == 7:
        return True
    if n < 11:
        return False
    
    k = 0
    m = n-1
    while m % 2 == 0:
        m//=2
        k+=1

    repeatTime = 3
    for i in range(repeatTime):
        a = random.randint(2, n-2)
        if not test(a,n,k,m):
            return False
    return True


if __name__ == "__main__":
    print(Rabin_Miller(17))