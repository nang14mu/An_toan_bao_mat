#include <bits/stdc++.h>
using namespace std;

int extended_gcd(int a, int b, int &x, int &y) {
    if (a == 0) {
        x = 0;
        y = 1;
        return b;  // gcd
    }
    int x1, y1; // Cac he so tam thoi
    int gcd = extended_gcd(b % a, a, x1, y1);
    
    // update x va y
    x = y1 - (b / a) * x1;
    y = x1;

    return gcd;  // Tra ve gcd
}


int mod_inverse(int a, int m) {
    int x, y;
    int gcd = extended_gcd(a, m, x, y);
    if (gcd != 1) {
        return -1;  
    } else {
        return (x % m + m) % m;
    }
}

int chinese_remainder_theorem(int a1, int m1, int a2, int m2, int a3, int m3) {

    int M = m1 * m2 * m3;

    int M1 = M / m1;
    int M2 = M / m2;
    int M3 = M / m3;

    int y1 = mod_inverse(M1, m1);
    int y2 = mod_inverse(M2, m2);
    int y3 = mod_inverse(M3, m3);

    int x = (a1 * M1 * y1 + a2 * M2 * y2 + a3 * M3 * y3) % M;

    if (x < 0) {
        x += M;
    }

    return x;
}

int main(){
	cout << "Output: x = " << chinese_remainder_theorem(17,19,11,5,16,3) << endl;
}


