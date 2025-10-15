import random

#tìm căn nguyên thủy
def find_primitive_root(p):
    if p == 2:
        return 1
    
    p1 = 2
    p2 = (p - 1)//p1

    while 1:
        a = random.randint(2, p-1)
        if not (pow(a , (p - 1)//p1, p) == 1):
            if not (pow(a, (p - 1)//p2, p) == 1):
                return a