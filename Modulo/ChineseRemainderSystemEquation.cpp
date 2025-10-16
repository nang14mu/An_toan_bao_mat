#include <bits/stdc++.h>
using namespace std;

int powerMod(int a, int k, int n) {
    int result = 1;
    a = a % n;
    while (k > 0) {
        if (k % 2 == 1) result = (result * a) % n;
        a = (a * a) % n;
        k /= 2;
    }
    return result;
}

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

vector<int> prime_factors(int n) {
    vector<int> factors;
    for (int i = 2; i * i <= n; i++) {
        while (n % i == 0) {
            factors.push_back(i);
            n /= i;
        }
    }
    if (n > 1) factors.push_back(n);  
    return factors;
}

int chineseRemainder(int a, int k, int n) {
    vector<int> factors = prime_factors(n);
    int M = 1;
    vector<int> r, M_i, y;

    for (int m_i : factors) {
        int r_i = powerMod(a, k, m_i);
        r.push_back(r_i);
        M *= m_i;
    }

    for (int i = 0; i < factors.size(); i++) {
        M_i.push_back(M / factors[i]);
        y.push_back(mod_inverse(M_i[i], factors[i]));
    }

    int result = 0;
    for (int i = 0; i < factors.size(); i++) {
        result = (result + 1LL * r[i] * M_i[i] * y[i]) % M;
    }

    if (result < 0) result += M;

    return result;
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

