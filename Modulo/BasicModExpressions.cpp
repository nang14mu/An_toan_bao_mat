#include <bits/stdc++.h>
using namespace std;

int powerMod(int a, int m, int n) {
    int result = 1;
    a = a % n;
    while (m > 0) {
        if (m % 2 == 1) result = (result * a) % n;
        a = (a * a) % n;
        m /= 2;
    }
    return result;
}

int modInverse(int b, int n) {
    int x0 = 0, x1 = 1, a = b, m = n;
    while (a > 1) {
        int q = a / m;
        int t = m; 
        m = a % m; 
        a = t; 
        t = x0; 
        x0 = x1 - q * x0; 
        x1 = t; 
    }
    return (x1 + n) % n; 
}

int main(){
	int result_a = powerMod(37,581,127); 
    int result_b = powerMod(97,364,127); 

    cout << "A1 = (a^x + b^y) mod n: " << (result_a + result_b) % 127 << endl;
    cout << "A2 = (a^x - b^y) mod n: " << (result_a - result_b + 127) % 127 << endl; 
    cout << "A3 = (a^x * b^y) mod n: " << (result_a * result_b) % 127 << endl;

    if (result_b != 0) { 
        int b_inv = modInverse(result_b, 127); 
        cout << "A4 = (b^y)^(-1) mod n: " << b_inv << endl;
        cout << "A5 = (a^x / b^y) mod n: " << (result_a * b_inv) % 127 << endl;    
    }
}
