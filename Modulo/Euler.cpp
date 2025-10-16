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

int euler_totient(int n) {
    int result = n;

    for (int p = 2; p * p <= n; ++p) {
        if (n % p == 0) {
            while (n % p == 0) {
                n /= p;
            }
            result -= result / p;
        }
    }

    if (n > 1) {
        result -= result / n;
    }

    return result;
}

int euler_power_mod(int a, int m, int n) {
    int phi_n = euler_totient(n);

    if (m >= phi_n) {
        m = m % phi_n;
    }

    return powerMod(a, m, n);
}

int main(){
	cout << "Output: b = " << euler_power_mod(23,3885,395) << endl;
}
