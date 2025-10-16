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

int fermat_power_mod(int a, int m, int n) {
    if (m >= n) {
        m = m % (n - 1);
    }
    return powerMod(a, m, n);
}

int main(){
	cout << "Output: b = " << fermat_power_mod(439,760,6269) << endl;
}
