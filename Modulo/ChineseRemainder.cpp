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

int main(){
	cout << "Output: b = " << chineseRemainder(227,80,60421);
}

